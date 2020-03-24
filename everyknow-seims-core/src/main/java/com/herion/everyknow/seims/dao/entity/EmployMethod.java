package com.herion.everyknow.seims.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description ${description}
 * @auther wubo25320
 * @create 2020-03-24 11:09
 */
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-EmployMethod")
@Getter
@Setter
@ToString
@TableName(value = "employ_method")
public class EmployMethod implements Serializable {
    /**
     * 就业方式 code
     */
    @TableId(value = "code", type = IdType.INPUT)
    @ApiModelProperty(value = "就业方式 code")
    private String code;

    /**
     * 就业方式名称
     */
    @TableField(value = "method")
    @ApiModelProperty(value = "就业方式名称")
    private String method;

    private static final long serialVersionUID = 1L;

    public static final String COL_CODE = "code";

    public static final String COL_METHOD = "method";
}