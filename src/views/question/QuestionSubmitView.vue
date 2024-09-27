<template>
    <div id="questionSubmitView">
        <a-form :model="searchParams" layout="inline">
            <a-form-item field="questionId" label="题号" style="min-width: 240px;">
                <a-input id="questionId" name="questionId" v-model="searchParams.questionId" placeholder="请输入ID" />
            </a-form-item>
            <a-form-item field="language" label="编程语言" style="min-width: 240px;">
                <a-select id="language" name="language" v-model="searchParams.language" :style="{ width: '320px' }"
                    placeholder="选择编程语言">
                    <a-option value="java">java</a-option>
                    <a-option value="cpp">cpp</a-option>
                    <a-option value="go">go</a-option>
                    <a-option value="html">html</a-option>
                </a-select>
            </a-form-item>
            <a-form-item>
                <a-button type="primary" @click="doSubmit">查询</a-button>
            </a-form-item>
        </a-form>
        <a-divider size="0" />
        <a-table :columns="columns" :data="dataList"
            :pagination="{ pageSize: searchParams.pageSize, current: searchParams.current, total, showTotal: true }"
            @page-change="onPageChange">
            <template #judgeInfo="{ record }">
                {{ record.judgeInfo.message }}
            </template>
            <template #memory="{ record }">
                {{ record.judgeInfo.memory }}
            </template>
            <template #time="{ record }">
                {{ record.judgeInfo.time }}
            </template>
            <template #createTime="{ record }">
                {{ moment(record.createTime).format('YYYY-MM-DD HH:mm') }}
            </template>
        </a-table>
    </div>
</template>

<script setup lang="ts">
import message from '@arco-design/web-vue/es/message';
import { QuestionControllerService } from '../../../generated/services/QuestionControllerService';
import { onMounted, ref, watchEffect } from 'vue';
import { QuestionSubmitQueryRequest } from 'generated';
import moment from 'moment';

const dataList = ref([])
const total = ref(0)
const searchParams = ref<QuestionSubmitQueryRequest>({
    questionId: undefined,
    language: undefined,
    pageSize: 10,
    current: 1,
})

const loadData = async () => {
    const res = await QuestionControllerService.listQuestionSubmitByPageUsingPost({
        ...searchParams.value,
        sortField: 'createTime',
        sortOrder: 'descend',
    }

    )
    if (res.code === 0) {
        dataList.value = res.data.records
        total.value = parseInt(res.data.total, 10)
    } else {
        message.error('加载失败' + res.message)
    }
}

/**
 * 监听 searchParams 变量，改变时触发页面的重新加载
 */
watchEffect(() => {
    loadData()
})

/**
 * 页面加载时请求数据
 */
onMounted(() => {
    loadData();
})


const columns = [{
    title: '提交号',
    dataIndex: 'id',
}, {
    title: '编程语言',
    dataIndex: 'language',
}, {
    title: '判题信息',
    dataIndex: 'judgeInfo',
    slotName: 'judgeInfo',
}, {
    title: '内存',
    dataIndex: 'memory',
    slotName: 'memory',
}, {
    title: '执行耗时',
    dataIndex: 'time',
    slotName: 'time',
}, {
    title: '判题状态',
    dataIndex: 'status'
}, {
    title: '题目 id',
    dataIndex: 'questionId',
}, {
    title: '提交者 id',
    dataIndex: 'userId',
}, {
    title: '创建时间',
    slotName: 'createTime',
}];

const onPageChange = (page: number) => {
    searchParams.value = {
        ...searchParams.value,
        current: page
    }
}

/**
 * 题目搜索
 */
const doSubmit = () => {
    // 重置搜索页号
    searchParams.value = {
        ...searchParams.value,
        current: 1
    }
}

</script>

<style scoped>
#questionSubmitView {
    max-width: 1300px;
    margin: 0 auto;
}
</style>