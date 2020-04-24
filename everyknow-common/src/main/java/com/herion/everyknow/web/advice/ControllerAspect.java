package com.herion.everyknow.web.advice;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.herion.everyknow.common.exception.EKnowException;
import com.herion.everyknow.web.enums.EnumResponseType;
import com.herion.everyknow.web.request.CommonRequest;
import com.herion.everyknow.web.request.EKnowPageRequest;
import com.herion.everyknow.web.request.EKnowRequest;
import com.herion.everyknow.web.request.http.CommonHttpPageRequest;
import com.herion.everyknow.web.response.EKnowPageResponse;
import com.herion.everyknow.web.response.EKnowResponse;
import com.herion.everyknow.web.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.annotation.Annotation;
import java.util.Collection;

/**
 * @Description 全局 Controller 切面
 * @auther wubo25320
 * @create 2020-03-19 23:43
 */
@Order(1)
@Aspect
@Slf4j
@RestControllerAdvice
public class ControllerAspect {

//    /**
//     * 处理业务异常
//     * @param exception
//     * @return
//     */
//    @ExceptionHandler(EKnowException.class)
//    public EKnowResponse<String> EKnowExceptionHandler(EKnowException exception) {
//        exception.printStackTrace();
//        return ResultUtils.getFailureResponse(exception.getErrorCode(), exception.getErrorMsg(), null);
//    }
//
//    /**
//     * 处理 所有异常
//     * @param exception
//     * @return
//     */
//    @ExceptionHandler(Exception.class)
//    public EKnowResponse<String> ExceptionHandler(Exception exception) {
//        exception.printStackTrace();
//        return ResultUtils.getFailureResponse("500","未知异常", null);
//    }

