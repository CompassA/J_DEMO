package com.study.leetcode.linkedlist;

/**
 * 24. Swap Nodes in Pairs
 * Medium
 *
 * 1854
 *
 * 158
 *
 * Add to List
 *
 * Share
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 *
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * @author fanqie
 * @date 2020/3/27
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode guard = new ListNode(-1);
        guard.next = head;
        ListNode tail = guard;

        while (tail.next != null) {
            ListNode nodeA = tail.next;
            ListNode nodeB = tail.next.next;
            if (nodeB == null) {
                return guard.next;
            }
            tail.next = nodeB;
            nodeA.next = nodeB.next;
            nodeB.next = nodeA;
            tail = nodeA;
        }
        return guard.next;
    }
}
