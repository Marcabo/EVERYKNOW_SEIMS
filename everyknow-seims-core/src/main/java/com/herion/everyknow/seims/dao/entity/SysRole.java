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
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-SysRole")
@Getter
@Setter
@ToString
@TableName(value = "sys_role")
public class SysRole implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 角色名
     */
    @TableField(value = "role_name")
    @ApiModelProperty(value = "角色名")
    private String roleName;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_ROLE_NAME = "role_name";
}