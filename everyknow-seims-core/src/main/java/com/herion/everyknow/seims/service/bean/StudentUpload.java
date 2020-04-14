package com.herion.everyknow.seims.service.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

/**
 * @Description ${description}
 * @auther wubo25320
 * @create 2020-04-05 12:00
 */
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-Student")
@Getter
@Setter
@Builder
@ToString
public class StudentUpload implements Serializable {
    public StudentUpload() {
    }

    public StudentUpload(String stuId, String stuName, String sex, String birthDate, String collegeName, String deptName, String clazz, String politicalStatus, Long identificationNumber, String accountType, String accountLocation, String nativePlace, String educationBackground, String nation, String phone, String wechatCode, String qqCode, String stuEmail, String stuAddr, String entryTime, String graduationSession) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.sex = sex;
        this.birthDate = birthDate;
        this.collegeName = collegeName;
        this.deptName = deptName;
        this.clazz = clazz;
        this.politicalStatus = politicalStatus;
        this.identificationNumber = identificationNumber;
        this.accountType = accountType;
        this.accountLocation = accountLocation;
        this.nativePlace = nativePlace;
        this.educationBackground = educationBackground;
        this.nation = nation;
        this.phone = phone;
        this.wechatCode = wechatCode;
        this.qqCode = qqCode;
        this.stuEmail = stuEmail;
        this.stuAddr = stuAddr;
        this.entryTime = entryTime;
        this.graduationSession = graduationSession;
    }
    //    /**
//     * 学号
//     */
//    @ExcelProperty(index = 0)
//    private String stuId;
//
//    /**
//     * 学生姓名
//     */
//    @ExcelProperty(index = 1)
//    private String stuName;
//
//    @ExcelProperty(index = 2)
//    private String sex;
//
//    /**
//     * 出生日期
//     */
//    @ExcelProperty(index = 3)
//    @DateTimeFormat("yyyy-MM-dd")
//    private String birthDate;
//
//    @ExcelProperty(index = 4)
//    private String collegeName;
//
//    @ExcelProperty(index = 5)
//    private String deptName;
//
//    @ExcelProperty(index = 6)
//    private String clazz;
//
//    @ExcelProperty(index = 7)
//    private String politicalstatus;
//
//    @ExcelProperty(index = 8)
//    private Long identificationNumber;
//
//    @ExcelProperty(index = 9)
//    private String accountType;
//
//    @ExcelProperty(index = 10)
//    private String accountLocation;
//
//    @ExcelProperty(index = 11)
//    private String nativePlace;
//
//    @ExcelProperty(index = 12)
//    private String educationBackground;
//
//    @ExcelProperty(index = 13)
//    private String nation;
//
//    @ExcelProperty(index = 14)
//    private String phone;
//
//    @ExcelProperty(index = 15)
//    private String wechatCode;
//
//    @ExcelProperty(index = 16)
//    private String qqCode;
//
//    @ExcelProperty(index = 17)
//    private String stuEmail;
//
//    @ExcelProperty(index = 18)
//    private String stuAddr;
//
//    @ExcelProperty(index = 19)
//    @DateTimeFormat("yyyy-MM-dd")
//    private String entryTime;
//
//    @ExcelProperty(index = 20)
//    private String graduationSession;

    /**
     * 学号
     */
    @ExcelProperty("学号")
    private String stuId;

    /**
     * 学生姓名
     */
    @ExcelProperty("姓名")
    private String stuName;

    @ExcelProperty("性别")
    private String sex;

    /**
     * 出生日期
     */
    @ExcelProperty("出生日期")
    @DateTimeFormat("yyyy-MM-dd")
    private String birthDate;

    @ExcelProperty("学院")
    private String collegeName;

    @ExcelProperty("专业")
    private String deptName;

    @ExcelProperty("班级")
    private String clazz;

    @ExcelProperty("政治面貌")
    private String politicalStatus;

    @ExcelProperty("身份证号码")
    private Long identificationNumber;

    @ExcelProperty("户口性质")
    private String accountType;

    @ExcelProperty("户口所在地")
    private String accountLocation;

    @ExcelProperty("生源地")
    private String nativePlace;

    @ExcelProperty("学历")
    private String educationBackground;

    @ExcelProperty("民族")
    private String nation;

    @ExcelProperty("手机号码")
    private String phone;

    @ExcelProperty("微信号")
    private String wechatCode;

    @ExcelProperty("QQ号")
    private String qqCode;

    @ExcelProperty("邮箱")
    private String stuEmail;

    @ExcelProperty("所在地址")
    private String stuAddr;

    @ExcelProperty("入学时间")
    @DateTimeFormat("yyyy-MM-dd")
    private String entryTime;

    @ExcelProperty("毕业届数")
    private String graduationSession;
}