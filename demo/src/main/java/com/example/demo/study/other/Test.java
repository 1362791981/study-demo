package com.example.demo.study.other;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: cs
 * @ClassName: Test
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-05-27 10:52
 */
public class Test {

    public static void main(String[] args) {
        test("");
    }

    private static void test(String... str){

        List<String> strs = Arrays.asList("a", "a", "a", "a", "b");
        System.out.println(Arrays.stream(str).anyMatch(strs::contains));
    }
}
