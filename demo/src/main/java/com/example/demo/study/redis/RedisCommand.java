package com.example.demo.study.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description: redis 命令使用集合
 * @ClassName: RedisCommand
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-06-10 08:49
 */
@Slf4j
@Service
public class RedisCommand {

    @Autowired
    RedisTemplate redisTemplate;

    public void execute() {
        log.info("####################################set####################################");
        //set 不重复的集合，类似HashMap,通常可用于取不同集合的交集，并集，合集
        //1.关注的相同博主 2.共同好友
        String key = "set";
        //可以向指定key加入一个或多个值
        redisTemplate.opsForSet().add(key, "set测试练习1");
        redisTemplate.opsForSet().add(key, "set测试练习2");
        redisTemplate.opsForSet().add(key, "set测试练习3");

        Set members = redisTemplate.opsForSet().members(key);
        log.info("members 获取指定key的集合 {}", members);

        Boolean isMember = redisTemplate.opsForSet().isMember(key, "set测试练习2");
        log.info("isMember 判断某个元素是否在集合中 {}", isMember);

        Long size = redisTemplate.opsForSet().size(key);
        log.info("size 获取集合中元素个数 {}", size);

        Object randomMember = redisTemplate.opsForSet().randomMember(key);
        log.info("randomMember 随机获取集合中一个元素 {}", randomMember);

        Object pop = redisTemplate.opsForSet().pop(key);
        log.info("pop 随机删除集合中一个元素 {} 剩余元素：{}", pop, redisTemplate.opsForSet().members(key));

        Boolean move = redisTemplate.opsForSet().move(key, "set测试练习2", "setMove");
        log.info("move 将集合中元素转移到另一个key中 {} 转移后set：{}", randomMember, redisTemplate.opsForSet().members(key));

        String pattern = "*se*";
        Cursor<Object> cursor = redisTemplate.opsForSet().scan(key, ScanOptions.scanOptions().match(pattern).count(100).build());
        while (cursor.hasNext()) {
            Object object = cursor.next();
            log.info("scan(K key, ScanOptions options)方法获取匹配的值:" + object);
        }

        //diff1和diff2的差集
        String[] diff1 = new String[]{"1", "2", "3", "4", "5"};
        String[] diff2 = new String[]{"4", "5", "6", "7", "8"};
        redisTemplate.opsForSet().add("diffK1", diff1);
        redisTemplate.opsForSet().add("diffK2", diff2);
        Set difference = redisTemplate.opsForSet().difference("diffK1", "diffK2");//redis中两个key对应集合取差集
        log.info("difference redis中两个key对应集合取差集 {}", difference);

        //union1和union2的并集
        String[] union1 = new String[]{"1", "2", "3", "4", "5"};
        String[] union2 = new String[]{"4", "5", "6", "7", "8"};
        redisTemplate.opsForSet().add("unionK1", union1);
        redisTemplate.opsForSet().add("unionK2", union2);
        Set union = redisTemplate.opsForSet().union("unionK1", "unionK2");//redis中两个key对应集合取并集
        log.info("union redis中两个key对应集合取并集 {}", union);

        //intersect1和intersect2的交集
        String[] intersect1 = new String[]{"1", "2", "3", "4", "5"};
        String[] intersect2 = new String[]{"4", "5", "6", "7", "8"};

        redisTemplate.opsForSet().add("intersectK1", intersect1);
        redisTemplate.opsForSet().add("intersectK2", intersect2);
        Set intersect = redisTemplate.opsForSet().intersect("intersectK1", "intersectK2");//redis中两个key对应集合取交集
        log.info("intersect redis中两个key对应集合取交集 {}", intersect);
        log.info("####################################set####################################");

        System.out.println();
        System.out.println();

        log.info("####################################zset####################################");
        String keyZ = "zset";
        redisTemplate.opsForZSet().add(keyZ, "zset测试1", 1);
        redisTemplate.opsForZSet().add(keyZ, "zset测试2", 3);
        redisTemplate.opsForZSet().add(keyZ, "zset测试3", 2);

        Long count = redisTemplate.opsForZSet().count(keyZ, 1, 2);
        log.info("count 获取指定区间总数 {} ", count);

        Long zsize = redisTemplate.opsForZSet().size(keyZ);
        log.info("size 获取集合元素总数 {} ", zsize);

        Double incrementScore = redisTemplate.opsForZSet().incrementScore(keyZ, "zset测试3", 2);
        log.info("incrementScore 给指定key的值加score分数 {}", incrementScore);

        Double score = redisTemplate.opsForZSet().score(keyZ, "zset测试2");
        log.info("score 给指定key的值分数 {}", score);

        Long rank = redisTemplate.opsForZSet().rank(keyZ, "zset测试3");
        log.info("rank 返回指定成员的排名（从小到大） {}", rank);
        Long reverseRank = redisTemplate.opsForZSet().reverseRank(keyZ, "zset测试3");
        log.info("reverseRank 返回指定成员的排名（从大到小） {}", reverseRank);

        log.info("####################################zset####################################");

        System.out.println();
        System.out.println();


        log.info("####################################list####################################");

        String listKey = "list";
        String listKey1 = "list1";
        //模拟队列 左进右出->先进先出
        //左进
        redisTemplate.opsForList().leftPush(listKey, 1);
        redisTemplate.opsForList().leftPush(listKey, 2);
        redisTemplate.opsForList().leftPush(listKey, 3);
        redisTemplate.opsForList().leftPush(listKey1, "Z");
        log.info("list 模拟队列 {}", redisTemplate.opsForList().range(listKey, 0, -1));
        //右出
        Object rightPop = redisTemplate.opsForList().rightPop(listKey);
        log.info("rightPop 取出右出元素 {}", rightPop);

        Object index = redisTemplate.opsForList().index(listKey, 1);
        log.info("index 获取指定下标元素 {}", index);

        redisTemplate.opsForList().remove(listKey, 1, 1);
        log.info("remove 可以理解为,从index为0的位置开始遍历这个list, 删除值为value的项,直到删除count项为止 {}");

        redisTemplate.opsForList().set(listKey, 0, 100);
        log.info("set 将原来list中下标为0的元素,重新赋值为100");

        log.info("list 模拟队列 listKey: {}", redisTemplate.opsForList().range(listKey, 0, -1));
        redisTemplate.opsForList().trim(listKey, 0, 1);
        log.info("trim 从一个list中截取下标startIndex到endIndex的值,再赋值给key,原来的key中的值就被替换了");


        redisTemplate.opsForList().rightPopAndLeftPush(listKey, listKey1);
        log.info("rightPopAndLeftPush 将一个list栈顶的一个值,放到另一个list的栈底");
        log.info("list 模拟队列 listKey: {}", redisTemplate.opsForList().range(listKey, 0, -1));
        log.info("list 模拟队列 listKey1: {}", redisTemplate.opsForList().range(listKey1, 0, -1));
        log.info("####################################list####################################");

        System.out.println();
        System.out.println();

        log.info("####################################hash####################################");

        String user = "user";
        redisTemplate.opsForHash().put(user, "id", 1);
        redisTemplate.opsForHash().put(user, "name", "赵美丽");
        redisTemplate.opsForHash().put(user, "age", 20);
        log.info("hash user:{}", redisTemplate.opsForHash().values(user));

        Map users = new HashMap();
        users.put("school", "清华大学");
        users.put("subject", "软件工程");
        redisTemplate.opsForHash().putAll(user, users);
        log.info("putAll 同时设置多个值 user:{}", redisTemplate.opsForHash().values(user));

        List list = redisTemplate.opsForHash().multiGet(user, new ArrayList<String>() {{
            add("school");
            add("name");
        }});
        log.info("multiGet 同时获取多个值 user:{}", list);

        redisTemplate.opsForHash().delete(user, "subject");
        log.info("delete 删除某个值 user:{}", redisTemplate.opsForHash().values(user));

        Long size1 = redisTemplate.opsForHash().size(user);
        log.info("size 获取键值对的个数 :{}", size1);

        Long lengthOfValue = redisTemplate.opsForHash().lengthOfValue(user, "name");
        log.info("lengthOfValue 获取键值对的长度 :{}", lengthOfValue);

        Set keys = redisTemplate.opsForHash().keys(user);
        log.info("keys 获取所有键值对key :{}", keys);

        redisTemplate.opsForHash().putIfAbsent(user, "name", "赵美丽啊");
        log.info("putIfAbsent 如果key不存在就赋值，否则什么都不做 :{}", redisTemplate.opsForHash().values(user));
        log.info("####################################hash####################################");

    }
}
