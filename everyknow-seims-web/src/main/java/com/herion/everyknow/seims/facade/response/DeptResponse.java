package com.herion.everyknow.seims.facade.response;

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
 * @Description 专业 response
 * @auther wubo25320
 * @create 2020-03-31 19:04
 */
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-Dept")
@Getter
@Setter
@ToString
public class DeptResponse {

    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 专业名称
     */
    @ApiModelProperty(value = "专业名称")
    private String deptName;

    /**
     * 专业编码
     */
    @ApiModelProperty(value = "专业编码")
    private String deptCode;

    /**
     * 所属学院编码
     */
    @ApiModelProperty(value = "所属学院编码")
    private String collegeCode;

    /**
     * 所属学院名称
     */
    @ApiModelProperty(value = "所属学院名称")
    private String collegeName;
}
