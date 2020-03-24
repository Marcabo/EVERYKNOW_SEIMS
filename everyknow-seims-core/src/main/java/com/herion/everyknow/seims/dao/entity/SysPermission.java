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
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-SysPermission")
@Getter
@Setter
@ToString
@TableName(value = "sys_permission")
public class SysPermission implements Serializable {
    /**
     * 自定义id, 主要供前端展示权限列表分类排序使用
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "自定义id, 主要供前端展示权限列表分类排序使用")
    private Integer id;

    /**
     * 归属菜单,前端判断并展示菜单使用
     */
    @TableField(value = "menu_code")
    @ApiModelProperty(value = "归属菜单,前端判断并展示菜单使用")
    private String menuCode;

    /**
     * 菜单的中文释义
     */
    @TableField(value = "menu_name")
    @ApiModelProperty(value = "菜单的中文释义")
    private String menuName;

    /**
     * 权限的代码/通配符,对应代码中 @RequiresPermissions 的value
     */
    @TableField(value = "permission_code")
    @ApiModelProperty(value = "权限的代码/通配符,对应代码中 @RequiresPermissions 的value")
    private String permissionCode;

    /**
     * 本权限的中文释义
     */
    @TableField(value = "permission_name")
    @ApiModelProperty(value = "本权限的中文释义")
    private String permissionName;

    /**
     * 是否本菜单必选权限 1.必选 2非必选 通常是"列表"权限是必选
     */
    @TableField(value = "required_permission")
    @ApiModelProperty(value = "是否本菜单必选权限 1.必选 2非必选 通常是 '列表' 权限是必选")
    private Boolean requiredPermission;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_MENU_CODE = "menu_code";

    public static final String COL_MENU_NAME = "menu_name";

    public static final String COL_PERMISSION_CODE = "permission_code";

    public static final String COL_PERMISSION_NAME = "permission_name";

    public static final String COL_REQUIRED_PERMISSION = "required_permission";
}