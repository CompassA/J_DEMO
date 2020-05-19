package com.study.leetcode.tree;

import com.study.leetcode.linkedlist.ListNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

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

    /**
     * 98. Validate Binary Search Tree
     * Medium
     *
     * 3437
     *
     * 489
     *
     * Add to List
     *
     * Share
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
     * Medium
     *
     * 1524
     *
     * 79
     *
     * Add to List
     *
     * Share
     * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
     *
     * Basically, the deletion can be divided into two stages:
     *
     * Search for a node to remove.
     * If the node is found, delete the node.
     * Note: Time complexity should be O(height of tree).
     *
     * Example:
     *
     * root = [5,3,6,2,4,null,7]
     * key = 3
     *
     *     5
     *    / \
     *   3   6
     *  / \   \
     * 2   4   7
     *
     * Given key to delete is 3. So we find the node with value 3 and delete it.
     *
     * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
     *
     *     5
     *    / \
     *   4   6
     *  /     \
     * 2       7
     *
     * Another valid answer is [5,2,6,null,4,null,7].
     *
     *     5
     *    / \
     *   2   6
     *    \   \
     *     4   7
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
     * Easy
     *
     * 1851
     *
     * 55
     *
     * Add to List
     *
     * Share
     * Given two binary trees, write a function to check if they are the same or not.
     *
     * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
     *
     * Example 1:
     *
     * Input:     1         1
     *           / \       / \
     *          2   3     2   3
     *
     *         [1,2,3],   [1,2,3]
     *
     * Output: true
     * Example 2:
     *
     * Input:     1         1
     *           /           \
     *          2             2
     *
     *         [1,2],     [1,null,2]
     *
     * Output: false
     * Example 3:
     *
     * Input:     1         1
     *           / \       / \
     *          2   1     1   2
     *
     *         [1,2,1],   [1,1,2]
     *
     * Output: false
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
     * Easy
     *
     * 3645
     *
     * 86
     *
     * Add to List
     *
     * Share
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
     * Medium
     *
     * 2611
     *
     * 65
     *
     * Add to List
     *
     * Share
     * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
     *
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
     * Medium
     *
     * 1748
     *
     * 89
     *
     * Add to List
     *
     * Share
     * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
     *
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
     * Easy
     *
     * 2218
     *
     * 66
     *
     * Add to List
     *
     * Share
     * Given a binary tree, find its maximum depth.
     *
     * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     *
     * Note: A leaf is a node with no children.
     *
     * Example:
     *
     * Given binary tree [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its depth = 3.
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 105. Construct Binary Tree from Preorder and Inorder Traversal
     * Medium
     *
     * 2910
     *
     * 85
     *
     * Add to List
     *
     * Share
     * Given preorder and inorder traversal of a tree, construct the binary tree.
     *
     * Note:
     * You may assume that duplicates do not exist in the tree.
     *
     * For example, given
     *
     * preorder = [3,9,20,15,7]
     * inorder = [9,3,15,20,7]
     * Return the following binary tree:
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
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
     * Medium
     *
     * 1413
     *
     * 29
     *
     * Add to List
     *
     * Share
     * Given inorder and postorder traversal of a tree, construct the binary tree.
     *
     * Note:
     * You may assume that duplicates do not exist in the tree.
     *
     * For example, given
     *
     * inorder = [9,3,15,20,7]
     * postorder = [9,15,7,20,3]
     * Return the following binary tree:
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
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
     * Easy
     *
     * 1154
     *
     * 203
     *
     * Add to List
     *
     * Share
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
     *
     * 2072
     *
     * 196
     *
     * Add to List
     *
     * Share
     * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
     *
     * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
     *
     * Example:
     *
     * Given the sorted array: [-10,-3,0,5,9],
     *
     * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
     *
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
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
     * Easy
     *
     * 1205
     *
     * 642
     *
     * Add to List
     *
     * Share
     * Given a binary tree, find its minimum depth.
     *
     * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
     *
     * Note: A leaf is a node with no children.
     *
     * Example:
     *
     * Given binary tree [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its minimum depth = 2.
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
     * Easy
     *
     * 1639
     *
     * 460
     *
     * Add to List
     *
     * Share
     * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
     *
     * Note: A leaf is a node with no children.
     *
     * Example:
     *
     * Given the below binary tree and sum = 22,
     *
     *       5
     *      / \
     *     4   8
     *    /   / \
     *   11  13  4
     *  /  \      \
     * 7    2      1
     * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
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
     * Medium
     *
     * 1541
     *
     * 56
     *
     * Add to List
     *
     * Share
     * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
     *
     * Note: A leaf is a node with no children.
     *
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
}
