package com.lhf.meetoj.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目编程语言枚举
 *
 * @author <a href="https://github.com/lilhf">程序员鱼皮</a>
 * @from <a href="https://lhf.icu">编程导航知识星球</a>
 */
public enum QuestionLanguageEnum {
    JAVA("Java", "Java"),
    C("C", "C"),
    C_PLUS_PLUS("C++", "C++"),
    C_SHARP("C#", "C#"),
    JAVASCRIPT("JavaScript", "JavaScript"),
    GO("Go", "Go"),
    RUST("Rust", "Rust"),
    PYTHON("Python", "Python");

    // 其他语言

    private final String text;

    private final String value;

    QuestionLanguageEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static QuestionLanguageEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (QuestionLanguageEnum anEnum : QuestionLanguageEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
