package com.lhf.meetoj.judge.codesandBox.Impl;

import com.lhf.meetoj.judge.codesandBox.CodeSandBox;
import com.lhf.meetoj.judge.codesandBox.model.ExcuteCodeRequest;
import com.lhf.meetoj.judge.codesandBox.model.ExcuteCodeResponse;

public class RemoteCodeSandBox implements CodeSandBox {

    @Override
    public ExcuteCodeResponse excute(ExcuteCodeRequest excuteCodeRequest) {
        System.out.println("远程代码沙箱");
        return null;
    }
}