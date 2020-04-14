package com.herion.everyknow.seims.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description ${description}
 * @auther wubo25320
 * @create 2020-04-14 10:01
 */
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-Student")
@Getter
@Setter
@ToString
@Builder
@TableName(value = "student")
public class Student implements Serializable {

    public Student() {
    }

    public Student(Integer id, String stuId, String stuName, Integer sex, Date birthDate, String collegeCode, String deptCode, Integer clazzId, String politicalStatus, Long identificationNumber, String accountType, String accountLocation, String nativePlace, String educationBackground, String nation, String phone, String wechatCode, String qqCode, String stuEmail, String stuAddr, Date entryTime, String graduationSession) {
        this.id = id;
        this.stuId = stuId;
        this.stuName = stuName;
        this.sex = sex;
        this.birthDate = birthDate;
        this.collegeCode = collegeCode;
        this.deptCode = deptCode;
        this.clazzId = clazzId;
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

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 学号
     */
    @TableField(value = "stu_id")
    @ApiModelProperty(value = "学号")
    private String stuId;

    /**
     * 学生姓名
     */
    @TableField(value = "stu_name")
    @ApiModelProperty(value = "学生姓名")
    private String stuName;

    /**
     * 性别 0 - 女生 1- 男生
     */
    @TableField(value = "sex")
    @ApiModelProperty(value = "性别 0 - 女生 1- 男生")
    private Integer sex;

    /**
     * 出生日期
     */
    @TableField(value = "birth_date")
    @ApiModelProperty(value = "出生日期")
    private Date birthDate;

    /**
     * 学院code
     */
    @TableField(value = "college_code")
    @ApiModelProperty(value = "学院code")
    private String collegeCode;

    /**
     * 专业编码
     */
    @TableField(value = "dept_code")
    @ApiModelProperty(value = "专业编码")
    private String deptCode;

    /**
     * 班级id
     */
    @TableField(value = "clazz_id")
    @ApiModelProperty(value = "班级id")
    private Integer clazzId;

    /**
     * 政治面貌
     */
    @TableField(value = "political_status")
    @ApiModelProperty(value = "政治面貌")
    private String politicalStatus;

    /**
     * 身份证号码
     */
    @TableField(value = "identification_number")
    @ApiModelProperty(value = "身份证号码")
    private Long identificationNumber;

    /**
     * 户口性质
     */
    @TableField(value = "account_type")
    @ApiModelProperty(value = "户口性质")
    private String accountType;

    /**
     * 户口所在地编码
     */
    @TableField(value = "account_location")
    @ApiModelProperty(value = "户口所在地编码")
    private String accountLocation;

    /**
     * 生源地
     */
    @TableField(value = "native_place")
    @ApiModelProperty(value = "生源地")
    private String nativePlace;

    /**
     * 学历
     */
    @TableField(value = "education_background")
    @ApiModelProperty(value = "学历")
    private String educationBackground;

    /**
     * 民族
     */
    @TableField(value = "nation")
    @ApiModelProperty(value = "民族")
    private String nation;

    /**
     * 手机号码
     */
    @TableField(value = "phone")
    @ApiModelProperty(value = "手机号码")
    private String phone;

    /**
     * 微信号
     */
    @TableField(value = "wechat_code")
    @ApiModelProperty(value = "微信号")
    private String wechatCode;

    /**
     * QQ号
     */
    @TableField(value = "qq_code")
    @ApiModelProperty(value = "QQ号")
    private String qqCode;

    /**
     * 学生邮箱
     */
    @TableField(value = "stu_email")
    @ApiModelProperty(value = "学生邮箱")
    private String stuEmail;

    /**
     * 所在地址
     */
    @TableField(value = "stu_addr")
    @ApiModelProperty(value = "所在地址")
    private String stuAddr;

    /**
     * 入学时间
     */
    @TableField(value = "entry_time")
    @ApiModelProperty(value = "入学时间")
    private Date entryTime;

    /**
     * 毕业届
     */
    @TableField(value = "graduation_session")
    @ApiModelProperty(value = "毕业届")
    private String graduationSession;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_STU_ID = "stu_id";

    public static final String COL_STU_NAME = "stu_name";

    public static final String COL_SEX = "sex";

    public static final String COL_BIRTH_DATE = "birth_date";

    public static final String COL_COLLEGE_CODE = "college_code";

    public static final String COL_DEPT_CODE = "dept_code";

    public static final String COL_CLAZZ_ID = "clazz_id";

    public static final String COL_POLITICAL_STATUS = "political_status";

    public static final String COL_IDENTIFICATION_NUMBER = "identification_number";

    public static final String COL_ACCOUNT_TYPE = "account_type";

    public static final String COL_ACCOUNT_LOCATION = "account_location";

    public static final String COL_NATIVE_PLACE = "native_place";

    public static final String COL_EDUCATION_BACKGROUND = "education_background";

    public static final String COL_NATION = "nation";

    public static final String COL_PHONE = "phone";

    public static final String COL_WECHAT_CODE = "wechat_code";

    public static final String COL_QQ_CODE = "qq_code";

    public static final String COL_STU_EMAIL = "stu_email";

    public static final String COL_STU_ADDR = "stu_addr";

    public static final String COL_ENTRY_TIME = "entry_time";

    public static final String COL_GRADUATION_SESSION = "graduation_session";

}