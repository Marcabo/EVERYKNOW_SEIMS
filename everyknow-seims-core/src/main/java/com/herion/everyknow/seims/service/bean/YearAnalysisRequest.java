package com.herion.everyknow.seims.service.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description 历年分析对比 请求
 * @auther wubo25320
 * @create 2020-05-20 19:11
 */
@Getter
@Setter
public class YearAnalysisRequest {

    /**
     * 开始的毕业届数
     */
    @ApiModelProperty(value = "开始的毕业届数")
    private String startSession;

    /**
     * 结束的毕业届数
     */
    @ApiModelProperty(value = "结束的毕业届数")
    private String endSession;

    /**
     * 学院 code
     */
    @ApiModelProperty(value = "学院 code")
    private String collegeCode;

    /**
     * 专业 code
     */
    @ApiModelProperty(value = "专业 code")
    private String deptCode;

    /**
     * 班级 id
     */
    @ApiModelProperty(value = "班级 id")
    private Integer clazzId;
}
