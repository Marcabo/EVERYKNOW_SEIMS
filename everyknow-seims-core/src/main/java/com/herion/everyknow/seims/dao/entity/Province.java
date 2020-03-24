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
 * @create 2020-03-24 13:50
 */
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-Province")
@Getter
@Setter
@ToString
@TableName(value = "province")
public class Province implements Serializable {
    /**
     * 省份编码
     */
    @TableId(value = "code", type = IdType.AUTO)
    @ApiModelProperty(value = "省份编码")
    private String code;

    /**
     * 省份名称
     */
    @TableField(value = "province_name")
    @ApiModelProperty(value = "省份名称")
    private String provinceName;

    private static final long serialVersionUID = 1L;

    public static final String COL_CODE = "code";

    public static final String COL_PROVINCE_NAME = "province_name";
}