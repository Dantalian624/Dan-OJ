package com.dantalian.danoj.service;

import com.dantalian.danoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.dantalian.danoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dantalian.danoj.model.entity.User;

/**
* @author xtt
* @description 针对表【question_submit(题目提交表)】的数据库操作Service
* @createDate 2024-08-06 14:35:55
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest 题目提交信息
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);


}
