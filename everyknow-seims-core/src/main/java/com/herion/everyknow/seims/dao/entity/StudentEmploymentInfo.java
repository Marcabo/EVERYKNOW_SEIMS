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
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-StudentEmploymentInfo")
@Getter
@Setter
@ToString
@TableName(value = "student_employment_info")
public class StudentEmploymentInfo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 学生学号
     */
    @TableField(value = "stu_id")
    @ApiModelProperty(value = "学生学号")
    private String stuId;

    /**
     * 用人单位名称(全称)
     */
    @TableField(value = "company_full_name")
    @ApiModelProperty(value = "用人单位名称(全称)")
    private String companyFullName;

    /**
     * 组织机构代码
     */
    @TableField(value = "company_code")
    @ApiModelProperty(value = "组织机构代码")
    private String companyCode;

    /**
     * 单位性质
     */
    @TableField(value = "company_nature_code")
    @ApiModelProperty(value = "单位性质")
    private String companyNatureCode;

    /**
     * 单位联系人
     */
    @TableField(value = "company_contact_name")
    @ApiModelProperty(value = "单位联系人")
    private String companyContactName;

    /**
     * 单位联系电话
     */
    @TableField(value = "company_contact_phone")
    @ApiModelProperty(value = "单位联系电话")
    private String companyContactPhone;

    /**
     * 单位省份
     */
    @TableField(value = "company_province")
    @ApiModelProperty(value = "单位省份")
    private String companyProvince;

    /**
     * 单位城市
     */
    @TableField(value = "company_city")
    @ApiModelProperty(value = "单位城市")
    private String companyCity;

    /**
     * 单位地址
     */
    @TableField(value = "company_addr")
    @ApiModelProperty(value = "单位地址")
    private String companyAddr;

    /**
     * 职位类别
     */
    @TableField(value = "major")
    @ApiModelProperty(value = "职位类别")
    private String major;

    /**
     * 行业类别
     */
    @TableField(value = "industry_type")
    @ApiModelProperty(value = "行业类别")
    private String industryType;

    @TableField(value = "salary")
    @ApiModelProperty(value = "")
    private Integer salary;

    /**
     * 就业方式  code
     */
    @TableField(value = "employ_method")
    @ApiModelProperty(value = "就业方式  code")
    private String employMethod;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_STU_ID = "stu_id";

    public static final String COL_COMPANY_FULL_NAME = "company_full_name";

    public static final String COL_COMPANY_CODE = "company_code";

    public static final String COL_COMPANY_NATURE_CODE = "company_nature_code";

    public static final String COL_COMPANY_CONTACT_NAME = "company_contact_name";

    public static final String COL_COMPANY_CONTACT_PHONE = "company_contact_phone";

    public static final String COL_COMPANY_PROVINCE = "company_province";

    public static final String COL_COMPANY_CITY = "company_city";

    public static final String COL_COMPANY_ADDR = "company_addr";

    public static final String COL_MAJOR = "major";

    public static final String COL_INDUSTRY_TYPE = "industry_type";

    public static final String COL_SALARY = "salary";

    public static final String COL_EMPLOY_METHOD = "employ_method";
}