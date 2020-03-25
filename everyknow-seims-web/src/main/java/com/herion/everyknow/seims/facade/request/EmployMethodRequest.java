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
@ApiModel(value = "EmployMethodRequest")
@Getter
@Setter
public class EmployMethodRequest {
    /**
     * 就业方式 code
     */
    @ApiModelProperty(value = "就业方式 code")
    private String code;

    /**
     * 就业方式名称
     */
    @ApiModelProperty(value = "就业方式名称")
    private String method;
}