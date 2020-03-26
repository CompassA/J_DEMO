package com.study.leetcode.linkedlist;

/**
 * 206. Reverse Linked List
 * Easy
 *
 * 3742
 *
 * 81
 *
 * Add to List
 *
 * Share
 * Reverse a singly linked list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 * @author fanqie
 * @date 2020/3/27
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode tail = head;

        while (tail.next != null) {
            ListNode toBeReversed = tail.next;
            tail.next = toBeReversed.next;
            toBeReversed.next = pre.next;
            pre.next = toBeReversed;
        }

        return pre.next;
    }

    public ListNode reverseListOtherWay(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseListRecursive(head.next);

        head.next.next = head;
        head.next = null;

        return newHead;
    }
}
