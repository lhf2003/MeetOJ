package com.lhf.meetoj;

import com.lhf.meetoj.judge.codesandBox.CodeSandBoxFactory;
import com.lhf.meetoj.judge.codesandBox.CodeSandBoxProxy;
import com.lhf.meetoj.judge.codesandBox.CodeSandBox;
import com.lhf.meetoj.judge.codesandBox.model.ExcuteCodeRequest;
import com.lhf.meetoj.judge.codesandBox.model.ExcuteCodeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

/**
 * 主类测试
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@SpringBootTest
class MainApplicationTests {
    @Value("${codesandbox.type:example}")
    private String type;

    @Test
    void contextLoads() {
        // 获取真实对象
        CodeSandBox codeSandBox = CodeSandBoxFactory.instance(type);

        // 模拟请求消息
        ExcuteCodeRequest codeRequest = ExcuteCodeRequest.builder()
                .input(new ArrayList<>())
                .language("cpp")
                .code("int main(){}")
                .build();

        // 使用代理类代理真实对象扩展输出日志
        CodeSandBoxProxy proxy = new CodeSandBoxProxy(codeSandBox);
        ExcuteCodeResponse codeResponse = proxy.excute(codeRequest);
    }

}
