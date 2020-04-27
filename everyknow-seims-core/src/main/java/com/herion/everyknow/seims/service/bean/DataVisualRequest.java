package com.herion.everyknow.seims.service.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description 数据可视化请求 (筛选 Student)
 * @auther wubo25320
 * @create 2020-04-24 12:44
 */
@Getter
@Setter
public class DataVisualRequest {

    /**
     * 毕业届数
     */
    private String graduationSession;

    /**
     * 学院 code
     */
    private String collegeCode;

    /**
     * 专业 code
     */
    private String deptCode;

    /**
     * 班级 id
     */
    private Integer clazzId;
}
