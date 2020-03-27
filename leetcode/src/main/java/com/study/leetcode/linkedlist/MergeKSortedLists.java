package com.study.leetcode.linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author fanqie
 * @date 2020/3/27
 */
public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        Queue<ListNode> q = new PriorityQueue<>(lists.length, Comparator.comparingInt(nodeA -> nodeA.val));
        for (ListNode node : lists) {
            if (node != null) {
                q.offer(node);
            }
        }
        ListNode head = new ListNode(-1);
        ListNode tail = head;
        while (!q.isEmpty()) {
            ListNode cur = q.poll();
            if (cur.next != null) {
                q.offer(cur.next);
            }
            tail.next = cur;
            tail = cur;
        }
        return head.next;
    }
}
