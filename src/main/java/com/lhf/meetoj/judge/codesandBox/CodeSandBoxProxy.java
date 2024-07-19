package com.lhf.meetoj.judge.codesandBox;

import com.lhf.meetoj.judge.codesandBox.model.ExcuteCodeRequest;
import com.lhf.meetoj.judge.codesandBox.model.ExcuteCodeResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class CodeSandBoxProxy implements CodeSandBox {
    private final CodeSandBox codeSandBox;

    @Override
    public ExcuteCodeResponse excute(ExcuteCodeRequest excuteCodeRequest) {
        log.info("excute code request: {}", excuteCodeRequest);
        ExcuteCodeResponse codeResponse = codeSandBox.excute(excuteCodeRequest);
        log.info("excute code response: {}", codeResponse);
        return codeResponse;
    }
}