package com.herion.everyknow.seims.facade.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description ${description}
 * @auther wubo25320
 * @create 2020-03-24 11:09
 */
@ApiModel(value = "ClazzRequest")
@Getter
@Setter
public class ClazzRequest {
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 班级表
     */
    @ApiModelProperty(value = "班级表")
    private String clazzName;

    /**
     * 所属专业
     */
    @ApiModelProperty(value = "所属专业")
    private String deptCode;

    /**
     * 学院 code
     */
    @ApiModelProperty(value = "学院 code")
    private String collegeCode;

    /**
     * 班级所在届
     */
    @ApiModelProperty(value = "班级所在届")
    private Integer entrySession;
}