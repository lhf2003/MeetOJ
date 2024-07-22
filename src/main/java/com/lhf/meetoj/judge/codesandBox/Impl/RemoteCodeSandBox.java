package com.lhf.meetoj.judge.codesandBox.Impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;
import com.lhf.meetoj.common.ErrorCode;
import com.lhf.meetoj.exception.BusinessException;
import com.lhf.meetoj.judge.codesandBox.CodeSandBox;
import com.lhf.meetoj.judge.codesandBox.model.ExcuteCodeRequest;
import com.lhf.meetoj.judge.codesandBox.model.ExcuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RemoteCodeSandBox implements CodeSandBox {
    private static String REQUEST_HEAD = "auth";
    private static String REQUEST_HEAD_VALUE = "123456";

    @Override
    public ExcuteCodeResponse excute(ExcuteCodeRequest excuteCodeRequest) {
        log.info("调用远程代码沙箱，请求参数：{}", excuteCodeRequest);
        // 发送HTTP请求调用代码沙箱接口
        String url = "http://192.168.3.129:8200/execute_code";
        String json = JSONUtil.toJsonStr(excuteCodeRequest);
        HttpRequest httpRequest = HttpUtil.createPost(url);
        String responseBody = httpRequest
                .header(REQUEST_HEAD, REQUEST_HEAD_VALUE)
                .body(json)
                .execute()
                .body();
        if (responseBody == null) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "远程代码沙箱调用失败");
        }
        // 将响应结果转换为ExcuteCodeResponse对象返回
        ExcuteCodeResponse excuteCodeResponse = JSONUtil.toBean(responseBody, ExcuteCodeResponse.class);
        log.info("调用远程代码沙箱结束，响应结果：{}", excuteCodeResponse);
        return excuteCodeResponse;
    }
}