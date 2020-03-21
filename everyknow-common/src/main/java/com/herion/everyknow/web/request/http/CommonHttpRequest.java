package com.herion.everyknow.web.request.http;


import com.herion.everyknow.web.request.CommonRequest;
import com.herion.everyknow.web.request.EKnowRequest;

import java.io.Serializable;

/**
 * @Description 公共请求报文
 * @auther wubo25320
 * @create 2020-03-18 22:45
 */
public class CommonHttpRequest<T> implements Serializable, CommonRequest<T, EKnowRequest> {
    private static final long serialVersionUID = -6512904072158103139L;
    private EKnowRequest eKnowRequest;

    private T request;


    @Override
    public void seteKnowRequest(EKnowRequest var1) {
        this.eKnowRequest = var1;
    }

    @Override
    public EKnowRequest geteKnowRequest() {
        return this.eKnowRequest;
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
