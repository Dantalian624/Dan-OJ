<template>
    <div id="viewQuestionView">
        <a-row :gutter="[24, 24]">
            <a-col :md="12" :xs="24">
                <a-tabs default-active-key="question">
                    <a-tab-pane key="question" title="题目">
                        <a-card :title="question?.title">
                            <a-descriptions title="判题条件" :column="{ xs: 1, md: 2, lg: 3 }">
                                <a-descriptions-item label="时间限制">
                                    {{ question?.judgeConfig?.timeLimit }} ms
                                </a-descriptions-item>
                                <a-descriptions-item label="内存限制">
                                    {{ question?.judgeConfig?.memoryLimit }} KB
                                </a-descriptions-item>
                                <a-descriptions-item label="堆栈限制">
                                    {{ question?.judgeConfig?.stackLimit }} KB
                                </a-descriptions-item>
                            </a-descriptions>
                            <a-divider />
                            <MdViewer :value="question?.content" />
                            <template #extra>
                                <a-space wrap>
                                    <a-tag v-for="(tag, index) of question?.tags" :key="index" color="green">{{ tag
                                        }}</a-tag>
                                </a-space>
                            </template>
                        </a-card>
                    </a-tab-pane>
                    <a-tab-pane key="comment" title="评论">
                        评论区
                    </a-tab-pane>
                    <a-tab-pane key="answer" title="答案">
                        暂时无法查看答案
                    </a-tab-pane>
                </a-tabs>

            </a-col>
            <!-- <a-col :md="12" :xs="24">
                <a-tabs default-active-key="code">
                    <a-tab-pane key="code" title="代码编辑">
                        <a-card>
                            <CodeEditor />
                        </a-card>
                        <a-divider size="0" />
                        <a-button type="primary" style="min-width: 100px;">提交</a-button>

                    </a-tab-pane>
                </a-tabs>
            </a-col> -->

            <a-col :md="12" :xs="24">
                <a-tabs default-active-key="code">
                    <a-tab-pane key="code" title="代码编辑">
                        <a-card>
                            <a-form :model="form" layout="inline">
                                <a-form-item field="title" label="编程语言" style="min-width: 240px;">
                                    <a-select v-model="form.language" :style="{ width: '320px' }" placeholder="选择编程语言">
                                        <a-option>java</a-option>
                                        <a-option>cpp</a-option>
                                        <a-option>go</a-option>
                                        <a-option>html</a-option>
                                    </a-select>
                                </a-form-item>
                            </a-form>
                            <CodeEditor :value="form.code" :language="form.language" :handle-change="changeCode" />
                        </a-card>
                        <a-divider size="0" />
                        <div style="display: flex; justify-content: flex-end;">
                            <a-button type="primary" style="min-width: 100px; margin-right: 10px;"
                                @click="doSubmit">提交</a-button>
                        </div>
                    </a-tab-pane>
                </a-tabs>
            </a-col>
        </a-row>
    </div>
</template>

<script setup lang="ts">
import message from '@arco-design/web-vue/es/message';
import { QuestionControllerService } from '../../../generated/services/QuestionControllerService';
import { onMounted, ref } from 'vue';
import { QuestionSubmitAddRequest, QuestionVO } from '../../../generated';
import CodeEditor from '@/components/CodeEditor.vue';
import MdViewer from '@/components/MdViewer.vue';


// 获取 url 的 id
interface Props {
    id: string
}
const props = withDefaults(defineProps<Props>(), {
    id: () => ''
})

const question = ref<QuestionVO>()


const loadData = async () => {
    const res = await QuestionControllerService.getQuestionVoByIdUsingGet(props.id as any)
    if (res.code === 0) {
        question.value = res.data
    } else {
        message.error('加载失败' + res.message)
    }
}

const form = ref<QuestionSubmitAddRequest>({
    language: 'java',
    code: ''
})

/**
 * 提交代码
 */
const doSubmit = async () => {
    if (!question.value?.id) {
        return
    }
    const res = await QuestionControllerService.doQuestionSubmitUsingPost({
        ...form.value,
        questionId: question.value?.id
    })
    console.log(res.code)
    if (res.code === 0) {
        message.success('提交成功')
    } else {
        message.error('提交失败' + res.message)
    }
}



/**
 * 页面加载时请求数据
 */
onMounted(() => {
    loadData();
})

const changeCode = (value: string) => {
    form.value.code = value
}



</script>

<style>
#viewQuestionView {
    max-width: 1440px;
    margin: 0 auto;
}

.arco-space-item {
    margin-bottom: 0 !important;
}
</style>