package com.example.demo.study.shuanfa;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 两数相加
 * @ClassName: AddTwoNUmbers
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-06-29 11:05
 */
@Slf4j
public class AddTwoNUmbers {

    public static void main(String[] args) {

        //输入：node1 = [3,4,2], node2 = [2,7,5]
        //输出：[5,1,8]
        //解释：243 + 572 = 815

        //输入：node1 = [3,4,4], node2 = [2,7,5]
        //输出：[5,1,0,1]
        //解释：443 + 572 = 1015

        OneWayNode node1 = new OneWayNode(3, new OneWayNode(4, new OneWayNode(4, null)));
        OneWayNode node2 = new OneWayNode(2, new OneWayNode(7, new OneWayNode(5, null)));
        OneWayNode oneWayNode = GetListNode.getListNode(node1, node2);

        System.out.println("链表结果为：" + JSON.toJSONString(oneWayNode));

    }
}

/**
 * 链表是以倒叙排列并且相加的
 * 计算加法时，是从右开始计算进位，那么就可以从链表的头结点向后同时遍历两个链表
 * 直接取出值相加，取模运算，进位值carry为除以10后取整数
 * 如果两个链表长度不相等，则用0补位
 * 最后要判断下是否剩余进位值，如果有加到结果链表的最后面
 * 结果取头结点的下个节点，因为头结点用于初始化时存的无用节点
 */
@Data
class GetListNode {

    public static OneWayNode getListNode(OneWayNode node1, OneWayNode node2) {
        OneWayNode head = new OneWayNode(0);
        OneWayNode curr = head;
        int carry = 0;

        while (node1 != null || node2 != null) {
            int num1 = node1 == null ? 0 : node1.getKey();
            int num2 = node2 == null ? 0 : node2.getKey();
            int sum = num1 + num2 + carry;

            int currSum = sum % 10;
            carry = sum / 10;

            curr.next = new OneWayNode(currSum);
            curr = curr.next;

            if (node1 != null) {
                node1 = node1.next;
            }
            if (node2 != null) {
                node2 = node2.next;
            }
        }
        if (carry > 0) {
            curr.next = new OneWayNode(carry);
        }
        return head.next;
    }
}
