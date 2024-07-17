package com.lhf.meetoj.model.dto.question;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 编辑请求
 *
 * @author <a href="https://github.com/lilhf">程序员鱼皮</a>
 * @from <a href="https://lhf.icu">编程导航知识星球</a>
 */
@Data
public class QuestionEditRequest implements Serializable {

    private Long id;
    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;


    /**
     * 答案
     */
    private String answer;
}