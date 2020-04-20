package com.herion.everyknow.seims.config.shiro;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;

/**
 * @Description Shiro 的配置
 * @auther wubo25320
 * @create 2020-04-16 9:43
 */

/**
 * Shiro 流程说明
 *  1. 首先根据 ShiroFilterFactoryBean 配置的 FilterChainDefinitionMap. 从上到下拦截.
 *      1.1 比如这里我们配置 FilterChainDefinitionMap如下
 *          filterRuleMap.put("/user/login", "anon");
 *          filterRuleMap.put("/**", "jwt");
 *          那么这里 /user/login 这个路径就不会拦截. 其他所有路径都会拦截(注意: 这里说的拦截,是在 进入Controller 层之前拦截)
 *  2. 如果路径和我们配置的 FilterChainDefinitionMap 匹配. 就会由我们的 ShiroFilterFactoryBean 设置的 FilterMap 中的规则来进行
 *      2.1 比如我们这里配置 FilterMap
 *          filterMap.put("jwt", new JWTFilter());
 *      那么我们就会进入 JWTFilter() 中执行流程
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建 ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Autowired SecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        // 1. 设置安全管理器
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//
//        // 2. 添加 shiro内置过滤器
//        /**
//         * Shiro 内置过滤器,可以实现权限相关的拦截器
//         *      常用的过滤器
//         *          anon: 无需认证( 登录 ) 可以方法
//         *          authc: 必须认证才可以访问
//         *          user: 如果使用 rememberMe的功能可以直接使用
//         *          perms: 该资源必须得到资源权限才能访问
//         *          role: 该资源必须得到角色权限才可以访问
//         */
//        // 定义 shiro 过滤链(map中key: 拦截路径, value: 过滤器)
//        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        // 加这个, 所有的请求都不会拦截
//        filterChainDefinitionMap.put("/**", "anon");
//
////        filterChainDefinitionMap.put("/", "anon");
////        filterChainDefinitionMap.put("/user/login", "anon");
////        //        filterChainDefinitionMap.put("/", "anon");
////
////        filterChainDefinitionMap.put("/**", "authc");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//
//        // 设置内置过滤器的 拦截返回的信息
//        LinkedHashMap<String, Filter> filterMap = new LinkedHashMap<>();
//        filterMap.put("authc", new AjaxPermissionAuthorizationFilter());
//        shiroFilterFactoryBean.setFilters(filterMap);
//
//        return shiroFilterFactoryBean;

        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        // 添加自己的过滤器并且取名为jwt
        LinkedHashMap<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("jwt", new JWTFilter());
        factoryBean.setFilters(filterMap);

        factoryBean.setSecurityManager(securityManager);

        /**
         * 自定义url规则
         * http://shiro.apache.org/web.html#urls-
         */
        LinkedHashMap<String, String> filterRuleMap = new LinkedHashMap<>();
//        filterRuleMap.put("/user/login", "anon");
        filterRuleMap.put("/**", "jwt");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }

    /**
     * 创建 SecurityManager (这里我们用的 DefaultWebSecurityManager)
     */
    @Bean
    public SecurityManager securityManager(@Autowired UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联 Realm
        securityManager.setRealm(userRealm);

        /**
         * 关闭 Shiro 自带的 session, 详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }

    /**
     * 创建一个 Realm (自定义的)
     */
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
//        userRealm.setCredentialsMatcher();
        return userRealm;
    }

    /**
     * 实现 Shiro 的加密,可以扩展此凭证匹配器, 实现加密, 输入密码错误次数后锁定等功能
     */


    /**
     * 以下部分为了实现 在 Spring 中开启 Shiro 的注解
     */
    // 1. Shiro生命周期处理器
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    // 2. 开启 Spring 中 Shiro 的注解
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager(userRealm()));
        return authorizationAttributeSourceAdvisor;
    }
}
