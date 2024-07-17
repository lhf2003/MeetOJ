package com.lhf.meetoj.model.dto.question;

import lombok.Data;

@Data
public class QuestionSubmitAddRequest {
    /**
     * 语言
     */
    private String lanague;

    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 用户代码
     */
    private String code;

    private static final long serialVersionUID = 1L;
}