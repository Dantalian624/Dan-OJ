package com.dantalian.danoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dantalian.danoj.common.ErrorCode;
import com.dantalian.danoj.exception.BusinessException;
import com.dantalian.danoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.dantalian.danoj.model.entity.Question;
import com.dantalian.danoj.model.entity.QuestionSubmit;
import com.dantalian.danoj.model.entity.User;
import com.dantalian.danoj.model.enums.QuestionSubmitLanguageEnum;
import com.dantalian.danoj.model.enums.QuestionSubmitStatusEnum;
import com.dantalian.danoj.service.QuestionService;
import com.dantalian.danoj.service.QuestionSubmitService;
import com.dantalian.danoj.mapper.QuestionSubmitMapper;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

/**
* @author xtt
* @description 针对表【question_submit(题目提交表)】的数据库操作Service实现
* @createDate 2024-08-06 14:35:55
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService{
    @Resource
    private QuestionService questionService;

    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        // 校验编程语言是否合法
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum languageEnum = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if(languageEnum == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"编程语言错误");
        }
        Long questionId = questionSubmitAddRequest.getQuestionId();
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        // 是否已题目提交
        long userId = loginUser.getId();
        // 每个用户串行题目提交
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        questionSubmit.setLanguage(language);
        // 设置初始状态
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo("{}");
        boolean save = this.save(questionSubmit);
        if (!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"数据插入失败");
        }
        return questionSubmit.getId();
    }
}




