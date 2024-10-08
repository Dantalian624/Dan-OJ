<template>
    <div id="questionsView">

        <a-form :model="searchParams" layout="inline">
            <a-form-item field="title" label="名称" style="min-width: 240px;">
                <a-input v-model="searchParams.title" placeholder="请输入名称" />
            </a-form-item>
            <a-form-item field="tags" label="标签" style="min-width: 240px;">
                <a-input-tag v-model="searchParams.tags" placeholder="请输入标签" />
            </a-form-item>
            <a-form-item>
                <a-button type="primary" @click="doSubmit">查询</a-button>
            </a-form-item>
        </a-form>
        <a-divider size="0" />
        <a-table :columns="columns" :data="dataList"
            :pagination="{ pageSize: searchParams.pageSize, current: searchParams.current, total, showTotal: true }"
            @page-change="onPageChange">
            <template #tags="{ record }">
                <a-space wrap>
                    <a-tag v-for="(tag, index) of record.tags" :key="index" color="green">{{ tag }}</a-tag>
                </a-space>
            </template>
            <template #acceptedRate="{ record }">
                {{ `${record.submitNum ? (record.acceptedNum / record.submitNum) : '0'}%
                (${record.acceptedNum}/${record.submitNum})` }}
            </template>
            <template #createTime="{ record }">
                {{ moment(record.createTime).format('YYYY-MM-DD') }}
            </template>
            <template #optional="{ record }">
                <a-space>
                    <a-button type="primary" @click="toQuestionPage(record)">做题</a-button>
                </a-space>
            </template>
        </a-table>
    </div>
</template>

<script setup lang="ts">
import message from '@arco-design/web-vue/es/message';
import { QuestionControllerService } from '../../../generated/services/QuestionControllerService';
import { onMounted, ref, watchEffect } from 'vue';
import { Question, QuestionQueryRequest } from 'generated';
import { useRouter } from 'vue-router';
import moment from 'moment';

const dataList = ref([])
const total = ref(0)
const searchParams = ref<QuestionQueryRequest>({
    title: '',
    pageSize: 10,
    current: 1,
})

const loadData = async () => {
    const res = await QuestionControllerService.listQuestionVoByPageUsingPost(searchParams.value)
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
    title: '题号',
    dataIndex: 'id',
}, {
    title: '题目名称',
    dataIndex: 'title',
}, {
    title: '标签',
    slotName: 'tags',
}, {
    title: '通过率',
    slotName: 'acceptedRate'
}, {
    title: '创建时间',
    slotName: 'createTime',
}, {
    slotName: 'optional'
}];

const onPageChange = (page: number) => {
    searchParams.value = {
        ...searchParams.value,
        current: page
    }
}

/**
 * 跳转到做题页面
 */
const router = useRouter()
const toQuestionPage = (question: Question) => {
    router.push({
        path: `/view/question/${question.id}`,
    })
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
#questionsView {
    max-width: 1300px;
    margin: 0 auto;
}
</style>