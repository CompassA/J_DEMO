package com.study.leetcode.tree;

import com.study.leetcode.linkedlist.ListNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author fanqie
 * @date 2020/4/15
 */
public class TreeProblem {
    /**
     * 1367. Linked List in Binary Tree
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

    /**
     * 98. Validate Binary Search Tree
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     *
     * Assume a BST is defined as follows:
     *
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     *
     *
     * Example 1:
     *
     *     2
     *    / \
     *   1   3
     *
     * Input: [2,1,3]
     * Output: true
     * Example 2:
     *
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     *
     * Input: [5,1,4,null,null,3,6]
     * Output: false
     * Explanation: The root node's value is 5 but its right child's value is 4.
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE, new HashSet<>());
    }

    private boolean isValidBST(TreeNode root, int min, int max, Set<Integer> occuredNum) {
        if (root == null) {
            return true;
        }
        if (occuredNum.contains(root.val) || root.val < min || root.val > max) {
            return false;
        }
        occuredNum.add(root.val);
        return isValidBST(root.left, min, root.val, occuredNum)
                && isValidBST(root.right, root.val, max, occuredNum);
    }

    /**
     * 450. Delete Node in a BST
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        }

        if (root.left == null) {
            TreeNode res = root.right;
            root.right = null;
            return res;
        }
        if (root.right == null) {
            TreeNode res = root.left;
            root.left = null;
            return res;
        }

        TreeNode rightMinNode = root.right;
        while (rightMinNode.left != null) {
            rightMinNode = rightMinNode.left;
        }
        rightMinNode.right = deleteNode(root.right, rightMinNode.val);
        rightMinNode.left = root.left;
        root.left = null;
        root.right = null;
        return rightMinNode;
    }

    /**
     * 100. Same Tree
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 101. Symmetric Tree
     * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     *
     * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
     *
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     *
     *
     * But the following [1,2,2,null,3,null,3] is not:
     *
     *     1
     *    / \
     *   2   2
     *    \   \
     *    3    3
     *
     *
     * Follow up: Solve it both recursively and iteratively.
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode rootA, TreeNode rootB) {
        if (rootA == null && rootB == null) {
            return true;
        }
        if (rootA == null || rootB == null) {
            return false;
        }
        return rootA.val == rootB.val
                && isSymmetric(rootA.left, rootB.right)
                && isSymmetric(rootA.right, rootB.left);
    }

    /**
     * 102. Binary Tree Level Order Traversal
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its level order traversal as:
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int iterateTimes = queue.size();
            List<Integer> curLevelNums = new ArrayList<>();
            for (int i = 0; i < iterateTimes; ++i) {
                TreeNode curNode = queue.poll();
                curLevelNums.add(curNode.val);
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            res.add(curLevelNums);
        }
        return res;
    }

    /**
     * 103. Binary Tree Zigzag Level Order Traversal
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its zigzag level order traversal as:
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean reverseMode = false;
        while (!queue.isEmpty()) {
            int iterateTimes = queue.size();
            if (reverseMode) {
                List<Integer> curLevelNum = new LinkedList<>();
                for (int i = 0; i < iterateTimes; ++i) {
                    TreeNode curNode = queue.poll();
                    curLevelNum.add(0, curNode.val);
                    if (curNode.left != null) {
                        queue.offer(curNode.left);
                    }
                    if (curNode.right != null) {
                        queue.offer(curNode.right);
                    }
                }
                res.add(curLevelNum);
            } else {
                List<Integer> curLevelNum = new ArrayList<>();
                for (int i = 0; i < iterateTimes; ++i) {
                    TreeNode curNode = queue.poll();
                    curLevelNum.add(curNode.val);
                    if (curNode.left != null) {
                        queue.offer(curNode.left);
                    }
                    if (curNode.right != null) {
                        queue.offer(curNode.right);
                    }
                }
                res.add(curLevelNum);
            }
            reverseMode = !reverseMode;
        }
        return res;
    }

    /**
     * 104. Maximum Depth of Binary Tree
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 105. Construct Binary Tree from Preorder and Inorder Traversal
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
    }

    private TreeNode buildTree(int[] pre, int[] in, int preLeft, int preRight, int inLeft, int inRight) {
        if (preRight < preLeft || inRight < inLeft) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preLeft]);
        for (int i = inLeft; i <= inRight; ++i) {
            if (in[i] == root.val) {
                int leftNodeNum = i - inLeft;
                root.left = buildTree(pre, in, preLeft + 1, preLeft+leftNodeNum, inLeft, i - 1);
                root.right = buildTree(pre, in, preLeft + leftNodeNum+1, preRight, i + 1, inRight);
                break;
            }
        }
        return root;
    }

    /**
     * 106. Construct Binary Tree from Inorder and Postorder Traversal
     */
    public TreeNode buildTreeII(int[] inorder, int[] postorder) {
        return buildTree(inorder, postorder, 0, inorder.length-1, 0, postorder.length-1);
    }

