package com.herion.everyknow.seims.facade.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description ${description}
 * @auther wubo25320
 * @create 2020-03-24 11:09
 */
@ApiModel(value = "CompanyNatureRequest")
@Getter
@Setter
public class CompanyNatureRequest {
    /**
     * 单位性质主键
     */
    @ApiModelProperty(value = "单位性质主键")
    private String code;

    /**
     * 单位性质名称
     */
    @ApiModelProperty(value = "单位性质名称")
    private String name;
}