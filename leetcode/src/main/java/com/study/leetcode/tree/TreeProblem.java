package com.study.leetcode.tree;

import com.study.leetcode.linkedlist.ListNode;

/**
 * @author fanqie
 * @date 2020/4/15
 */
public class TreeProblem {
    /**
     * 1367. Linked List in Binary Tree
     * Medium
     *
     * 246
     *
     * 11
     *
     * Add to List
     *
     * Share
     * Given a binary tree root and a linked list with head as the first node.
     *
     * Return True if all the elements in the linked list starting from the head correspond to some downward path connected in the binary tree otherwise return False.
     *
     * In this context downward path means a path that starts at some node and goes downwards.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
     * Output: true
     * Explanation: Nodes in blue form a subpath in the binary Tree.
     * Example 2:
     *
     *
     *
     * Input: head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
     * Output: true
     * Example 3:
     *
     * Input: head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
     * Output: false
     * Explanation: There is no path in the binary tree that contains all the elements of the linked list from head.
     *
     *
     * Constraints:
     *
     * 1 <= node.val <= 100 for each node in the linked list and binary tree.
     * The given linked list will contain between 1 and 100 nodes.
     * The given binary tree will contain between 1 and 2500 nodes.
     */
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        return dfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean dfs(ListNode curNode, TreeNode root) {
        if (curNode == null) {
            return true;
        }
        if (root == null || root.val != curNode.val) {
            return false;
        }
        return dfs(curNode.next, root.left) || dfs(curNode.next, root.right);
    }
}
