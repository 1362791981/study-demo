package com.example.demo.study.shuanfa;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * @Description: 两数之和
 * @ClassName: SumTwoNumbers
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-06-29 10:11
 */
@Slf4j
public class SumTwoNumbers {

    //输入：int[] nums = {9, 7, 11, 2}; int number = 9;
    //输出：[1,3]
    //解释：因为 nums[1] + nums[3] == 9 ，返回 [1, 3] 。
    public static void main(String[] args) {
        int[] nums = {9, 7, 11, 2};
        int number = 9;
        int[] index = GetIndex.getIndex(nums, number);
        System.out.println("结果为：" + index == null ? "无对应结果" : JSON.toJSONString(index));
    }
}

/**
 * 核心思想为，用一个hashMap存储数组中各个值以及对应的下标
 * 两数之和可转化为 目标数number - first = second
 * 判断map中key是否等体育目标数number - 当前值，如果为true，则证明已找到一组结果
 * <p>
 * 时间复杂度O(n)
 * 空间复杂度O(n)
 */
class GetIndex {

    public static int[] getIndex(int[] nums, int number) {

        //利用HashMap的containsKey方法，判断是否有值与当前值相加等于number
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(number - nums[i])) {
                return new int[]{hashMap.get(number - nums[i]), i};
            } else {
                hashMap.put(nums[i], i);
            }
        }
        return null;
    }
}
