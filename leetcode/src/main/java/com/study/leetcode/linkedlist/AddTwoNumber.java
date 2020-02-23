package com.study.leetcode.linkedlist;

import java.util.Objects;

/**
 * @author FanQie
 * @date 2019/11/20 23:40
 */
public class AddTwoNumber {

    /**
     * NO.2 Add Two Numbers
     * <p>
     * You are given two non-empty linked lists representing two non-negative integers.
     * The digits are stored in reverse order and each of their nodes contain a single digit.
     * Add the two numbers and return it as a linked list.
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //设置头节点、前驱节点、进位数
        final ListNode head = new ListNode(-1);
        ListNode pre = head;
        int carry = 0;

        //只要有一个节点非空就进行循环
        while (Objects.nonNull(l1) || Objects.nonNull(l2)) {
            if (Objects.nonNull(l1)) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (Objects.nonNull(l2)) {
                carry += l2.val;
                l2 = l2.next;
            }
            final ListNode cur = new ListNode(carry % 10);
            carry /= 10;
            pre.next = cur;
            pre = cur;
        }

        //位数是否增加
        if (carry != 0) {
            pre.next = new ListNode(carry);
        }

        return head.next;
    }

}