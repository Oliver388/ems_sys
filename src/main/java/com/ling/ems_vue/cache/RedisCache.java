package com.ling.ems_vue.cache;

import com.ling.ems_vue.utils.ApplicationContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author linglifan
 * @date 2022/07/14 15:17
 */
@Slf4j
public class RedisCache implements Cache {

    private String id;

    public RedisCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        log.info("当前的缓存id: [{}]",id);
        return this.id;
    }

    @Override//放入redis缓存
    public void putObject(Object key, Object value) {
        log.info("放入缓存的key:{}",key);
        getRedisTemplate().opsForHash().put(id,key.toString(),value);
    }

    @Override//从redis缓存获取
    public Object getObject(Object key) {
        log.info("获取缓存的key:[{}]",key.toString());
        return getRedisTemplate().opsForHash().get(id,key.toString());
    }

    @Override//删除指定的缓存信息
    public Object removeObject(Object o) {
        return null;
    }

    @Override//清楚缓存
    public void clear() {
        log.info("清除所有缓存信息...");
        getRedisTemplate().delete(id);

    }

    @Override
    public int getSize() {
        return getRedisTemplate().opsForHash().size(id).intValue();
    }

    //封装获取redisTemplate的方法
    public RedisTemplate getRedisTemplate(){
        //静态方法可以直接由类名访问
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
