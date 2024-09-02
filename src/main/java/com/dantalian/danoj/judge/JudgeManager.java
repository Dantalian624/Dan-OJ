package com.dantalian.danoj.judge;

import com.dantalian.danoj.judge.strategy.DefaultJudgeStrategy;
import com.dantalian.danoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.dantalian.danoj.judge.strategy.JudgeContext;
import com.dantalian.danoj.judge.strategy.JudgeStrategy;
import com.dantalian.danoj.model.dto.questionsubmit.JudgeInfo;
import com.dantalian.danoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo dojudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();

        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if("java".equals(language)){
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }

        return judgeStrategy.dojudge(judgeContext);
    }
}
