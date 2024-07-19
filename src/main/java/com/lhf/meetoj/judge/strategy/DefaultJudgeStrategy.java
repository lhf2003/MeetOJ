package com.lhf.meetoj.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.lhf.meetoj.model.dto.question.JudgeCase;
import com.lhf.meetoj.model.dto.question.JudgeConfig;
import com.lhf.meetoj.model.dto.question.JudgeInfo;
import com.lhf.meetoj.model.entity.Question;
import com.lhf.meetoj.model.enums.JudgeInfoMessageEnum;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 默认的判题策略
 */
public class DefaultJudgeStrategy implements JudgeStrategy {
    @Override
    public JudgeInfo doJudge(JudgeContext context) {
        // 1.从上下文拿到题目信息
        JudgeContext judgeContext = new JudgeContext();
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        List<String> expectOutputList = judgeCaseList.stream().map(JudgeCase::getOutput).collect(Collectors.toList());
        List<String> outputList = judgeContext.getOutputList();
        List<String> inputList = judgeContext.getInputList();
        Question question = judgeContext.getQuestion();

        // 2.默认判题状态为正确
        JudgeInfoMessageEnum judgeInfoMessage = JudgeInfoMessageEnum.ACCEPTED;

        // 3. 判题逻辑
        // 3.1判断输入数量是否一致
        if (inputList.size() != outputList.size()) {
            judgeInfoMessage = JudgeInfoMessageEnum.FAILED;
            return null;
        }

        // 3.2判断运行结果是否与预期结果一致
        for (int i = 0; i < inputList.size(); i++) {
            if (!expectOutputList.get(i).equals(outputList.get(i))) {
                judgeInfoMessage = JudgeInfoMessageEnum.FAILED;
                return null;
            }
        }
        // 3.3判断运行结果信息（judgeInfo）是否符合judgeConfig
        // 拿到题目的 judgeConfig
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        Long expectTimeLimit = judgeConfig.getTimeLimit();
        Long expectMemoryLimit = judgeConfig.getMemoryLimit();
        Long expectStackLimit = judgeConfig.getStackLimit();

        // 拿到运行后的judgeInfo（代码沙箱返回的）
        String message = judgeInfo.getMessage(); //TODO 目前不知道有什么用
        Long time = judgeInfo.getTime();
        Long memory = judgeInfo.getMemory();
        // 3.3.1 判断内存是否符合
        if (expectMemoryLimit < memory) {
            judgeInfoMessage = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            return null;
        }
        // 3.3.2 判断运行时间是否符合
        if (expectTimeLimit < time) {
            judgeInfoMessage = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            return null;
        }
        // 封装judgeInfo返回
        judgeInfo.setMessage(judgeInfoMessage.getValue());
        judgeInfo.setTime(time);
        judgeInfo.setMemory(memory);

        return judgeInfo;
    }
}