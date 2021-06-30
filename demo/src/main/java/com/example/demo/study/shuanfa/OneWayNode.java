package com.example.demo.study.shuanfa;

import lombok.Data;

/**
 * @Description: 单向链表
 * @ClassName: OneWayNode
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-06-29 11:08
 */
@Data
public class OneWayNode {

    int key;
    String value;
    OneWayNode next;

    public OneWayNode() { }

    public OneWayNode(int key) {
        this.key = key;
    }

    public OneWayNode(int key, OneWayNode next) {
        this.key = key;
        this.next = next;
    }

    public OneWayNode(String value) {
        this.value = value;
    }

    public OneWayNode(int key, String value, OneWayNode next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
