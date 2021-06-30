package com.example.demo.study.shuanfa;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 反转链表
 * @ClassName: ReverseLinkedList
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-06-29 09:02
 */
@Slf4j
public class ReverseLinkedList {

    public static void main(String[] args) {
        OneWayNode head = new OneWayNode(1,"a",new OneWayNode(2,"b",null));
        ReverseLinked.reverseLinked(head);
    }

}

/**
 * 将一个链表反转过来，无非就是将前后节点反转
 * 对单向列表而言，直接将节点的前后指向反转即可
 * 将当前节点的next节点指向当前节点的prev节点
 * 循环整个链表，先保存好前节点个下个节点用于循环反转
 */
class ReverseLinked{
    //反转链表
    public static void reverseLinked(OneWayNode head){

        System.out.println("反转前：" + JSON.toJSONString(head));
        OneWayNode prev = null;
        OneWayNode curr = head;

        while (curr != null){
            OneWayNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        System.out.println("反转后：" + JSON.toJSONString(prev));
    }
}
