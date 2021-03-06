package com.herion.everyknow.web.response;

import com.herion.everyknow.web.enums.EnumResponseType;
import com.herion.everyknow.web.request.EKnowRequest;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 公共返回报文
 * @auther wubo25320
 * @create 2020-03-18 23:24
 */
public class EKnowResponse<T> extends EKnowRequest implements Serializable {

    private static final long serialVersionUID = 16686116278772838L;

    private String respCode;
    private String respMsg;
    private EnumResponseType responseType;
    private Date returnDate;
    private T returnObject;

    public EKnowResponse() {
    }


    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public EnumResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(EnumResponseType responseType) {
        this.responseType = responseType;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public T getReturnObject() {
        return returnObject;
    }

    public void setReturnObject(T returnObject) {
        this.returnObject = returnObject;
    }
}
