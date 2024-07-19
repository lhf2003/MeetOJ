package com.lhf.meetoj.judge.codesandBox.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ExcuteCodeRequest {
    /**
     * 输入数据
     */
    List<String> input;
    /**
     * 编程语言
     */
    String language;
    /**
     * 代码
     */
    String code;
}