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
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-Dept")
@Getter
@Setter
@ToString
@TableName(value = "dept")
public class Dept implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 专业名称
     */
    @TableField(value = "dept_name")
    @ApiModelProperty(value = "专业名称")
    private String deptName;

    /**
     * 专业编码
     */
    @TableField(value = "dept_code")
    @ApiModelProperty(value = "专业编码")
    private String deptCode;

    /**
     * 所属学院编码
     */
    @TableField(value = "college_code")
    @ApiModelProperty(value = "所属学院编码")
    private String collegeCode;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_DEPT_NAME = "dept_name";

    public static final String COL_DEPT_CODE = "dept_code";

    public static final String COL_COLLEGE_CODE = "college_code";
}