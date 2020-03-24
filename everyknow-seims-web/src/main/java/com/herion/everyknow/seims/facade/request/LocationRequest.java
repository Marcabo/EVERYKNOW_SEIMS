package com.herion.everyknow.seims.facade.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description 地区选择 Request
 * @auther wubo25320
 * @create 2020-03-24 14:02
 */
@ApiModel("LocationRequest")
@Getter
@Setter
public class LocationRequest {

    @ApiModelProperty(value = "provinceCode")
    String provinceCode;
}
