package com.lhf.meetoj.service;

import com.lhf.meetoj.model.dto.question.QuestionSubmitAddRequest;
import com.lhf.meetoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lhf.meetoj.model.entity.User;

/**
* @author LHF
* @description 针对表【question_submit(题目提交表)】的数据库操作Service
* @createDate 2024-07-17 16:33:48
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 点赞
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

}
