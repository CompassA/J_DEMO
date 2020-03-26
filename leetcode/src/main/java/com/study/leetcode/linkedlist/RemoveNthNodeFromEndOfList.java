package com.study.leetcode.linkedlist;

/**
 * @author fanqie
 * @date 2020/3/25
 */
public class RemoveNthNodeFromEndOfList {
    /**
     * 19. Remove Nth Node From End of List
     * Medium
     *
     * 2731
     *
     * 199
     *
     * Add to List
     *
     * Share
     * Given a linked list, remove the n-th node from the end of list and return its head.
     *
     * Example:
     *
     * Given linked list: 1->2->3->4->5, and n = 2.
     *
     * After removing the second node from the end, the linked list becomes 1->2->3->5.
     * Note:
     *
     * Given n will always be valid.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode guard = new ListNode(-1);
        guard.next = head;

        ListNode fast = guard;
        for (int i = 0; i < n; ++i) {
            fast = fast.next;
        }

        ListNode slow = guard;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        return guard.next;
    }
}
