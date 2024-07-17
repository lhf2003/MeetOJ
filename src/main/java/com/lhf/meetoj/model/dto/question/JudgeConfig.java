package com.lhf.meetoj.model.dto.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 判题配置
 * @author ${USER}
 * @date ${DATE} ${TIME}
 */
@NoArgsConstructor
@Data
public class JudgeConfig {

    /**
     * 时间限制
     * @date 2024/7/17 17:16
     * @param null
     * @return null
     */
    @JsonProperty("timeLimit")
    private Long timeLimit;

    /**
     * 内存限制
     * @date 2024/7/17 17:24
     * @param null
     * @return null
     */
    @JsonProperty("memoryLimit")
    private Long memoryLimit;
    /**
     * 堆栈限制
     * @date 2024/7/17 17:24
     * @param null
     * @return null
     */
    @JsonProperty("stackLimit")
    private Long stackLimit;
}