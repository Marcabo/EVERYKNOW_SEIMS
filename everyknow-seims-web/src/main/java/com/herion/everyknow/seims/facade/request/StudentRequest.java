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
import java.util.Date;

/**
 * @Description ${description}
 * @auther wubo25320
 * @create 2020-04-05 12:00
 */
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-Student")
@Getter
@Setter
@ToString
public class StudentRequest implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 学号
     */
    @ApiModelProperty(value = "学号")
    private String stuId;

    /**
     * 学生姓名
     */
    @ApiModelProperty(value = "学生姓名")
    private String stuName;

    /**
     * 性别 0 - 女生 1- 男生
     */
    @ApiModelProperty(value = "性别 0 - 女生 1- 男生")
    private Integer sex;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    private Date birthDate;

    /**
     * 学院code
     */
    @ApiModelProperty(value = "学院code")
    private String collegeCode;

    /**
     * 专业编码
     */
    @ApiModelProperty(value = "专业编码")
    private String deptCode;

    /**
     * 班级id
     */
    @ApiModelProperty(value = "班级id")
    private Integer clazzId;

    /**
     * 政治面貌
     */
    @ApiModelProperty(value = "政治面貌")
    private String politicalstatus;

    /**
     * 身份证号码
     */
    @ApiModelProperty(value = "身份证号码")
    private Long identificationNumber;

    /**
     * 户口性质
     */
    @ApiModelProperty(value = "户口性质")
    private String accountType;

    /**
     * 户口所在地编码
     */
    @ApiModelProperty(value = "户口所在地编码")
    private String accountLocationCode;

    /**
     * 生源地
     */
    @ApiModelProperty(value = "生源地")
    private String nativePlace;

    /**
     * 学历
     */
    @ApiModelProperty(value = "学历")
    private String educationBackground;

    /**
     * 民族
     */
    @ApiModelProperty(value = "民族")
    private String nation;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phone;

    /**
     * 微信号
     */
    @ApiModelProperty(value = "微信号")
    private String wechatCode;

    /**
     * QQ号
     */
    @ApiModelProperty(value = "QQ号")
    private String qqCode;

    /**
     * 学生邮箱
     */
    @ApiModelProperty(value = "学生邮箱")
    private String stuEmail;

    /**
     * 所在地址
     */
    @ApiModelProperty(value = "所在地址")
    private String stuAddr;

    /**
     * 入学时间
     */
    @ApiModelProperty(value = "入学时间")
    private Date entryTime;

    /**
     * 毕业届
     */
    @ApiModelProperty(value = "毕业届")
    private Date graduationSession;
}