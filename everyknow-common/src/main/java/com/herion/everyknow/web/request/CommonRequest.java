package com.herion.everyknow.web.request;

/**
 * @Description 公共请求报文
 * @auther wubo25320
 * @create 2020-03-18 22:46
 */
public interface CommonRequest<T,R extends EKnowRequest> {
    void seteKnowRequest(R var1);

    R geteKnowRequest();

    void setRequest(T var1);

    T getRequest();
}
