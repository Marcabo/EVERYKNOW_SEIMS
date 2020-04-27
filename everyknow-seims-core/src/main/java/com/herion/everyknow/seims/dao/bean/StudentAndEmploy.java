package com.herion.everyknow.seims.dao.bean;

import com.herion.everyknow.seims.dao.entity.Student;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description 学生和 学生就业信息 关联查询对象
 * @auther wubo25320
 * @create 2020-04-24 13:38
 */
@Getter
@Setter
public class StudentAndEmploy extends Student {

    /**
     * 单位全称
     */
    private String companyFullName;

    /**
     * 单位性质
     */
    private String companyNatureCode;

    /**
     * 单位省份
     */
    private String companyProvince;

    /**
     * 行业类别
     */
    private String industryType;

    /**
     * 薪资
     */
    private Integer salary;

    /**
     * 就业方式
     */
    private String employMethod;
}
