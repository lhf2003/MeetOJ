package com.lhf.meetoj.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhf.meetoj.common.ErrorCode;
import com.lhf.meetoj.constant.CommonConstant;
import com.lhf.meetoj.exception.BusinessException;
import com.lhf.meetoj.model.dto.question.*;
import com.lhf.meetoj.model.entity.*;
import com.lhf.meetoj.model.entity.QuestionSubmit;
import com.lhf.meetoj.model.enums.QuestionLanguageEnum;
import com.lhf.meetoj.model.enums.QuestionSubmitEnum;
import com.lhf.meetoj.service.QuestionService;
import com.lhf.meetoj.service.QuestionSubmitService;
import com.lhf.meetoj.mapper.QuestionSubmitMapper;
import com.lhf.meetoj.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
        QuestionLanguageEnum language = QuestionLanguageEnum.getEnumByValue(questionSubmitAddRequest.getLanague());

        if (language == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编程语言错误");
        }
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
        questionSubmit.setLanague(language.getValue());

        //todo 设置初始状态

        questionSubmit.setStatus(QuestionSubmitEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo("");
        boolean save = this.save(questionSubmit);
        return questionSubmit.getId();
    }
    /**
     * 获取查询包装类
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest) {
        QueryWrapper<QuestionSubmit> queryWrapper = new QueryWrapper<>();
        if (questionSubmitQueryRequest == null) {
            return queryWrapper;
        }
        Long id = questionSubmitQueryRequest.getId();
        Long questionId = questionSubmitQueryRequest.getQuestionId();
        String lanague = questionSubmitQueryRequest.getLanague();
        String judgeInfo = questionSubmitQueryRequest.getJudgeInfo();
        Integer status = questionSubmitQueryRequest.getStatus();
        Date createTime = questionSubmitQueryRequest.getCreateTime();
        Date updateTime = questionSubmitQueryRequest.getUpdateTime();
        String sortField = questionSubmitQueryRequest.getSortField();
        String sortOrder = questionSubmitQueryRequest.getSortOrder();

        // 拼接查询条件
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(questionId), "userId", questionId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(lanague), "lanague", lanague);
        queryWrapper.eq(ObjectUtils.isNotEmpty(judgeInfo), "judgeInfo", judgeInfo);
        queryWrapper.eq(ObjectUtils.isNotEmpty(status), "status", status);
        queryWrapper.ge(ObjectUtils.isNotEmpty(createTime), "create_time", createTime);
        queryWrapper.le(ObjectUtils.isNotEmpty(updateTime), "update_time", updateTime);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

}




