package com.dantalian.danoj.judge;

import com.dantalian.danoj.common.ErrorCode;
import com.dantalian.danoj.exception.BusinessException;
import com.dantalian.danoj.model.entity.QuestionSubmit;
import com.dantalian.danoj.model.enums.QuestionSubmitStatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@Service
class JudgeServiceImplTest {

    @Resource
    QuestionSubmit questionSubmit = new QuestionSubmit();

    @Resource
    private JudgeService judgeService;

    @Resource
    JudgeServiceImpl judgeServiceImpl;

    @Test
    void doJudge() {
        questionSubmit.setUserId(1818992418576822274L);
        questionSubmit.setQuestionId(1828653989791952898L);
        questionSubmit.setCode("javavavavavavava");
        questionSubmit.setLanguage("java");
        // 设置初始状态
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo("{}");
        System.out.println(questionSubmit.getId());
    }

    @Test
    void id(){
        QuestionSubmit questionSubmit = new QuestionSubmit();
        System.out.println(questionSubmit.getId());
    }

}