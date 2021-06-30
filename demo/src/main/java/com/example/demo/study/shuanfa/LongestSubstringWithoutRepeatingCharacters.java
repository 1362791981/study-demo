package com.example.demo.study.shuanfa;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;

/**
 * @Description: 无重复字符的最长子串
 * @ClassName: LongestSubstringWithoutRepeatingCharacters
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-06-29 14:04
 */
@Slf4j
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String str = "abcddjhzndasad";

        int length = str.length();
        //定义左下标，由下标。最大不重复子串可看作是左两个下标进行滑动窗口对比
        int leftIndex = -1, rightIndex = 0, maxLength = 0;
        HashSet<Character> characterHashSet = new HashSet<Character>(length);

        for (int i = 0; i < length; i++) {

            //左侧下标向前移动，移除前一位字符
            if (i != 0) {
                characterHashSet.remove(str.charAt(i - 1));
            }

            //右侧下标不断向前移动，如果hashset中有重复字符，说明此刻已重复，跳出循环，否者将当前字符放入hashmap中
            while (rightIndex + 1 < length && !characterHashSet.contains(str.charAt(rightIndex + 1))) {
                characterHashSet.add(str.charAt(rightIndex + 1));
                rightIndex++;
            }
            //保留上一次与此次 子串长度最大的
            if (rightIndex - i + 1 > maxLength) {
                maxLength = rightIndex - i + 1;
                leftIndex = i;
            }
        }

        System.out.println("最大长度：" + maxLength + "字符串：" + str.substring(leftIndex, rightIndex - 1));
    }
}
