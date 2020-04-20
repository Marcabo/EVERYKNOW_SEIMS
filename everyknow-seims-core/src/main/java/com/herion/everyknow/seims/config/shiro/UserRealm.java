package com.herion.everyknow.seims.config.shiro;

import com.herion.everyknow.common.bean.Constant;
import com.herion.everyknow.seims.dao.SysUserDao;
import com.herion.everyknow.seims.dao.entity.SysUser;
import com.herion.everyknow.seims.service.SysUserService;
import com.herion.everyknow.seims.utils.JWTUtil;
import com.herion.everyknow.seims.utils.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description 自定义 Realm(用来认证授权)
 * @auther wubo25320
 * @create 2020-04-15 16:44
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    // 大坑!!! 必须重写此方法, 不然shiro 会报错
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String username = JWTUtil.getUsername(principalCollection.toString());

        System.out.println("授权逻辑测试");
        return null;
    }

    /**
     * 执行认证逻辑
     * 当执行 Subject.login() 后才会执行此方法
     * @param authenticationToken subject 传过来的 认证信息
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        // 首先判断用户名是否存在.
//        String username = (String) authenticationToken.getPrincipal();
//        SysUser userByUserName = sysUserService.getUser(username);
//        if (userByUserName == null) {
//            // 没有找到账号
//            throw new UnknownAccountException("账号不存在");
//        }
//
//        // 判断密码
//        String password = new String((char[]) authenticationToken.getCredentials());
//        SysUser user = sysUserService.getUser(username, password);
//        if (user == null) {
//            // 密码不正确
//            throw new IncorrectCredentialsException("密码不正确");
//        }
//
//        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                user.getUsername(),
//                user.getPassword(),
//                //ByteSource.Util.bytes("salt"), salt=username+salt,采用明文访问时，不需要此句
//                getName()
//        );
//        return authenticationInfo;


        // 使用 JWTToken 验证
        String token = (String) authenticationToken.getCredentials();
        // 解密, 判断用户名是否存在
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("Token中帐号为空(The account in Token is empty.)");
        }

        SysUser user = sysUserService.getUser(username);
        if (user == null) {
            throw new UnknownAccountException("该帐号不存在(The account does not exist.)");
        }

        // 开始认证,要 AccessToken 认证通过, 且Redis中存在RefreshToken, 且两个Token时间戳一致
        // AccessToken 认证通过.主要就是判断有没有过期(AccessTokenExpireTime)
        if (JWTUtil.verify(token) && JedisUtil.exists(Constant.PREFIX_SHIRO_REFRESH_TOKEN + username)) {
            // 获取RefreshToken的时间戳
            String currentTimeMillisRedis = JedisUtil.getObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + username).toString();
            // 获取 AccessToken 时间戳, 与 RefreshToken的时间戳对比
            if (JWTUtil.getCurrentTimeMillis(token).equals(currentTimeMillisRedis)) {
                return new SimpleAuthenticationInfo(token, token, getName());
            }
        }
        throw new IncorrectCredentialsException("Token已过期(Token expired or incorrect.)");

    }
}
