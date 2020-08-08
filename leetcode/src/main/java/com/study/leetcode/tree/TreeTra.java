package com.study.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author fanqie
 * @date 2020/5/8
 */
public class TreeTra {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Call> stack = new ArrayDeque<>();
        stack.offerLast(new Call(false, root));
        while (!stack.isEmpty()) {
            Call curCall = stack.pollLast();
            TreeNode curNode = curCall.node;
            if (curCall.needPrint) {
                res.add(curNode.val);
            } else {
                if (curNode.right != null) {
                    stack.offerLast(new Call(false, curNode.right));
                }
                if (curNode.left != null) {
                    stack.offerLast(new Call(false, curNode.left));
                }
                stack.offerLast(new Call(true, curNode));
            }
        }
        return res;
    }

    public List<Integer> preorderTraversalMorris(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode curPre = cur.left;
                while (curPre.right != null && curPre.right != cur) {
                    curPre = curPre.right;
                }
                //第一次遍历到此节点
                if (curPre.right == null) {
                    res.add(cur.val);
                    curPre.right = cur;
                    cur = cur.left;
                }
                //第二次遍历到此节点
                else {
                    curPre.right = null;
                    cur = cur.right;
                }
            } else {
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Call> stack = new ArrayDeque<>();
        stack.offerLast(new Call(false, root));
        while (!stack.isEmpty()) {
            Call curCall = stack.pollLast();
            TreeNode curNode = curCall.node;
            if (curCall.needPrint) {
                res.add(curCall.node.val);
            } else {
                if (curNode.right != null) {
                    stack.offerLast(new Call(false, curNode.right));
                }
                stack.offerLast(new Call(true, curNode));
                if (curNode.left != null) {
                    stack.offerLast(new Call(false, curNode.left));
                }
            }
        }
        return res;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Call> stack = new ArrayDeque<>();
        stack.offerLast(new Call(false, root));
        while (!stack.isEmpty()) {
            Call curCall = stack.pollLast();
            TreeNode curNode = curCall.node;
            if (curCall.needPrint) {
                res.add(curNode.val);
            } else {
                stack.offerLast(new Call(true, curNode));
                if (curNode.right != null) {
                    stack.offerLast(new Call(false, curNode.right));
                }
                if (curNode.left != null) {
                    stack.offerLast(new Call(false, curNode.left));
                }
            }
        }
        return res;
    }

    public List<Integer> postorderTraversalMorris(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeNode dummy = new TreeNode(-1);
        dummy.left = root;
        TreeNode cur = dummy;

        while (cur != null) {
            if (cur.left != null) {
                TreeNode curPre = cur.left;
                while (curPre.right != null && curPre.right != cur) {
                    curPre = curPre.right;
                }
                if (curPre.right == null) {
                    curPre.right = cur;
                    cur = cur.left;
                } else {
                    res.addAll(reverseTraversal(cur.left, curPre));
                    curPre.right = null;
                    cur = cur.right;
                }
            } else {
                cur = cur.right;
            }
        }
        return res;
    }

    private List<Integer> reverseTraversal(TreeNode begin, TreeNode end) {
        LinkedList<Integer> res = new LinkedList<>();
        TreeNode cur = begin;
        while (true) {
            res.addFirst(cur.val);
            if (cur == end) {
                break;
            }
            cur = cur.right;
        }
        return res;
    }

    private static class Call {
        boolean needPrint;
        TreeNode node;
        public Call(boolean _needPrint, TreeNode _node) {
            needPrint = _needPrint;
            node = _node;
        }
    }

    public List<Integer> inorderTraversalMorris(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode curPre = cur.left;
                while (curPre.right != null && curPre.right != cur) {
                    curPre = curPre.right;
                }
                if (curPre.right == null) {
                    curPre.right = cur;
                    cur = cur.left;
                } else {
                    curPre.right = null;
                    res.add(cur.val);
                    cur = cur.right;
                }
            } else {
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }

    /**
     * 173. Binary Search Tree Iterator
     * Medium
     *
     * 2280
     *
     * 268
     *
     * Add to List
     *
     * Share
     * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
     *
     * Calling next() will return the next smallest number in the BST.
     *
     *
     *
     * Example:
     *
     *
     *
     * BSTIterator iterator = new BSTIterator(root);
     * iterator.next();    // return 3
     * iterator.next();    // return 7
     * iterator.hasNext(); // return true
     * iterator.next();    // return 9
     * iterator.hasNext(); // return true
     * iterator.next();    // return 15
     * iterator.hasNext(); // return true
     * iterator.next();    // return 20
     * iterator.hasNext(); // return false
     *
     *
     * Note:
     *
     * next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
     * You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
     */
    class BSTIterator {

        private TreeNode curHead = new TreeNode(-1);;

        public BSTIterator(TreeNode root) {
            if (root == null) {
                return;
            }
            TreeNode curTail = curHead;
            Deque<TreeNode> stack = new ArrayDeque<>();
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.offerLast(root);
                    root = root.left;
                }
                root = stack.pollLast();
                curTail.right = root;
                curTail = root;
                root = root.right;
            }
            curTail.right = null;
        }

        /** @return the next smallest number */
        public int next() {
            int res = curHead.right.val;
            curHead = curHead.right;
            return res;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return curHead.right != null;
        }
    }

}
