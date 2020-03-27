package com.study.leetcode.linkedlist;

/**
 * 25. Reverse Nodes in k-Group
 * Hard
 *
 * 1794
 *
 * 340
 *
 * Add to List
 *
 * Share
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * Example:
 *
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 *
 * Note:
 *
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 * @author fanqie
 * @date 2020/3/27
 */
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode guard = new ListNode(-1);
        guard.next = head;

        int cnt = 0;
        while (head != null) {
            ++cnt;
            head = head.next;
        }

        int groups = cnt / k;
        ListNode tail = guard;
        ListNode newTail = guard.next;
        for (int i = 0; i < groups; ++i) {
            for (int j = 1; j < k; ++j) {
                ListNode toBeReversed = newTail.next;
                newTail.next = toBeReversed.next;
                toBeReversed.next = tail.next;
                tail.next = toBeReversed;
            }
            tail = newTail;
            newTail = tail.next;
        }

        return guard.next;
    }
}
