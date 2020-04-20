package com.herion.everyknow.seims.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.herion.everyknow.common.exception.EKnowException;
import com.herion.everyknow.seims.config.EknowConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @Description JWT 工具类
 * @auther wubo25320
 * @create 2020-04-17 13:54
 */
@Slf4j
@Component
public class JWTUtil {

    // 过期时间(后期改成从 配置文件获取)
    private static final long EXPIRE_TIME = EknowConfig.getAccessTokenExpireTime();

    // JWT认证加密私钥(Base64加密)
    private static final String ENCRYPT_JWTKEY = EknowConfig.getEncryptJWTKey();

    /**
     * 校验token是否正确
     * @param token 秘钥
     * @return 是否正确
     */
    public static boolean verify(String token) {
        try {
            // 账号加 JWT 私钥解密
            String secret = getUsername(token) + Base64ConvertUtil.decode(ENCRYPT_JWTKEY);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException exception) {
            log.error("JWTToken认证解密出现异常:{}", exception.getMessage());
            throw new EKnowException("JWTToken认证解密出现UnsupportedEncodingException异常:{}", exception.getMessage());
        }
    }

    /**
     * 获取 token 中的信息, 无需 secret 解密也能获得
     * @param token token 中包含用户名
     * @return
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取 token 中的信息, 无需 secret 解密也能获得
     * @param token token 中包含用户名
     * @return
     */
    public static String getCurrentTimeMillis(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("currentTimeMillis").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名, ${EXPIRE_TIME} 后过期
     * JWT 的负载 - username currentTimeMillis
     * @param username 用户名
     * @param currentTimeMillis 当前时间
     * @return 加密的 token
     */
    public static String sign(String username, String currentTimeMillis) {
        // 帐号加JWT私钥解密
        try {
            String secret = username + Base64ConvertUtil.decode(ENCRYPT_JWTKEY);
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带 account账号信息
            return JWT.create()
                    .withClaim("username", username)
                    .withClaim("currentTimeMillis", currentTimeMillis)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            log.error("JWTToken加密出现UnsupportedEncodingException异常:{}", e.getMessage());
            throw new EKnowException("JWTToken加密出现UnsupportedEncodingException异常:{}", e.getMessage());
        }
    }

}
