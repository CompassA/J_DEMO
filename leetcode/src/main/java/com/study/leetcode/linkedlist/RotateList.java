package com.study.leetcode.linkedlist;

/**
 * 61. Rotate List
 * Medium
 *
 * 941
 *
 * 960
 *
 * Add to List
 *
 * Share
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 *
 * Example 1:
 *
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 * Example 2:
 *
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 * @author fanqie
 * @date 2020/3/27
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        ListNode guard = new ListNode(-1);
        guard.next = head;

        int length = 0;
        while (head != null) {
            head = head.next;
            ++length;
        }
        k = k % length;
        if (k == 0) {
            return guard.next;
        }

        ListNode slow = guard;
        ListNode fast = guard;
        for (int i = 0; i < k; ++i) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = guard.next;

        return newHead;
    }
}
