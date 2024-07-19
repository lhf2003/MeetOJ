package com.lhf.meetoj.judge.strategy;

import com.lhf.meetoj.model.dto.question.JudgeCase;
import com.lhf.meetoj.model.dto.question.JudgeInfo;
import com.lhf.meetoj.model.entity.Question;
import com.lhf.meetoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 判题策略的上下文
 */
@Data
public class JudgeContext {
    private JudgeInfo judgeInfo;
    private List<JudgeCase> judgeCaseList;
    private List<String> outputList;
    private List<String> inputList;
    private Question question;
    private QuestionSubmit questionSubmit;
}