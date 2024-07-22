package com.lhf.meetoj.judge.codesandBox.model;

import com.lhf.meetoj.model.dto.question.JudgeInfo;
import lombok.Data;

import java.util.List;

@Data
public class ExcuteCodeResponse {
    /**
     * 输出数据
     */
    private List<String> outputList;

    /**
     * 题目信息
     */
    private JudgeInfo judgeInfo;

    /**
     * 状态码
     */
    private Integer status;
}