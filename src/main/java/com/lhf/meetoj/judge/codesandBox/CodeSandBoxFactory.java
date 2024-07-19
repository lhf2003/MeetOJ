package com.lhf.meetoj.judge.codesandBox;

import com.lhf.meetoj.judge.codesandBox.Impl.ExampleCodeSandBox;
import com.lhf.meetoj.judge.codesandBox.Impl.RemoteCodeSandBox;

public class CodeSandBoxFactory {

    public static CodeSandBox instance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandBox();
            case "remote":
                return new RemoteCodeSandBox();
            default:
                return new ExampleCodeSandBox();
        }
    }
}