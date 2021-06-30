package com.example.demo.study.algorithm.MaxNumCommonSubstring;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 使用map降低时间复杂度 | 时间复杂度O（i*maxLength）,空间复杂度O（n）
 * @ClassName: MapTOReduceComplexity
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-05-31 15:02
 */
@Slf4j
public class MapTOReduceComplexity {

    public static void main(String[] args) {
        String source = "abcdef1234";
        String target = "123abcdef";
        String compare = CommonSubstring2.compare(source, target);
        log.info("最大公共子串为：{}", compare);
    }
}

@Slf4j
class CommonSubstring2 {

    public static String compare(String source, String target) {
        long beginTime = System.currentTimeMillis();
        log.info("开始执行：{}", beginTime);
        //source为基础字符串，target为待比较子串
        //maxLengthIndex最大公共子串最后位置索引,maxLength最大公共子串长度
        int maxLengthIndex = 0;
        int maxLength = 0;

        char[] sources = source.toCharArray();
        char[] targets = target.toCharArray();

        //将当前比较字符串的字符作为key，下标索引作为value保存至一个map中
        Map<Character, String> targetMap = new HashMap<>(targets.length);
        for (int i = 0; i < targets.length; i++) {
            if (targetMap.containsKey(targets[i])) {
                targetMap.put(targets[i], targetMap.get(targets[i]) + "," + i);
            } else {
                targetMap.put(targets[i], "" + i);
            }
        }

        for (int i = 0; i < sources.length; i++) {
            //如果当前字符在map中作为key存在，则相等
            if (targetMap.containsKey(sources[i])) {

                //记录两个字符串相同位置索引
                int commonIIndex = i;
                String targetIndexs = targetMap.get(sources[i]);
                int tempMaxLength = 0;

                for (String targetIndexStr : targetIndexs.split(",")) {
                    int targetIndex = Integer.valueOf(targetIndexStr);
                    while (commonIIndex < sources.length && targetIndex < targets.length && sources[i] == targets[targetIndex]) {
                        commonIIndex++;
                        targetIndex++;
                        tempMaxLength++;
                    }
                }

                if (tempMaxLength > maxLength) {
                    maxLength = tempMaxLength;
                    maxLengthIndex = commonIIndex;
                }

            }
        }
        if (maxLength == 0) {
            return "-1";
        } else {
            log.info("结束执行：{}", System.currentTimeMillis());
            log.info("总时间：{}", System.currentTimeMillis() - beginTime);
            return source.substring(maxLengthIndex - maxLength, maxLengthIndex);
        }
    }
}
