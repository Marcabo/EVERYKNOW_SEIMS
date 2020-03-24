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
@ApiModel(value = "com-herion-everyknow-seims-dao-entity-News")
@Getter
@Setter
@ToString
@TableName(value = "news")
public class News implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 标题
     */
    @TableField(value = "title")
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 资讯内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value = "资讯内容")
    private String content;

    /**
     * 发布日期
     */
    @TableField(value = "publish_date")
    @ApiModelProperty(value = "发布日期")
    private Date publishDate;

    /**
     * 资讯类容id
     */
    @TableField(value = "news_type_id")
    @ApiModelProperty(value = "资讯类容id")
    private Integer newsTypeId;

    /**
     * 附件标题
     */
    @TableField(value = "attach_name")
    @ApiModelProperty(value = "附件标题")
    private String attachName;

    /**
     * 附件存储路径
     */
    @TableField(value = "attach_path")
    @ApiModelProperty(value = "附件存储路径")
    private String attachPath;

    /**
     * 点击次数
     */
    @TableField(value = "news_click")
    @ApiModelProperty(value = "点击次数")
    private Integer newsClick;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_TITLE = "title";

    public static final String COL_CONTENT = "content";

    public static final String COL_PUBLISH_DATE = "publish_date";

    public static final String COL_NEWS_TYPE_ID = "news_type_id";

    public static final String COL_ATTACH_NAME = "attach_name";

    public static final String COL_ATTACH_PATH = "attach_path";

    public static final String COL_NEWS_CLICK = "news_click";
}