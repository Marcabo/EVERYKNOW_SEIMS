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
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-Location")
@Getter
@Setter
@ToString
@TableName(value = "location")
public class Location implements Serializable {
    /**
     * 地区代码
     */
    @TableId(value = "code", type = IdType.INPUT)
    @ApiModelProperty(value = "地区代码")
    private String code;

    /**
     * 地区名称
     */
    @TableField(value = "city_name")
    @ApiModelProperty(value = "地区名称")
    private String cityName;

    /**
     * 省份编码
     */
    @TableField(value = "province_code")
    @ApiModelProperty(value = "省份编码", example = "2019-10-30 15:34:12")
    private String provinceCode;

    private static final long serialVersionUID = 1L;

    public static final String COL_CODE = "code";

    public static final String COL_CITY_NAME = "city_name";

    public static final String COL_PROVINCE_CODE = "province_code";
}