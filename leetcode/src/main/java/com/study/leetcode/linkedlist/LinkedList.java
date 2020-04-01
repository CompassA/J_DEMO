package com.study.leetcode.linkedlist;

import com.study.leetcode.tree.TreeNode;

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

    /**
     * 109. Convert Sorted List to Binary Search Tree
     * Medium
     *
     * 1636
     *
     * 82
     *
     * Add to List
     *
     * Share
     * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
     *
     * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
     *
     * Example:
     *
     * Given the sorted linked list: [-10,-3,0,5,9],
     *
     * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
     *
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode pre = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (pre != null) {
            pre.next = null;
        }

        TreeNode node = new TreeNode(slow.val);
        node.left = sortedListToBST(head);
        node.right = sortedListToBST(slow.next);
        return node;
    }

    /**
     * 138. Copy List with Random Pointer
     * Medium
     *
     * 2670
     *
     * 598
     *
     * Add to List
     *
     * Share
     * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
     *
     * Return a deep copy of the list.
     *
     * The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
     *
     * val: an integer representing Node.val
     * random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.
     *
     *
     * Example 1:
     *
     *
     * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
     * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
     * Example 2:
     *
     *
     * Input: head = [[1,1],[2,1]]
     * Output: [[1,1],[2,1]]
     * Example 3:
     *
     *
     *
     * Input: head = [[3,null],[3,0],[3,null]]
     * Output: [[3,null],[3,0],[3,null]]
     * Example 4:
     *
     * Input: head = []
     * Output: []
     * Explanation: Given linked list is empty (null pointer), so return null.
     *
     *
     * Constraints:
     *
     * -10000 <= Node.val <= 10000
     * Node.random is null or pointing to a node in the linked list.
     * Number of Nodes will not exceed 1000.
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            Node cloneNode = new Node(cur.val);
            cloneNode.next = next;
            cur.next = cloneNode;
            cur = next;
        }
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        Node guard = new Node(-1);
        Node originGuard = new Node(-1);
        Node tail = guard;
        Node originTail = originGuard;
        while (head != null) {
            tail.next = head.next;
            tail = tail.next;
            originTail.next = head;
            originTail = originTail.next;
            head = head.next.next;
        }
        originTail.next = null;
        return guard.next;
    }
}
