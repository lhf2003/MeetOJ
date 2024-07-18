package com.lhf.meetoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhf.meetoj.annotation.AuthCheck;
import com.lhf.meetoj.common.BaseResponse;
import com.lhf.meetoj.common.ErrorCode;
import com.lhf.meetoj.common.ResultUtils;
import com.lhf.meetoj.constant.UserConstant;
import com.lhf.meetoj.exception.BusinessException;
import com.lhf.meetoj.model.dto.question.QuestionQueryRequest;
import com.lhf.meetoj.model.dto.question.QuestionSubmitAddRequest;
import com.lhf.meetoj.model.dto.question.QuestionSubmitQueryRequest;
import com.lhf.meetoj.model.entity.Question;
import com.lhf.meetoj.model.entity.QuestionSubmit;
import com.lhf.meetoj.model.entity.User;
import com.lhf.meetoj.service.QuestionSubmitService;
import com.lhf.meetoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交
 *
 * @author <a href="https://github.com/lilhf">程序员鱼皮</a>
 * @from <a href="https://lhf.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;


    /**
     * 提交题目
     *
     * @author ${USER}
     * @date ${DATE} ${TIME}
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
                                               HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能操作
        final User loginUser = userService.getLoginUser(request);
        Long result = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 分页获取题目提交列表，除管理员外，普通用户只能看到自己的题目答案
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmit>> listQuestionByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        Page<QuestionSubmit> questionPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        return ResultUtils.success(questionPage);
    }
}
