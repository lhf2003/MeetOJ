package com.lhf.meetoj.judge.codesandBox;

import com.lhf.meetoj.judge.codesandBox.model.ExcuteCodeRequest;
import com.lhf.meetoj.judge.codesandBox.model.ExcuteCodeResponse;

public interface CodeSandBox {
    ExcuteCodeResponse excute(ExcuteCodeRequest excuteCodeRequest);
}