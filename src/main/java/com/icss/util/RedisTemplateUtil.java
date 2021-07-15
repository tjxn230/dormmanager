package com.icss.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class RedisTemplateUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    public RedisTemplateUtil(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean set(String key, Object value) {
        try {
            ValueOperations valueOperations = redisTemplate.opsForValue();
            valueOperations.set(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean setH(String key,Object value,long time){
        try {
            if(time>0){
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.HOURS);
            }else{
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间(小时)
     * @return
     */
    public boolean expireHour(String key,long time){
        try {
            if(time>0){
                redisTemplate.expire(key, time, TimeUnit.HOURS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }
    public Boolean setList(String key, Object value,long time) {
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            listOperations.leftPush(key, value);
            expireHour(key,time);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List getList(String key, long start, long end) {
        return redisTemplate.opsForList().range(key,start,end);
    }
    public long getListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }
    //集合元素不能重复
    public boolean setSet(String key, Object value,long time) {
        try {
            SetOperations setOperations = redisTemplate.opsForSet();
            setOperations.add(key, value);
            expireHour(key,time);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Set getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }


    //键值对
    public boolean setHash(String key, Map<String, ?> value) {
        try {
            HashOperations hashOperations = redisTemplate.opsForHash();
            hashOperations.putAll(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Object getHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public boolean setZset(String key,Object o,double score,long time) {
        try {
            ZSetOperations zSetOperations = redisTemplate.opsForZSet();
            zSetOperations.add(key,o,score);
            expireHour(key,time);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Set getZset(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key,start,end);
    }
    public Set getReverseRange(String key,long start,long end) {
        return redisTemplate.opsForZSet().reverseRange(key,start,end);
    }
    public Set getReverseRangeByScore(String key,double a,double b) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key,a,b);
    }
    public Set getReverseRangeByScore(String key,double a,double b,long start,long end) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key,a,b,start,end);
    }
    public long getZsetSize(String key) {
        return redisTemplate.opsForZSet().size(key);
    }
    public long removeObjectInZset(String key,Object object) {
        return redisTemplate.opsForZSet().remove(key,object);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

}

