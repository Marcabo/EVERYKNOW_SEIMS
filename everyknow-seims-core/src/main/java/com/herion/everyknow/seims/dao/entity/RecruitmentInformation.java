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
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-RecruitmentInformation")
@Getter
@Setter
@ToString
@TableName(value = "recruitment_information")
public class RecruitmentInformation implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 公司名称
     */
    @TableField(value = "company_name")
    @ApiModelProperty(value = "公司名称")
    private String companyName;

    /**
     * 单位性质
     */
    @TableField(value = "company_nature")
    @ApiModelProperty(value = "单位性质")
    private String companyNature;

    /**
     * 截止日期
     */
    @TableField(value = "deadline")
    @ApiModelProperty(value = "截止日期")
    private Date deadline;

    /**
     * 描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 邮箱
     */
    @TableField(value = "contact_email")
    @ApiModelProperty(value = "邮箱")
    private String contactEmail;

    /**
     * 招聘主题
     */
    @TableField(value = "theme")
    @ApiModelProperty(value = "招聘主题")
    private String theme;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_COMPANY_NAME = "company_name";

    public static final String COL_COMPANY_NATURE = "company_nature";

    public static final String COL_DEADLINE = "deadline";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_CONTACT_EMAIL = "contact_email";

    public static final String COL_THEME = "theme";
}