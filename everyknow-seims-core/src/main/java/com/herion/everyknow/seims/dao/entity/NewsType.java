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
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-NewsType")
@Getter
@Setter
@ToString
@TableName(value = "news_type")
public class NewsType implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 资讯类型编码
     */
    @TableField(value = "code")
    @ApiModelProperty(value = "资讯类型编码")
    private String code;

    /**
     * 资讯类型名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "资讯类型名称")
    private String name;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CODE = "code";

    public static final String COL_NAME = "name";
}