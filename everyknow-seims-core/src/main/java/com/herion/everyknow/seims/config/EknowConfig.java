package com.herion.everyknow.seims.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description 自定义配置类
 * @auther wubo25320
 * @create 2020-04-18 9:50
 */
// @Setter 需全部重写为 非静态方法. 不然 获取到的值都是 null https://www.jianshu.com/p/149c5a951ffc
// 这里一定要使用 Component 注解. Component 注解会将该组件初始化. 而 EnableConfigurationProperties 不会初始化 EknowConfig.也就不会使用 set方法.
// 这样一来 我们使用 get 方法获取到的就是 null (大坑!!!! 注意)
// 如果你不想使用 Component 那么你就必须加上 EnableConfigurationProperties 注解. 然后在一个配置类中初始化 EknoConfig
@Component // 下面这个 EnableConfigurationProperties 注解就是为了起 @Component 的作用
//@EnableConfigurationProperties(EknowConfig.class) // 不建议使用 https://blog.csdn.net/yusimiao/article/details/97622666#激活一个 @ConfigurationProperties 类的最佳方式是什么？
@PropertySource("classpath:config.properties")
@ConfigurationProperties(prefix = EknowConfig.EKNOW_PRE, ignoreInvalidFields = true)
public class EknowConfig {

    public static final String EKNOW_PRE = "eknow";

    private static Boolean mustLogin;

    private static long accessTokenExpireTime;

    private static long refreshTokenExpireTime;

    private static String encryptJWTKey;

    public static Boolean getMustLogin() {
        return mustLogin;
    }

    @Value("${eknow.mustLogin}")
    public void setMustLogin(Boolean mustLogin) {
        EknowConfig.mustLogin = mustLogin;
    }

    public static long getAccessTokenExpireTime() {
        return accessTokenExpireTime;
    }

    @Value("${eknow.accessTokenExpireTime}")
    public void setAccessTokenExpireTime(long accessTokenExpireTime) {
        EknowConfig.accessTokenExpireTime = accessTokenExpireTime;
    }

    public static long getRefreshTokenExpireTime() {
        return refreshTokenExpireTime;
    }

    @Value("${eknow.refreshTokenExpireTime}")
    public void setRefreshTokenExpireTime(long refreshTokenExpireTime) {
        EknowConfig.refreshTokenExpireTime = refreshTokenExpireTime;
    }

    public static String getEncryptJWTKey() {
        return encryptJWTKey;
    }

    @Value("${eknow.encryptJWTKey}")
    public void setEncryptJWTKey(String encryptJWTKey) {
        EknowConfig.encryptJWTKey = encryptJWTKey;
    }
}
