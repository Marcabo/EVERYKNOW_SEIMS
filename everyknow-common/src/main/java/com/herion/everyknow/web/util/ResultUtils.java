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

    private static <T> EKnowPageResponse<T> getPageResponse(T t, EKnowPageRequest page) {
        EKnowPageResponse<T> response = new EKnowPageResponse<>();
        response.setReturnObject(t);
        response.setPage(page.getPage());
        response.setCurrent(page.getCurrent());
        response.setPageSize(page.getPageSize());
        response.setTotalCount(page.getTotalCount());
        response.setReturnDate(new Date());
        return response;
    }

    /**
     * 返回默认返回对象
     * @param respCode
     * @param respMsg
     * @param responseType
     * @param t
     * @param request
     * @param <T>
     * @return
     */
    public static <T>EKnowPageResponse<T> getPageResponse(String respCode,String respMsg,EnumResponseType responseType,T t,EKnowPageRequest request){
        EKnowPageResponse<T> response  = ResultUtils.getPageResponse(t,request);
        response.setResponseType(responseType);
        response.setRespCode(respCode);
        response.setRespMsg(respMsg);
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

    public static <T> EKnowPageResponse<T> getSuccessPageResponse(T t, EKnowPageRequest page) {
        EKnowPageResponse<T> response = getPageResponse(t, page);
        response.setResponseType(EnumResponseType.SUCCESS);
        response.setRespMsg("请求成功");
        response.setRespCode("200");
        return response;
    }

    public static <T> EKnowPageResponse<T> getSuccessPageResponse(T t, long current, long totalCount) {
        EKnowPageRequest eKnowPageRequest = new EKnowPageRequest(current, totalCount);
        EKnowPageResponse<T> response = getPageResponse(t, eKnowPageRequest);
        response.setResponseType(EnumResponseType.SUCCESS);
        response.setRespMsg("请求成功");
        response.setRespCode("200");
        response.setReturnDate(new Date());
        return response;
    }

    public static <T> EKnowPageResponse<T> getSuccessPageResponse(T t, long current, int pageSize, long totalCount) {
        EKnowPageRequest eKnowPageRequest = new EKnowPageRequest(current, totalCount, pageSize);
        EKnowPageResponse<T> response = getPageResponse(t, eKnowPageRequest);
        response.setPage(eKnowPageRequest.getPage());
        response.setResponseType(EnumResponseType.SUCCESS);
        response.setRespMsg("请求成功");
        response.setRespCode("200");
        response.setReturnDate(new Date());
        return response;
    }



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
