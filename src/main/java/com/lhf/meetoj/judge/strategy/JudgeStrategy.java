package com.lhf.meetoj.judge.strategy;

import com.lhf.meetoj.model.dto.question.JudgeInfo;

public interface JudgeStrategy {
    JudgeInfo doJudge(JudgeContext context);
}
