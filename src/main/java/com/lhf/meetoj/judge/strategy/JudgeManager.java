package com.lhf.meetoj.judge.strategy;

import com.lhf.meetoj.model.dto.question.JudgeInfo;
import com.lhf.meetoj.model.entity.QuestionSubmit;

/**
 * p判题策略管理（简化调用）
 */
public class JudgeManager implements JudgeStrategy{

    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        String language = questionSubmit.getLanguage();

        if ("java".equals(language)) {
            judgeStrategy = new JavaJudgeStrategy();
        } else if ("c".equals(language)) {
            judgeStrategy = new CJudgeStrategy();
        }

        return judgeStrategy.doJudge(judgeContext);
    }
}