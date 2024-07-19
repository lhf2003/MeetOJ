package com.lhf.meetoj.judge;

import com.lhf.meetoj.model.entity.QuestionSubmit;

public interface JudgeService {
    QuestionSubmit doJudge(Long questionSubmitId);
}