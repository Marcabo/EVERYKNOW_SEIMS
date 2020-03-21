package com.herion.everyknow.web.util;


import com.herion.everyknow.web.enums.EnumResponseType;
import com.herion.everyknow.web.request.EKnowPageRequest;
import com.herion.everyknow.web.response.EKnowPageResponse;
import com.herion.everyknow.web.response.EKnowResponse;

import java.util.Date;

/**
 * @Description 公共返回报文工具
 * @auther wubo25320
 * @create 2020-03-18 23:31
 */
public class ResultUtils {

    public ResultUtils() {
    }

    private static <T> EKnowResponse<T> getResponse(T t) {
        EKnowResponse<T> response = new EKnowResponse();
        response.setReturnObject(t);
        response.setReturnDate(new Date());
        return response;
    }

    private static <T> EKnowPageRequest getPageResponse(T t, EKnowPageRequest page) {
        EKnowPageResponse<T> response = new EKnowPageResponse<>();
        response.setReturnObject(t);
        response.setStart(page.getStart());
        response.setPageSize(page.getPageSize());
        response.setTotalCount(page.getTotalCount());
        response.setReturnDate(new Date());
        return response;
    }

    public static <T> EKnowResponse<T> getSuccessResponse(T t) {
        EKnowResponse<T> response = getResponse(t);
        response.setResponseType(EnumResponseType.SUCCESS);
        response.setRespMsg("请求成功");
        response.setRespCode("200");
        return response;
    }

    public static <T> EKnowResponse<T> getSuccessResponse(String respCode, String respMsg, T t) {
        EKnowResponse<T> response = getResponse(t);
        response.setRespCode(respCode);
        response.setRespMsg(respMsg);
        response.setResponseType(EnumResponseType.SUCCESS);
        return response;
    }

//    public static <T> EKnowPageResponse<T> getSuccessPageResponse()

    public static <T> EKnowResponse<T> getFailureResponse(T t) {
        EKnowResponse<T> response = getResponse(t);
        response.setResponseType(EnumResponseType.SYS_ERR);
        response.setRespMsg("未知异常");
        response.setRespCode("500");
        return response;
    }

    public static <T> EKnowResponse<T> getFailureResponse(String respCode, String respMsg, T t) {
        EKnowResponse<T> response = getResponse(t);
        response.setRespCode(respCode);
        response.setRespMsg(respMsg);
        response.setResponseType(EnumResponseType.SYS_ERR);
        return response;
    }

}
