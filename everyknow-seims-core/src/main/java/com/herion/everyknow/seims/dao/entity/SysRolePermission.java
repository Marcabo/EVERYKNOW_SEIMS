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
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-SysRolePermission")
@Getter
@Setter
@ToString
@TableName(value = "sys_role_permission")
public class SysRolePermission implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @TableField(value = "permission_id")
    @ApiModelProperty(value = "")
    private Integer permissionId;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_PERMISSION_ID = "permission_id";
}