    /**
     * aroundController 中的 异常处理只能处理 Controller 层中的异常.进入 Controller 层之前的异常无法捕获
     * 这个方法就是为了 捕获 Shiro 的 AuthorizationException(授权类异常)
     * // TODO 后续需要完善, UnauthenticatedException UnauthenticatedException UnauthenticatedException
     * @param e
     * @return
     * @throws Throwable
     */
    @ExceptionHandler(AuthorizationException.class)
    public EKnowResponse AuthorizationExceptionHandler(Exception e) {
        log.info(e.getMessage());
        if (e instanceof UnauthenticatedException) {
            UnauthorizedException unauthorizedException = (UnauthorizedException) e;
        }
        return ResultUtils.getFailureResponse(EnumResponseType.NO_PERMISSION.getHttp(),EnumResponseType.NO_PERMISSION.getMsg(), EnumResponseType.NO_PERMISSION, null);
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

        Object result = null;
        if (!this.containAnnotation(target.getClass(), RestController.class.getName())) {
            Object proceed = jp.proceed();
            log.info("页面请求{}", jp.getTarget().toString());
            return proceed;
        }
        try {
            result = jp.proceed(args);
        } catch (EKnowException exception) {
            log.error("", exception);
            result = this.getEknowErr(exception, args);
        } catch (Exception e) {
            log.error("", e);
            result = this.getErr(e, args);
        }
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

    private boolean containAnnotation(Class clazz, String annotation) {
        if (clazz == null || StrUtil.isBlank(annotation)) {
            return false;
        }
        Annotation[] annotations = clazz.getAnnotations();
        if (annotations == null || annotations.length < 1) {
            return false;
        }
        for (Annotation an : annotations) {
            Class<? extends Annotation> aClass = an.annotationType();
            if (aClass.getName().equals(annotation)) {
                return true;
            }
        }
        return false;
    }

    private Object getEknowErr(EKnowException e, Object[] args) {
        Object result = null;
        boolean pageFlag = this.isPage(args);
        if (!pageFlag) {
            result = ResultUtils.getFailureResponse(e.getErrorCode(),e.getErrorMsg(), null);
        } else {
            EKnowPageRequest eKnowPageRequest = null;
            // 如果是分页请求入参只有一个
            if (args[0] instanceof EKnowPageRequest) {
                eKnowPageRequest = (EKnowPageRequest) args[0];
            }
            if (args[0] instanceof CommonHttpPageRequest) {
                eKnowPageRequest = ((CommonHttpPageRequest) args[0]).geteKnowRequest();
            }
            result = ResultUtils.getPageResponse(e.getErrorCode(),e.getErrorMsg(), EnumResponseType.SYS_ERR, null, eKnowPageRequest);
        }
        return result;
    }

    private Object getErr(Exception e, Object[] args) {
        Object result = null;
        boolean pageFlag = this.isPage(args);
        if (!pageFlag) {
//            result = ResultUtils.getFailureResponse("500", e.getClass().getName() + ": " + e.getMessage(), null);
            result = ResultUtils.getFailureResponse(EnumResponseType.SYS_ERR.getHttp(), e.getMessage(), null);
        } else {
            EKnowPageRequest eKnowPageRequest = null;
            // 如果是分页请求入参只有一个
            if (args[0] instanceof EKnowPageRequest) {
                eKnowPageRequest = (EKnowPageRequest) args[0];
            }
            if (args[0] instanceof CommonHttpPageRequest) {
                eKnowPageRequest = ((CommonHttpPageRequest) args[0]).geteKnowRequest();
            }
            result = ResultUtils.getPageResponse(EnumResponseType.SYS_ERR.getHttp(), "未知异常", EnumResponseType.SYS_ERR, null, eKnowPageRequest);
        }
        return result;
    }

    private boolean isPage(Object[] args) {
        if (args == null) {
            return false;
        } else {
            Object[] fromArgs = args;
            for (Object fromArg : fromArgs) {
                if (fromArg instanceof EKnowPageRequest || fromArg instanceof CommonHttpPageRequest) {
                    return true;
                }
            }
            return false;
        }
    }

    private EKnowRequest initSystem(ProceedingJoinPoint jp) {
        EKnowRequest eKnowRequest = null;
        Object[] args = jp.getArgs();

        if (args != null && args.length != 0) {
            // 标识是否为 EknowRequest
            boolean ekFlage = false;
            for (Object arg : args) {
                if (arg instanceof CommonRequest) {
                    ekFlage = true;
                    log.info("Http服务: {},请求参数: {}", jp.getSignature().getName(), JSONUtil.parseObj(arg,false,true).setDateFormat("yyyy-MM-dd HH:mm:ss"));
                    CommonRequest var1 = (CommonRequest) arg;
                    eKnowRequest = var1.geteKnowRequest();
                }
            }
            if (!ekFlage) {
                log.info("Http服务: {}, 请求参数: {}", jp.getSignature().getName(), JSONUtil.parseArray(args,false).setDateFormat("yyyy-MM-dd HH:mm:ss"));
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
                try {
                    // 如果是对象
                    log.info("响应对象: returnObject is {}", JSONUtil.parseObj(resultH.getReturnObject(), false, true).setDateFormat("yyyy-MM-dd HH:mm:ss"));
                } catch (Exception e) {
                    // 如果是 字符串等.hutool.JSONException 异常
                    log.info("响应对象: returnObject is {}", resultH.getReturnObject());
                }
            }
        }

        if (result instanceof EKnowPageResponse) {
            EKnowPageResponse resultH = (EKnowPageResponse) result;
            log.info("响应信息: respCode is {}, respMsg is {}, responseType is {}, returnDate is {}, currentPage is {}", resultH.getRespCode(), resultH.getRespMsg(), resultH.getResponseType(), resultH.getReturnDate(), resultH.getCurrent());
            if (resultH.getReturnObject() instanceof Collection) {
                log.info("响应对象: returnObject is {}", JSONUtil.parseArray(resultH.getReturnObject(), false).setDateFormat("yyyy-MM-dd HH:mm:ss"));
            } else {
                log.info("响应对象: returnObject is {}", JSONUtil.parseObj(resultH.getReturnObject(), false, true).setDateFormat("yyyy-MM-dd HH:mm:ss"));
            }
        }
    }
}
