package com.herion.everyknow.seims.config.shiro.cache;

import com.herion.everyknow.common.bean.Constant;
import com.herion.everyknow.seims.config.EknowConfig;
import com.herion.everyknow.seims.utils.JWTUtil;
import com.herion.everyknow.seims.utils.JedisUtil;
import com.herion.everyknow.seims.utils.SerializableUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.*;

/**
 * @Description Shiro 自定义缓存保存读取
 * @auther wubo25320
 * @create 2020-04-21 12:57
 */
public class CustomCache<K,V> implements Cache<K,V> {

    /**
     * 缓存的key名称获取为shiro:cache:account
     * @param key
     * @return
     */
    private String getKey(Object key) {
        return Constant.PREFIX_SHIRO_CACHE + JWTUtil.getUsername(key.toString());
    }

    /**
     * 获取缓存
     * @param k
     * @return
     * @throws CacheException
     */
    @Override
    public V get(K k) throws CacheException {
        if (Boolean.FALSE.equals(JedisUtil.exists(this.getKey(k)))) {
            return null;
        }
        return (V) JedisUtil.getObject(this.getKey(k));
    }

    @Override
    public V put(K k, V v) throws CacheException {
        return (V) JedisUtil.setObject(this.getKey(k),v, (int) EknowConfig.getShiroCacheExpireTime());
    }

    @Override
    public V remove(K k) throws CacheException {
        if (Boolean.FALSE.equals(JedisUtil.exists(this.getKey(k)))) {
            return null;
        }
        V returnV = this.get((K) this.getKey(k));
        JedisUtil.delKey(this.getKey(k));
        return returnV;

    }

    @Override
    public void clear() throws CacheException {
        Objects.requireNonNull(JedisUtil.getJedis()).flushDB();
    }

    @Override
    public int size() {
        Long size = Objects.requireNonNull(JedisUtil.getJedis()).dbSize();
        return size.intValue();
    }

    @Override
    public Set<K> keys() {
        Set<byte[]> keys = Objects.requireNonNull(JedisUtil.getJedis()).keys("*".getBytes());
        Set<K> set = new HashSet<>();
        for (byte[] bs : keys) {
            set.add((K) SerializableUtil.unserializable(bs));
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        Set<K> keys = this.keys();
        List<V> values = new ArrayList<>();
        for (K key : keys) {
            values.add((V) JedisUtil.getObject(this.getKey(key)));
        }
        return values;
    }
}
