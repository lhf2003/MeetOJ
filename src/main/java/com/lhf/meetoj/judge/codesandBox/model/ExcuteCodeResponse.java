package com.lhf.meetoj.judge.codesandBox.model;

import com.lhf.meetoj.model.dto.question.JudgeInfo;
import lombok.Data;

import java.util.List;

@Data
public class ExcuteCodeResponse {
    /**
     * 输出数据
     */
    List<String> output;

    /**
     * 题目信息
     */
    JudgeInfo judgeInfo;
}