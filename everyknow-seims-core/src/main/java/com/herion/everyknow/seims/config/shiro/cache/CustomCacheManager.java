package com.herion.everyknow.seims.config.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @Description 重写 Shiro 缓存管理器
 * @auther wubo25320
 * @create 2020-04-21 13:18
 */
public class CustomCacheManager implements CacheManager {
    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new CustomCache<K,V>();
    }
}
