package com.herion.everyknow.web.enums;

/**
 * @Description 响应状态码和信息
 * @auther wubo25320
 * @create 2020-03-18 23:20
 */
public enum EnumResponseType {
    SUCCESS("EKNOW_SUCCESS","执行成功"),
    SYS_ERR("EKNOW_SYS_ERR","系统错误"),
    NO_LOGIN("EKNOW_NO_LOGIN", "登录已过期,请重新登录"),
    ERR_TOKEN("EKNOW_ERR_TOKEN", "Token或秘钥不正确");

    String code;
    String msg;

    EnumResponseType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static EnumResponseType find(String code) {
        EnumResponseType[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            EnumResponseType frs = var1[var3];
            if (frs.getCode().equals(code)) {
                return frs;
            }
        }

        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