    public TreeNode buildTreeII(int[] in, int[] post, int inL, int inR, int postL, int postR) {
        if (inL > inR || postL > postR) {
            return null;
        }
        TreeNode root = new TreeNode(post[postR]);
        for (int i = inL; i <= inR; ++i) {
            if (root.val == in[i]) {
                int rightNum = inR - i;
                root.left = buildTree(in, post, inL, i-1, postL, postR-rightNum-1);
                root.right = buildTree(in, post, i+1, inR, postR-rightNum, postR-1);
                break;
            }
        }
        return root;
    }

    /**
     * 107. Binary Tree Level Order Traversal II
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
     *
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its bottom-up level order traversal as:
     * [
     *   [15,7],
     *   [9,20],
     *   [3]
     * ]
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int iterateTimes = queue.size();
            List<Integer> curLevelNum = new ArrayList<>();
            for (int i = 0; i < iterateTimes; ++i) {
                TreeNode curNode = queue.poll();
                curLevelNum.add(curNode.val);
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            res.add(0, curLevelNum);
        }
        return res;
    }

    /**
     * 108. Convert Sorted Array to Binary Search Tree
     * Easy
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return create(nums, 0, nums.length-1);
    }

    private TreeNode create(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = create(nums, left, mid-1);
        root.right = create(nums, mid+1, right);
        return root;
    }

    /**
     * 111. Minimum Depth of Binary Tree
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int curLevel = 1;
        int res = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int curLevelNum = queue.size();
            for (int i = 0; i < curLevelNum; ++i) {
                TreeNode curNode = queue.poll();
                if (curNode.left == null && curNode.right == null) {
                    res = Math.min(res, curLevel);
                } else {
                    if (curNode.left != null) {
                        queue.offer(curNode.left);
                    }
                    if (curNode.right != null) {
                        queue.offer(curNode.right);
                    }
                }
            }
            curLevel++;
        }
        return res;
    }

    public int minDepthII(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    /**
     * 112. Path Sum
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        int subPathSum = sum - root.val;
        return hasPathSum(root.left, subPathSum) || hasPathSum(root.right, subPathSum);
    }

    /**
     * 113. Path Sum II
     * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
     * Example:
     *
     * Given the below binary tree and sum = 22,
     *
     *       5
     *      / \
     *     4   8
     *    /   / \
     *   11  13  4
     *  /  \    / \
     * 7    2  5   1
     * Return:
     *
     * [
     *    [5,4,11,2],
     *    [5,8,4,5]
     * ]
     */
    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            if (root == null) {
                return res;
            }
            findSum(root, sum, new ArrayList<>());
            return res;
        }

        private void findSum(TreeNode root, int sum, List<Integer> path) {
            path.add(root.val);
            if (root.left == null && root.right == null) {
                if (root.val == sum) {
                    res.add(new ArrayList<>(path));
                }
                path.remove(path.size()-1);
                return;
            }

            int subSum = sum - root.val;
            if (root.left != null) {
                findSum(root.left, subSum, path);
            }
            if (root.right != null) {
                findSum(root.right, subSum, path);
            }
            path.remove(path.size()-1);
        }
    }

    /**
     * 199. Binary Tree Right Side View
     * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
     *
     * Example:
     *
     * Input: [1,2,3,null,5,null,4]
     * Output: [1, 3, 4]
     * Explanation:
     *
     *    1            <---
     *  /   \
     * 2     3         <---
     *  \     \
     *   5     4       <---
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            res.add(queue.peek().val);
            for (int i = 0; i < levelNum; ++i) {
                TreeNode popNode = queue.poll();
                if (popNode.right != null) {
                    queue.offer(popNode.right);
                }
                if (popNode.left != null) {
                    queue.offer(popNode.left);
                }
            }
        }
        return res;
    }

    public List<Integer> rightSideViewSolution2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, 1, res);
        return res;
    }

    private void dfs(TreeNode root, int level, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (level > res.size()) {
            res.add(root.val);
        }
        dfs(root.right, level+1, res);
        dfs(root.left, level+1, res);
    }

    /**
     * 222. Count Complete Tree Nodes
     * Given a complete binary tree, count the number of nodes.
     *
     * Note:
     *
     * Definition of a complete binary tree from Wikipedia:
     * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
     *
     * Example:
     *
     * Input:
     *     1
     *    / \
     *   2   3
     *  / \  /
     * 4  5 6
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode left = root;
        TreeNode right = root;
        int height = 0;
        while (right != null) {
            left = left.left;
            right = right.right;
            ++height;
        }
        if (left == null) {
            return (1 << height) - 1;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
     * 226. Invert Binary Tree
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        TreeNode leftSubRoot = invertTree(root.left);
        TreeNode rightSubRoot = invertTree(root.right);
        root.left = rightSubRoot;
        root.right = leftSubRoot;
        return root;
    }

    /**
     * 230. Kth Smallest Element in a BST
     * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
     */
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        int cnt = 0;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.offerLast(root);
                root = root.left;
            }
            TreeNode cur = stack.pollLast();
            ++cnt;
            if (cnt == k) {
                return cur.val;
            }
            root = cur.right;
        }
        return -1;
    }

    /**
     * 235. Lowest Common Ancestor of a Binary Search Tree
     * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
     *
     * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
     *
     * Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        return root;
    }

    /**
     * 236. Lowest Common Ancestor of a Binary Tree
     */
    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode leftRes = lowestCommonAncestorII(root.left, p, q);
        TreeNode rightRes = lowestCommonAncestorII(root.right, p, q);
        if (leftRes != null && rightRes != null) {
            return root;
        }
        if (leftRes != null) {
            return leftRes;
        }
        return rightRes;
    }

    /**
     * 257. Binary Tree Paths
     * Given a binary tree, return all root-to-leaf paths.
     * Input:
     *
     *    1
     *  /   \
     * 2     3
     *  \
     *   5
     *
     * Output: ["1->2->5", "1->3"]
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfs(res, new ArrayDeque<>(), root);
        return res;
    }

    private void dfs(List<String> res, Deque<Integer> path, TreeNode root) {
        path.offerLast(root.val);
        if (root.left == null && root.right == null) {
            StringBuilder builder = new StringBuilder();
            int cnt = 0;
            for (int pathElem : path) {
                builder.append(pathElem);
                ++cnt;
                if (cnt != path.size()) {
                    builder.append("->");
                }
            }
            res.add(builder.toString());
            path.pollLast();
            return;
        }
        if (root.left != null) {
            dfs(res, path, root.left);
        }
        if (root.right != null) {
            dfs(res, path, root.right);
        }
        path.pollLast();
    }

    /**
     * 160. Intersection of Two Linked Lists
     * Easy
     *
     * Write a program to find the node at which the intersection of two singly linked lists begins.
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        while (curA != null && curB != null) {
            if (curA == curB) {
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
            if (curA == null && curB == null) {
                return null;
            } else if (curA == null) {
                curA = headB;
            } else if (curB == null) {
                curB = headA;
            }
        }
        return null;
    }

    /**
     * 449. Serialize and Deserialize BST
     */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder builder = new StringBuilder();
            serialize(root, builder);
            return builder.toString();
        }

        private void serialize(TreeNode root, StringBuilder builder) {
            if (root == null) {
                builder.append("N&");
                return;
            }
            builder.append(root.val).append("&");
            serialize(root.left, builder);
            serialize(root.right, builder);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Queue<String> queue = new LinkedList<>();
            String[] tokens = data.split("&");
            for (String str : tokens) {
                queue.offer(str);
            }
            return deserialize(queue);
        }

        private TreeNode deserialize(Queue<String> tokens) {
            if (tokens.isEmpty()) {
                return null;
            }
            String peek = tokens.poll();
            if (peek.equals("N")) {
                return null;
            }

            TreeNode node = new TreeNode(Integer.parseInt(peek));
            node.left = deserialize(tokens);
            node.right = deserialize(tokens);
            return node;
        }
    }
}
