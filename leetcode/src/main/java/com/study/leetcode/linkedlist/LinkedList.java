package com.study.leetcode.linkedlist;

/**
 * @author fanqie
 * @date 2020/3/31
 */
public class LinkedList {

    /**
     * 86. Partition List
     * Medium
     *
     * 1038
     *
     * 263
     *
     * Add to List
     *
     * Share
     * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
     *
     * You should preserve the original relative order of the nodes in each of the two partitions.
     *
     * Example:
     *
     * Input: head = 1->4->3->2->5->2, x = 3
     * Output: 1->2->2->4->3->5
     */
    public ListNode partition(ListNode head, int x) {
        ListNode headA = new ListNode(-1);
        ListNode headB = new ListNode(-1);
        ListNode tailA = headA;
        ListNode tailB = headB;
        while (head != null) {
            if (head.val < x) {
                tailA.next = head;
                tailA = tailA.next;
            } else {
                tailB.next = head;
                tailB = tailB.next;
            }
            head = head.next;
        }
        tailA.next = headB.next;
        tailB.next = null;
        return headA.next;
    }

    /**
     * 92. Reverse Linked List II
     * Medium
     *
     * 1900
     *
     * 124
     *
     * Add to List
     *
     * Share
     * Reverse a linked list from position m to n. Do it in one-pass.
     *
     * Note: 1 ≤ m ≤ n ≤ length of list.
     *
     * Example:
     *
     * Input: 1->2->3->4->5->NULL, m = 2, n = 4
     * Output: 1->4->3->2->5->NULL
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode guard = new ListNode(-1);
        guard.next = head;
        ListNode partitionHead = guard;
        for (int i = 1; i < m; ++i) {
            partitionHead = partitionHead.next;
        }
        ListNode tail = partitionHead.next;
        int moveTimes = n - m;
        for (int i = 0; i < moveTimes; ++i) {
            ListNode nextMove = tail.next;
            tail.next = nextMove.next;
            nextMove.next = partitionHead.next;
            partitionHead.next = nextMove;
        }
        return guard.next;
    }
}
