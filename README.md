# EVERYKONW_SEIMS
南昌大学本科生毕业论文_学生就业信息管理系统

# 所使用的技术
spring, springboot, mybatis, mybatis-plus, druid, swagger, easyexcel, hutool,

# druid 监控配置说明

访问地址: http://localhost:8090/druid

账号: admin

密码: admin

# Swagger 访问地址
访问地址: http://localhost:8090/swagger-ui.html

文档地址: http://localhost:8090/v2/api-docs

# Shiro 配置说明
本项目中的 鉴权系统.采用 Shiro + JWT的形式完成.
其中 Shiro 的缓存使用的是自定义的缓存(使用 Jedis 实现)

本项目的权限基于 RBAC(Resource-Based Access Control)新解完成. 是基于资源的.而不是基于角色
具体参考
https://github.com/dolyw/ShiroJwt
https://github.com/Heeexy/SpringBoot-Shiro-Vue

## 如何自定义秘钥
请看 everyknow-seims-core/resources/config.properties 配置文件(注意看说明)
此配置文件中的值通过 `EknowConfig.java` 读取使用
目前 有两个秘钥. 一个是 JWT的秘钥.用户 JWT加密解密
一个是 AES密码加密秘钥. 用于用户密码加密(前后端交流, 数据库存取一共两次)
前后端交流的 用户密码加密 使用 AESUtil2.java 工具
数据库存取的 密码加密 使用 AesCipherUtil.java 工具
![未命名文件.jpg](https://i.loli.net/2020/04/21/5x1bYukr2Unvlzm.png)

## 如何开启和关闭用户密码
// TODO
目前暂时不开启加密和解密

# Jedis 配置说明
请看 everyknow-seims-core/resources/config.properties 配置文件

Jedis数据库中存入的值都是经过 SerializableUtil 序列化的字节数组对象(推荐存Byte数组，存Json字符串效率更慢)