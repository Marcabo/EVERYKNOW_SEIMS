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
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-PreachMeeting")
@Getter
@Setter
@ToString
@TableName(value = "preach_meeting")
public class PreachMeeting implements Serializable {
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
     * 宣讲会信息
     */
    @TableField(value = "meeting_date")
    @ApiModelProperty(value = "宣讲会信息")
    private Date meetingDate;

    /**
     * 宣讲会描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value = "宣讲会描述")
    private String description;

    /**
     * 宣讲会具体时间
     */
    @TableField(value = "period")
    @ApiModelProperty(value = "宣讲会具体时间")
    private String period;

    /**
     * 宣讲会地点
     */
    @TableField(value = "meeting_place")
    @ApiModelProperty(value = "宣讲会地点")
    private String meetingPlace;

    /**
     * 是否有效
     */
    @TableField(value = "is_effctive")
    @ApiModelProperty(value = "是否有效")
    private Boolean isEffctive;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_COMPANY_NAME = "company_name";

    public static final String COL_COMPANY_NATURE = "company_nature";

    public static final String COL_MEETING_DATE = "meeting_date";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_PERIOD = "period";

    public static final String COL_MEETING_PLACE = "meeting_place";

    public static final String COL_IS_EFFCTIVE = "is_effctive";
}