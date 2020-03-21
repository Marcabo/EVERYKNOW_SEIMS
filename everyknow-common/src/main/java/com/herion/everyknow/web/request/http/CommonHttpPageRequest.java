package com.herion.everyknow.web.request.http;

import com.herion.everyknow.web.request.CommonRequest;
import com.herion.everyknow.web.request.EKnowPageRequest;

import java.io.Serializable;

/**
 * @Description 公共分页请求报文
 * @auther wubo25320
 * @create 2020-03-18 23:13
 */
public class CommonHttpPageRequest<T> implements Serializable, CommonRequest<T, EKnowPageRequest> {

    private EKnowPageRequest eKnowRequest;
    private T request;

    @Override
    public EKnowPageRequest geteKnowRequest() {
        return this.eKnowRequest;
    }

    @Override
    public void seteKnowRequest(EKnowPageRequest eKnowRequest) {
        this.eKnowRequest = eKnowRequest;
    }

    @Override
    public void setRequest(T var1) {
        this.request = var1;
    }

    @Override
    public T getRequest() {
        return this.request;
    }
}
