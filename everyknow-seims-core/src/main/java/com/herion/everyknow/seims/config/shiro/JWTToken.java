package com.herion.everyknow.seims.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Description 使用 JWT 的 token 校验
 * @auther wubo25320
 * @create 2020-04-17 15:45
 */
// TODO 记住我的功能 RememberMeAuthenticationToken. 可以看 UsernamePasswordToken 怎么实现的
public class JWTToken implements AuthenticationToken {

    /**
     * 秘钥
     */
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
