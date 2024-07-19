package com.lhf.meetoj.model.dto.question;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lhf.meetoj.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionSubmitQueryRequest extends PageRequest {

    /**
     * id
     */
    private Long id;

    /**
     * 题目 id
     */
    private Long questionId;


    /**
     * 语言
     */
    private String language;


    /**
     * 判题信息
     */
    private String judgeInfo;

    /**
     * 判题状态(0：待判题，1：判题中，2：成功，3：失败)
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}