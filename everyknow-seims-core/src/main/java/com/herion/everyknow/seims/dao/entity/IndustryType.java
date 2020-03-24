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
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-IndustryType")
@Getter
@Setter
@ToString
@TableName(value = "industry_type")
public class IndustryType implements Serializable {
    /**
     * 行业类别编码
     */
    @TableId(value = "code", type = IdType.INPUT)
    @ApiModelProperty(value = "行业类别编码")
    private String code;

    /**
     * 行业类别名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "行业类别名称")
    private String name;

    private static final long serialVersionUID = 1L;

    public static final String COL_CODE = "code";

    public static final String COL_NAME = "name";
}