package com.lhf.meetoj.model.dto.question;

import lombok.Data;

/**
 * 判题用例
 * @author ${USER}
 * @date ${DATE} ${TIME}
 */
@Data
public class JudgeCase {
    /**
     * 输入用例
     *
     */
    private String input;
    /**
     * 输出用例
     *
     */
    private String output;
}