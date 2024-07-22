package com.lhf.meetoj.judge.codesandBox.Impl;

import com.lhf.meetoj.judge.codesandBox.CodeSandBox;
import com.lhf.meetoj.judge.codesandBox.model.ExcuteCodeRequest;
import com.lhf.meetoj.judge.codesandBox.model.ExcuteCodeResponse;
import com.lhf.meetoj.model.dto.question.JudgeInfo;

public class ExampleCodeSandBox implements CodeSandBox {
    @Override
    public ExcuteCodeResponse excute(ExcuteCodeRequest excuteCodeRequest) {

        ExcuteCodeResponse codeResponse = new ExcuteCodeResponse();
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage("不知道写什么，后期改");
        judgeInfo.setTime(1000L);
        judgeInfo.setMemory(1000L);
        codeResponse.setJudgeInfo(judgeInfo);
        codeResponse.setOutputList(excuteCodeRequest.getInput());
        return codeResponse;
    }
}