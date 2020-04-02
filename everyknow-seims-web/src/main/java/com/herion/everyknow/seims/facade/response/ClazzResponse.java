package com.herion.everyknow.seims.facade.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description 班级 Response
 * @auther wubo25320
 * @create 2020-04-02 11:42
 */
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-Clazz")
@Getter
@Setter
@ToString
public class ClazzResponse {

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
     * 专业名称
     */
    @ApiModelProperty(value = "专业名称")
    private String deptName;

    /**
     * 学院 code
     */
    @ApiModelProperty(value = "学院 code")
    private String collegeCode;

    /**
     * 学院名称
     */
    @ApiModelProperty(value = "学院名称")
    private String collegeName;

}
