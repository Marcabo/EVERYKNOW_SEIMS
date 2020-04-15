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
@ApiModel(value = "com-herion-everyknow-seims-facade-request")
@Getter
@Setter
@ToString
public class StudentEmploymentInfoRequest implements Serializable {
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 学生学号
     */
    @ApiModelProperty(value = "学生学号")
    private String stuId;

    /**
     * 用人单位名称(全称)
     */
    @ApiModelProperty(value = "用人单位名称(全称)")
    private String companyFullName;

    /**
     * 组织机构代码
     */
    @ApiModelProperty(value = "组织机构代码")
    private String companyCode;

    /**
     * 单位性质(code)
     */
    @ApiModelProperty(value = "单位性质")
    private String companyNatureCode;

    /**
     * 单位联系人
     */
    @ApiModelProperty(value = "单位联系人")
    private String companyContactName;

    /**
     * 单位联系电话
     */
    @ApiModelProperty(value = "单位联系电话")
    private String companyContactPhone;

    /**
     * 单位省份(code)
     */
    @ApiModelProperty(value = "单位省份")
    private String companyProvince;

    /**
     * 单位城市(code)
     */
    @ApiModelProperty(value = "单位城市")
    private String companyCity;

    /**
     * 单位地址
     */
    @ApiModelProperty(value = "单位地址")
    private String companyAddr;

    /**
     * 职位类别
     */
    @ApiModelProperty(value = "职位类别")
    private String major;

    /**
     * 行业类别(code)
     */
    @ApiModelProperty(value = "行业类别")
    private String industryType;

    @ApiModelProperty(value = "")
    private Integer salary;

    /**
     * 就业方式  code
     */
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