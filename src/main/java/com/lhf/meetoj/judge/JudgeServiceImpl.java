package com.lhf.meetoj.judge;

import cn.hutool.json.JSONUtil;
import com.lhf.meetoj.common.ErrorCode;
import com.lhf.meetoj.exception.BusinessException;
import com.lhf.meetoj.judge.codesandBox.CodeSandBox;
import com.lhf.meetoj.judge.codesandBox.CodeSandBoxFactory;
import com.lhf.meetoj.judge.codesandBox.CodeSandBoxProxy;
import com.lhf.meetoj.judge.codesandBox.model.ExcuteCodeRequest;
import com.lhf.meetoj.judge.codesandBox.model.ExcuteCodeResponse;
import com.lhf.meetoj.judge.strategy.DefaultJudgeStrategy;
import com.lhf.meetoj.judge.strategy.JudgeContext;
import com.lhf.meetoj.judge.strategy.JudgeManager;
import com.lhf.meetoj.model.dto.question.JudgeCase;
import com.lhf.meetoj.model.dto.question.JudgeInfo;
import com.lhf.meetoj.model.entity.Question;
import com.lhf.meetoj.model.entity.QuestionSubmit;
import com.lhf.meetoj.model.enums.QuestionSubmitEnum;
import com.lhf.meetoj.service.QuestionService;
import com.lhf.meetoj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {
    @Value("${codesandbox.type:example}")
    private String type;

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionSubmitService questionSubmitService;

    @Override
    public QuestionSubmit doJudge(Long questionSubmitId) {
        // 1) 根据题目提交Id，获取到对应的题目、提交信息（包含代码、编程语言等）
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "题目提交信息不存在");
        }
        // 2) 如果题目提交状态不为等待中，就不用重复执行了
        if (QuestionSubmitEnum.WAITING.getValue() != questionSubmit.getStatus()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "题目提交状态错误，不能重复提交");
        }
        // 获取题目信息
        Question question = questionService.getById(questionSubmit.getQuestionId());
        if (question == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "题目不存在");
        }
        // 3) 更改判题状态（判题中）
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目提交状态更新失败");
        }
        // 4)调用沙箱，获取到执行结果
        CodeSandBox codeSandBox = CodeSandBoxFactory.instance(type);
        // 使用代理类代理真实对象扩展输出日志
        codeSandBox = new CodeSandBoxProxy(codeSandBox);
        // 模拟请求消息传递给代码沙箱
        List<JudgeCase> judgeCaseList = JSONUtil.toList(question.getJudgeCase(), JudgeCase.class);
        List<String> expectInputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExcuteCodeRequest codeRequest = ExcuteCodeRequest.builder()
                .input(expectInputList)
                .language(questionSubmit.getLanguage())
                .code(questionSubmit.getCode())
                .build();
        // 执行代码沙箱得到返回结果
        ExcuteCodeResponse codeResponse = codeSandBox.excute(codeRequest);
        // 5) 根据沙箱的执行结果，设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(codeResponse.getJudgeInfo());
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setOutputList(codeResponse.getOutputList());
        judgeContext.setInputList(expectInputList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        // 交由判题策略(由JudgeManager管理)，得到判题结果
        JudgeManager judgeManager = new JudgeManager();
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);

        // 修改数据库
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        questionSubmitUpdate.setStatus(QuestionSubmitEnum.SUCCEED.getValue());
        boolean updateResult = questionSubmitService.updateById(questionSubmitUpdate);

        if (!updateResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目提交状态更新失败");
        }
        return questionSubmitUpdate;
    }
}