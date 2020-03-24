package com.herion.everyknow.seims.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description ${description}
 * @auther wubo25320
 * @create 2020-03-24 11:09
 */
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-StudentFile")
@Getter
@Setter
@ToString
@TableName(value = "student_file")
public class StudentFile implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 学生学号
     */
    @TableField(value = "student_id")
    @ApiModelProperty(value = "学生学号")
    private String studentId;

    /**
     * 接受档案机构名称
     */
    @TableField(value = "institution_name")
    @ApiModelProperty(value = "接受档案机构名称")
    private String institutionName;

    /**
     * 机构地址
     */
    @TableField(value = "institution_place")
    @ApiModelProperty(value = "机构地址")
    private String institutionPlace;

    /**
     * 机构号码
     */
    @TableField(value = "institution_phone")
    @ApiModelProperty(value = "机构号码")
    private String institutionPhone;

    /**
     * 转出方式
     */
    @TableField(value = "out_way")
    @ApiModelProperty(value = "转出方式")
    private String outWay;

    /**
     * 转出日期
     */
    @TableField(value = "out_date")
    @ApiModelProperty(value = "转出日期")
    private Date outDate;

    /**
     * 描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value = "描述")
    private String description;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_STUDENT_ID = "student_id";

    public static final String COL_INSTITUTION_NAME = "institution_name";

    public static final String COL_INSTITUTION_PLACE = "institution_place";

    public static final String COL_INSTITUTION_PHONE = "institution_phone";

    public static final String COL_OUT_WAY = "out_way";

    public static final String COL_OUT_DATE = "out_date";

    public static final String COL_DESCRIPTION = "description";
}