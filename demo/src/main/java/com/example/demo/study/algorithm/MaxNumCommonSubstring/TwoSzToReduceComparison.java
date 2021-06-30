package com.example.demo.study.algorithm.MaxNumCommonSubstring;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @Description: 使用二维数组记录两字符串不同位置值相同记录 | 时间复杂度O（i*j）,空间复杂度O（i*j）
 * @ClassName: TwoSzToReduceComparison
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-05-31 15:58
 */
@Slf4j
public class TwoSzToReduceComparison {

    public static void main(String[] args) {
        String source = "abcdef1234";
        String target = "123abcdef";
        String compare = CommonSubstring3.compare(source, target);
        log.info("最大公共子串为：{}", compare);
    }

}

@Slf4j
class CommonSubstring3{

    public static String compare(String source, String target){
        long beginTime = System.currentTimeMillis();
        log.info("开始执行：{}", beginTime);
        //source为基础字符串，target为待比较子串
        //maxLengthIndex最大公共子串最后位置索引,maxLength最大公共子串长度
        int maxLengthIndex = 0;
        int maxLength = 0;

        char[] sources = source.toCharArray();
        char[] targets = target.toCharArray();

        //定义一个二维数组
        int[][] twoArray = new int[sources.length+1][targets.length+1];

        for(int i = 0;i<sources.length;i++){
            for(int j=0;j<targets.length;j++){
                if(sources[i] == targets[j]){
                    twoArray[i+1][j+1] = twoArray[i][j] + 1;
                    if(twoArray[i+1][j+1] > maxLength){
                        maxLength = twoArray[i+1][j+1];
                        maxLengthIndex = i;
                    }

                }
            }
        }

        if(maxLength == 0){
            return "-1";
        }else {
            log.info("结束执行：{}", System.currentTimeMillis());
            log.info("总时间：{}", System.currentTimeMillis() - beginTime);
            return source.substring(maxLengthIndex+1-maxLength,maxLengthIndex+1);
        }

    }
}
