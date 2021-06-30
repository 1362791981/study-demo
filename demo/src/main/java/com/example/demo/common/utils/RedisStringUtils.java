package com.example.demo.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisStringUtils {

    private static Logger log = LoggerFactory.getLogger(RedisStringUtils.class);

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, String object) {
        redisTemplate.opsForValue().set(key, object);
    }

    public void set(String key, String object, long timeout) {
        redisTemplate.opsForValue().set(key, object, timeout, TimeUnit.SECONDS);
    }

    /**
     * List 数据结构
     */
    public List<String> range(String key) {
        try {
            return redisTemplate.opsForList().range(key, 0, -1);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 左入
     * @param key
     * @param object
     * @return
     */
    public Long lpush(String key, Object object) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForList().leftPush(key, (String) object);
    }
    /**
     * 右入
     * @param key
     * @param object
     * @return
     */
    public Long rpush(String key, Object object) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForList().rightPush(key, (String) object);
    }
    /**
     * 左批量入
     * @param key
     * @param object
     * @return
     */
    public Long lpushAll(String key,String... val){
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForList().leftPushAll(key,val);
    }
    /**
     * 右批量入
     * @param key
     * @param object
     * @return
     */
    public Long rpushAll(String key,String... val){
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForList().rightPushAll(key,val);
    }
    /**
     * 左出
     * @param key
     * @param object
     * @return
     */
    public String lpop(String key){
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForList().leftPop(key);
    }
    /**
     * 右出
     * @param key
     * @param object
     * @return
     */
    public String rpop(String key){
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * hash set方法
     * @param key
     * @param hk
     * @param hv
     */
    public void hset(String key,Object hk, Object hv){
        redisTemplate.opsForHash().put(key,hk,hv);
    }

    /**
     * hash setAll方法
     * @param key
     * @param var2
     */
    public void hsetAll(String key, Map<Object,Object> var2){
        redisTemplate.opsForHash().putAll(key,var2);
    }

    /**
     * hash get方法
     * @param key
     * @param hk
     * @return
     */
    public Object hget(String key,Object hk){
        return redisTemplate.opsForHash().get(key,hk);
    }

    /**
     * hash 获取同一个key下 多个field
     * @param key
     * @param hk
     * @return
     */
    public Object hmultiGet(String key,List<Object> hk){
        return redisTemplate.opsForHash().multiGet(key,hk);
    }

    /**
     * hash 删除一个key下 多个field
     * @param key
     * @param hk
     * @return
     */
    public Long hmultiGet(String key,Object... hk){
        return redisTemplate.opsForHash().delete(key,hk);
    }

    /**
     * hash 指定key下的field自增var3(long类型)
     * @param key
     * @param hk
     * @param var3
     * @return
     */
    public Long hincrement(String key,Object hk,long var3){
        return redisTemplate.opsForHash().increment(key,hk,var3);
    }

    /**
     * hash 指定key下的field自增var3（double类型）
     * @param key
     * @param hk
     * @param var3
     * @return
     */
    public Double hincrement(String key,Object hk,double var3){
        return redisTemplate.opsForHash().increment(key,hk,var3);
    }

    /**
     * hash 获取指定key的值的长度
     * @param key
     * @param hk
     * @return
     */
    public Long hlen(String key,Object hk){
        return redisTemplate.opsForHash().lengthOfValue(key,hk);
    }

    /**
     * hash 返回指定key下field个数
     * @param key
     * @return
     */
    public Long hlen(String key){
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * hash 获取指定key的所有filed
     * @param key
     * @return
     */
    public Set<Object> hkeys(String key){
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * hash 获取指定key下所有values
     * @param key
     * @return
     */
    public List<Object> hvals(String key){
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * hash 获取指定key下所有field-value
     * @param key
     * @return
     */
    public Map<Object, Object> hentries(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * hash 数据结构
     * 如果 field 存在就不在修改
     */
    public void hsetIfAbsent(String key, String field, String value) {
        try {
            redisTemplate.opsForHash().putIfAbsent(key, field, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 将一个元素及其 score 值加入到有序集 key 当中
     * O(M*log(N))， N 是有序集的基数， M 为成功添加的新成员的数量
     *
     * @param key    key
     * @param value  member
     * @param source score
     * @return 是否成功
     */
    public Boolean zAdd(String key, String value, double source) {
        try {
            return redisTemplate.opsForZSet().add(key, value, source);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
