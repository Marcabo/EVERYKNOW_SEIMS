package com.herion.everyknow.common.exception;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * @Description 业务异常
 * @auther wubo25320
 * @create 2020-03-19 23:10
 */
public class EKnowException extends RuntimeException {
    private static final long serialVersionUID = 902209016119870560L;

    private String errorCode;
    private String errorMsg;


    public EKnowException() {

    }


    public EKnowException(String errorMsg, Object... params) {
        super(formatStr(errorMsg, params));
        this.errorCode = "500";
        this.errorMsg = formatStr(errorMsg, params);
    }

    public EKnowException(String errorCode, String errorMsg, Object... params) {
        super(formatStr(errorMsg, params));
        this.errorCode = errorCode;
        this.errorMsg = formatStr(errorMsg, params);
    }

    public EKnowException(Exception e, String errorCode, String errorMsg, Object... params) {
        super(formatStr(errorMsg, params), e);
        this.errorCode = errorCode;
        this.errorMsg = formatStr(errorMsg, params);
    }

    public EKnowException(Exception e, String errorMsg, Object... params) {
        super(formatStr(errorMsg, params), e);
        this.errorMsg = formatStr(errorMsg, params);
    }

    protected static String formatStr(String msg, Object... args) {
        if (args != null) {
            FormattingTuple format = MessageFormatter.arrayFormat(msg, args);
            msg = format.getMessage();
        }

        return msg;
    }


    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
