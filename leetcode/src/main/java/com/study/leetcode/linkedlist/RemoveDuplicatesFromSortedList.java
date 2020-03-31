package com.study.leetcode.linkedlist;

/**
 * @author fanqie
 * @date 2020/3/27
 */
public class RemoveDuplicatesFromSortedList {
    /**
     * 82. Remove Duplicates from Sorted List II
     * Medium
     *
     * 1329
     *
     * 100
     *
     * Add to List
     *
     * Share
     * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
     *
     * Return the linked list sorted as well.
     *
     * Example 1:
     *
     * Input: 1->2->3->3->4->4->5
     * Output: 1->2->5
     * Example 2:
     *
     * Input: 1->1->1->2->3
     * Output: 2->3
     */
    public ListNode deleteDuplicatesTwo(ListNode head) {
        ListNode guard = new ListNode(-1);
        guard.next = head;
        ListNode tail = guard;

        while (head != null) {
            ListNode valEnd = head.next;
            while (valEnd != null && valEnd.val == head.val) {
                valEnd = valEnd.next;
            }
            if (valEnd == head.next) {
                tail.next = head;
                tail = tail.next;
            }
            head = valEnd;
        }

        tail.next = null;
        return guard.next;
    }


    /**
     * 83. Remove Duplicates from Sorted List
     * Easy
     *
     * 1219
     *
     * 96
     *
     * Add to List
     *
     * Share
     * Given a sorted linked list, delete all duplicates such that each element appear only once.
     *
     * Example 1:
     *
     * Input: 1->1->2
     * Output: 1->2
     * Example 2:
     *
     * Input: 1->1->2->3->3
     * Output: 1->2->3
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode tail = head;
        ListNode newHead = head;
        head = head.next;

        while (head != null) {
            if (tail.val != head.val) {
                tail.next = head;
                tail = tail.next;
            }
            head = head.next;
        }
        tail.next = null;
        return newHead;
    }
}
