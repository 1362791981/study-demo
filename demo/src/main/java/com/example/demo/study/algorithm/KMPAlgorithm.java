package com.example.demo.study.algorithm;

/**
 * @Description: KMP算法
 * 基本介绍：
 * （1）暴力匹配算法
 *      1）如果当前字符匹配成功（即str1[i]=str2[i]），则i++,j++，继续匹配下一个字符
 *      2）如果失败，令i=i-(j-1)，j=0，相当于每次匹配失败时，i回溯，j被转为0
 *      3）用暴力方法解决的话就会有大量的回溯，每次只移动一位，若是不匹配，移动到下一位接着判断，浪费大量时间。（不可行）
 *      4）暴力匹配实现
 * （2）KMP算法介绍
 *      1）KMP是一个解决模式串在文本串是否出现过，如果出现过，最早出现的位置就经典算法。
 *      2）Knuth-Morris-Pratt字符串查找法，简称KMP。
 *      3）KMP算法就是利用之前判断过信息，通过一个next数组，保存模式串中前后最长公共序列的长度，每次回溯时，通过next数组找到，
 *          前面匹配的位置，省去了大量的计算时间
 *      4）参考资料：https://www.cnblogs.com/ZuoAndFutureGirl/p/9028287.html
 * @ClassName: KMPAlgorithm
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-03-16 11:25
 */
public class KMPAlgorithm {

    public static void main(String[] args) {
        // 暴力匹配
        String str1 = "abcabaabaabcacb";
        String str2 = "abaabcac";
        int res = 0;
        int sourceLength = str1.length();
        int patternLength = str2.length();
        for (int i = 0; i <= (sourceLength - patternLength); i++) {
            res++;
            String str = str1.substring(i, i + patternLength);
            if (str.equals(str2)) {
                System.out.println("朴素模式：匹配成功");
                break;
            }
        }
        System.out.println("朴素模式：一共匹配" + res + "次");

        //KMP算法
        System.out.println(kmpMatch(str1, str2));
    }

    /**
     * 求出一个字符数组的next数组
     * @param t 字符数组
     * @return next数组
     */
    public static int[] getNextArray(char[] t) {
        int[] next = new int[t.length];
        next[0] = -1;
        next[1] = 0;
        int k;
        for (int j = 2; j < t.length; j++) {
            k = next[j - 1];
            while (k != -1) {
                if (t[j - 1] == t[k]) {
                    next[j] = k + 1;
                    break;
                } else {
                    k = next[k];
                }
                next[j] = 0;  //当k==-1而跳出循环时，next[j] = 0，否则next[j]会在break之前被赋值
            }
        }
        return next;
    }

    /**
     * 对主串s和模式串t进行KMP模式匹配
     * @param s 主串
     * @param t 模式串
     * @return 若匹配成功，返回t在s中的位置（第一个相同字符对应的位置），若匹配失败，返回-1
     */
    public static int kmpMatch(String s, String t) {
        char[] s_arr = s.toCharArray();
        char[] t_arr = t.toCharArray();
        int[] next = getNextArray(t_arr);
        int i = 0, j = 0;
        while (i < s_arr.length && j < t_arr.length) {
            if (j == -1 || s_arr[i] == t_arr[j]) {
                i++;
                j++;
            } else
                j = next[j];
        }
        if (j == t_arr.length)
            return i - j;
        else
            return -1;
    }

}
