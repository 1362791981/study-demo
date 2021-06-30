package com.example.demo.study.algorithm.MaxNumCommonSubstring;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 双层循环遍历 | 时间复杂度O（i*j*maxLength）,空间复杂度O（1）
 * @ClassName: BilevelErgodic
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-05-31 14:09
 */
@Slf4j
public class BilevelErgodic {

    public static void main(String[] args) {
        String source = "abcdef1234";
        String target = "123abcdef";
        String compare = CommonSubstring1.compare(source, target);
        log.info("最大公共子串为：{}", compare);
    }
}

@Slf4j
class CommonSubstring1 {

    public static String compare(String source, String target) {
        long beginTime = System.currentTimeMillis();
        log.info("开始执行：{}", beginTime);
        //source为基础字符串，target为待比较子串
        //maxLengthIndex最大公共子串最后位置索引,maxLength最大公共子串长度
        int maxLengthIndex = 0;
        int maxLength = 0;

        char[] sources = source.toCharArray();
        char[] targets = target.toCharArray();
        for (int i = 0; i < sources.length; i++) {
            for (int j = 0; j < targets.length; j++) {
                //遍历source和target，首先找到第一个相同字符
                if (sources[i] == targets[j]) {

                    //记录两个字符串相同位置索引
                    int commonIIndex = i;
                    int commonJIndex = j;
                    int tempMaxLength = 0;
                    //将两个字符串索引同时加一，自循环判断
                    //注意两个字符串的下标不要越界
                    while (commonIIndex < sources.length && commonJIndex < targets.length && sources[i] == targets[j]) {
                        commonIIndex++;
                        commonJIndex++;
                        tempMaxLength++;
                    }

                    if (tempMaxLength > maxLength) {
                        maxLength = tempMaxLength;
                        maxLengthIndex = commonIIndex;
                    }

                }
            }
        }
        if (maxLengthIndex == 0) {
            return "-1";
        } else {
            log.info("结束执行：{}", System.currentTimeMillis());
            log.info("总时间：{}", System.currentTimeMillis() - beginTime);
            return source.substring(maxLengthIndex - maxLength, maxLengthIndex);
        }
    }
}
