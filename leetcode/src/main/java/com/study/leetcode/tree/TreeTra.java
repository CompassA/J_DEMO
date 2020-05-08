package com.study.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
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

    private static class Call {
        boolean needPrint;
        TreeNode node;
        public Call(boolean _needPrint, TreeNode _node) {
            needPrint = _needPrint;
            node = _node;
        }
    }
}
