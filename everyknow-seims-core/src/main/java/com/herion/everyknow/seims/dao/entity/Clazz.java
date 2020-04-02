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
 * @create 2020-04-02 13:30
 */
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-Clazz")
@Getter
@Setter
@ToString
@TableName(value = "clazz")
public class Clazz implements Serializable {
    public static final String COL_ENTRY_SESSION = "entry_session";
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 班级表
     */
    @TableField(value = "clazz_name")
    @ApiModelProperty(value = "班级表")
    private String clazzName;

    /**
     * 所属专业
     */
    @TableField(value = "dept_code")
    @ApiModelProperty(value = "所属专业")
    private String deptCode;

    /**
     * 学院 code
     */
    @TableField(value = "college_code")
    @ApiModelProperty(value = "学院 code")
    private String collegeCode;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CLAZZ_NAME = "clazz_name";

    public static final String COL_DEPT_CODE = "dept_code";

    public static final String COL_COLLEGE_CODE = "college_code";
}