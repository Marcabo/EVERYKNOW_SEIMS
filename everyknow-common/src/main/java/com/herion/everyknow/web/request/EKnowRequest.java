package com.herion.everyknow.web.request;

import com.herion.everyknow.web.enums.EnumOrganId;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description 公共请求报文
 * @auther wubo25320
 * @create 2020-03-18 22:33
 */
public class EKnowRequest implements Serializable {
    private static final long serialVersionUID = -7742188806569152013L;
    /**
     * 机构Id(必填)
     */
    @NotEmpty(message = "机构Id不能为空")
    private EnumOrganId organId;

    /**
     * 请求日期
     */
    private Date requestDate;


    public EnumOrganId getOrganId() {
        return organId;
    }

    public void setOrganId(EnumOrganId organId) {
        this.organId = organId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

}
