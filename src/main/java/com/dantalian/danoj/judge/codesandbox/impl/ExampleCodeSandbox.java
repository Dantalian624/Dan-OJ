package com.dantalian.danoj.judge.codesandbox.impl;

import com.dantalian.danoj.judge.codesandbox.CodeSandbox;
import com.dantalian.danoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.dantalian.danoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.dantalian.danoj.model.dto.questionsubmit.JudgeInfo;
import com.dantalian.danoj.model.enums.JudgeInfoMessageEnum;
import com.dantalian.danoj.model.enums.QuestionSubmitLanguageEnum;
import com.dantalian.danoj.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * 示例代码沙箱（仅为了跑通业务流程）
 */
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {

        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
