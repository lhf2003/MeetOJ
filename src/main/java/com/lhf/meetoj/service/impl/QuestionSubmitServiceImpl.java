package com.lhf.meetoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhf.meetoj.common.ErrorCode;
import com.lhf.meetoj.exception.BusinessException;
import com.lhf.meetoj.model.dto.question.QuestionSubmitAddRequest;
import com.lhf.meetoj.model.entity.*;
import com.lhf.meetoj.model.entity.QuestionSubmit;
import com.lhf.meetoj.service.QuestionService;
import com.lhf.meetoj.service.QuestionSubmitService;
import com.lhf.meetoj.mapper.QuestionSubmitMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LHF
 * @description 针对表【question_submit(题目提交表)】的数据库操作Service实现
 * @createDate 2024-07-17 16:33:48
 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit> implements QuestionSubmitService {

    @Resource
    private QuestionService questionService;

    /**
     * 点赞
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        // 判断实体是否存在，
        Long questionId = questionSubmitAddRequest.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 是否已提交题目
        long userId = loginUser.getId();
        // 每个用户串行提交题目
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        questionSubmit.setUserId(userId);
        questionSubmit.setLanague(questionSubmitAddRequest.getLanague());

        //todo 设置初始状态

        questionSubmit.setStatus(0);
        questionSubmit.setJudgeInfo("");
        boolean save = this.save(questionSubmit);
        return questionSubmit.getId();
    }

}




