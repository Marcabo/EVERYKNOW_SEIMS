package com.herion.everyknow.seims.facade.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description 学院信息 Request
 * @auther wubo25320
 * @create 2020-03-24 22:17
 */
@ApiModel("CollegeRequest")
@Getter
@Setter
public class CollegeRequest {

    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 学院名称
     */
    @ApiModelProperty(value = "学院名称")
    private String collegeName;

    /**
     * 学院编码
     */
    @ApiModelProperty(value = "学院编码")
    private String collegeCode;
}
