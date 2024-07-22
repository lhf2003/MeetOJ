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
import java.util.Arrays;

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
        // 使用代理类代理真实对象扩展输出日志
        CodeSandBoxProxy proxy = new CodeSandBoxProxy(codeSandBox);
        // 模拟请求消息
        String code = "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        int a = Integer.parseInt(args[0]);\n" +
                "        int b = Integer.parseInt(args[1]);\n" +
                "        System.out.println(\"结果：\" + (a + b));\n" +
                "    }\n" +
                "}";
        ExcuteCodeRequest codeRequest = ExcuteCodeRequest.builder()
                .input(Arrays.asList("1 2", "3 4"))
                .language("cpp")
                .code(code)
                .build();
        proxy.excute(codeRequest);
    }

}
