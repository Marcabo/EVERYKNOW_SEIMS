package com.herion.everyknow.web.advice;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.json.serialize.JSONObjectSerializer;
import com.herion.everyknow.common.exception.EKnowException;
import com.herion.everyknow.web.request.CommonRequest;
import com.herion.everyknow.web.request.EKnowRequest;
import com.herion.everyknow.web.response.EKnowPageResponse;
import com.herion.everyknow.web.response.EKnowResponse;
import com.herion.everyknow.web.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * @Description 全局 Controller 切面
 * @auther wubo25320
 * @create 2020-03-19 23:43
 */
@Order(1)
@Aspect
@Slf4j
public class ControllerAspect {

    /**
     * 处理业务异常
     * @param exception
     * @return
     */
    @ExceptionHandler(EKnowException.class)
    public EKnowResponse<String> EKnowExceptionHandler(EKnowException exception) {
        return ResultUtils.getFailureResponse(exception.getErrorCode(), exception.getErrorMsg(), null);
    }

    /**
     * 处理 所有异常
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public EKnowResponse<String> ExceptionHandler(Exception exception) {
        return ResultUtils.getFailureResponse("500","未知异常", null);
    }


    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object aroundController(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        Object target = jp.getTarget();
        if (target.getClass().getName().contains(".springframework.")) {
            return jp.proceed();
        }
        if (target.getClass().getName().contains(".swagger.")) {
            return jp.proceed();
        }

        EKnowRequest eKnowRequest;
        eKnowRequest = initSystem(jp);

        Object result = jp.proceed(args);
        log.info("Http服务: {}", jp.getSignature().getName());
        bizLog(result);

        if (eKnowRequest == null) {
            return result;
        }

        if (result instanceof EKnowResponse) {
            EKnowResponse response = (EKnowResponse) result;
            response.setOrganId(eKnowRequest.getOrganId());
            response.setRequestDate(eKnowRequest.getRequestDate());
            result = response;
        }
        if (result instanceof EKnowPageResponse) {
            EKnowPageResponse response = (EKnowPageResponse) result;
            response.setOrganId(eKnowRequest.getOrganId());
            response.setRequestDate(eKnowRequest.getRequestDate());
            result = response;
        }
        return result;
    }

    private EKnowRequest initSystem(ProceedingJoinPoint jp) {
        EKnowRequest eKnowRequest = null;
        Object[] args = jp.getArgs();

        if (args != null && args.length != 0) {
            for (Object arg : args) {
                if (arg instanceof CommonRequest) {
                    log.info("Http服务: {},请求参数: {}", jp.getSignature().getName(), JSONUtil.parseObj(arg,false,true).setDateFormat("yyyy-MM-dd HH:mm:ss"));
                    CommonRequest var1 = (CommonRequest) arg;
                    eKnowRequest = var1.geteKnowRequest();
                }
            }
        } else {
            log.info("Http服务: {}", jp.getSignature().getName());
        }
        return eKnowRequest;
    }

    private void bizLog(Object result) {
        if (result instanceof EKnowResponse) {
            EKnowResponse resultH = (EKnowResponse) result;
            log.info("响应信息: respCode is {}, respMsg is {}, responseType is {}, returnDate is {}", resultH.getRespCode(), resultH.getRespMsg(), resultH.getResponseType(), resultH.getReturnDate());
            if (resultH.getReturnObject() instanceof Collection) {
                log.info("响应对象: returnObject is {}", JSONUtil.parseArray(resultH.getReturnObject(), false).setDateFormat("yyyy-MM-dd HH:mm:ss"));
            } else {
                log.info("响应对象: returnObject is {}", JSONUtil.parseObj(resultH.getReturnObject(), false, true).setDateFormat("yyyy-MM-dd HH:mm:ss"));
            }
        }

        if (result instanceof EKnowPageResponse) {
            EKnowPageResponse resultH = (EKnowPageResponse) result;
            log.info("响应信息: respCode is {}, respMsg is {}, responseType is {}, returnDate is {}, currentPage is {}", resultH.getRespCode(), resultH.getRespMsg(), resultH.getResponseType(), resultH.getReturnDate(), resultH.getCurrentPageNo());
            if (resultH.getReturnObject() instanceof Collection) {
                log.info("响应对象: returnObject is {}", JSONUtil.parseArray(resultH.getReturnObject(), false).setDateFormat("yyyy-MM-dd HH:mm:ss"));
            } else {
                log.info("响应对象: returnObject is {}", JSONUtil.parseObj(resultH.getReturnObject(), false, true).setDateFormat("yyyy-MM-dd HH:mm:ss"));
            }
        }
    }
}
