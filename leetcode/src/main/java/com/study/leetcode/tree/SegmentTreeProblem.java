package com.study.leetcode.tree;

/**
 * @author fanqie
 * @date 2020/5/19
 */
public class SegmentTreeProblem {

    /**
     * 307. Range Sum Query - Mutable
     * Medium
     *
     * 1183
     *
     * 77
     *
     * Add to List
     *
     * Share
     * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
     *
     * The update(i, val) function modifies nums by updating the element at index i to val.
     *
     * Example:
     *
     * Given nums = [1, 3, 5]
     *
     * sumRange(0, 2) -> 9
     * update(1, 2)
     * sumRange(0, 2) -> 8
     * Note:
     *
     * The array is only modifiable by the update function.
     * You may assume the number of calls to update and sumRange function is distributed evenly.
     */
    private static class NumArray {

        private int[] tree;
        private int[] nums;

        public NumArray(int[] nums) {
            if (nums.length == 0) {
                return;
            }
            this.nums = nums;
            tree = new int[4 * nums.length];
            build(0, 0, nums.length-1);
        }

        public void update(int i, int val) {
            update(0, 0, nums.length-1, i, val);
        }

        public int sumRange(int i, int j) {
            return query(0, 0, nums.length-1, i, j);
        }

        private void build(int treeIndex, int l, int r) {
            if (l == r) {
                tree[treeIndex] = nums[l];
                return;
            }
            int mid = l + (r - l) / 2;
            int leftChildIndex = treeIndex*2+1;
            int rightChildIndex = treeIndex*2+2;
            build(leftChildIndex, l, mid);
            build(rightChildIndex, mid+1, r);
            tree[treeIndex] = tree[leftChildIndex] + tree[rightChildIndex];
        }

        private void update(int treeIndex, int l, int r, int index, int num) {
            if (l == r) {
                tree[treeIndex] = num;
                return;
            }
            int mid = l + (r - l) / 2;
            int leftChildIndex = treeIndex*2+1;
            int rightChildIndex = treeIndex*2+2;
            if (index >= mid + 1) {
                update(rightChildIndex, mid+1, r, index, num);
            } else {
                update(leftChildIndex, l, mid, index, num);
            }
            tree[treeIndex] = tree[leftChildIndex] + tree[rightChildIndex];
        }

        private int query(int tIndex, int l, int r, int qL, int qR) {
            if (l == qL && r == qR) {
                return tree[tIndex];
            }
            int mid = l + (r - l) / 2;
            int leftChildIndex = tIndex*2+1;
            int rightChildIndex = tIndex*2+2;
            if (qR < mid + 1) {
                return query(leftChildIndex, l, mid, qL, qR);
            } else if (qL > mid) {
                return query(rightChildIndex, mid+1, r, qL, qR);
            }
            int leftRes = query(leftChildIndex, l, mid, qL, mid);
            int rightRes = query(rightChildIndex, mid+1, r, mid+1, qR);
            return leftRes + rightRes;
        }
    }

    static class NumArrayII {

        private SegmentTreeNode root;
        public NumArrayII(int[] nums) {
            if (nums.length == 0) {
                return;
            }
            root = build(nums, 0, nums.length-1);
        }

        public void update(int i, int val) {
            update(root, i, val);
        }

        public int sumRange(int i, int j) {
            return query(root, i, j);
        }

        private SegmentTreeNode build(int[] nums, int l, int r) {
            if (l > r) {
                return null;
            }
            if (l == r) {
                return new SegmentTreeNode(l, r, nums[l]);
            }
            int mid = l + (r - l) / 2;
            SegmentTreeNode root = new SegmentTreeNode(l, r, 0);
            root.lChild = build(nums, l, mid);
            root.rChild = build(nums, mid+1, r);
            if (root.lChild != null) {
                root.sum += root.lChild.sum;
            }
            if (root.rChild != null) {
                root.sum += root.rChild.sum;
            }
            return root;
        }

        private int query(SegmentTreeNode root, int qL, int qR) {
            if (root == null) {
                return 0;
            }
            if (root.l == qL && root.r == qR) {
                return root.sum;
            }
            int mid = root.l + (root.r - root.l) / 2;
            if (mid < qL) {
                return query(root.rChild, qL, qR);
            } else if (mid+1 > qR) {
                return query(root.lChild, qL, qR);
            }
            int leftRes = query(root.lChild, qL, mid);
            int rightRes = query(root.rChild, mid+1, qR);
            return leftRes + rightRes;
        }

        private void update(SegmentTreeNode root, int index, int e) {
            if (root == null) {
                return;
            }
            if (root.l == root.r) {
                root.sum = e;
                return;
            }
            int mid = root.l + (root.r - root.l) / 2;
            if (mid < index) {
                update(root.rChild, index, e);
            } else {
                update(root.lChild, index, e);
            }
            root.sum = (root.lChild == null ? 0 : root.lChild.sum) +
                    (root.rChild == null ? 0 : root.rChild.sum);
        }

        private static class SegmentTreeNode {
            int l;
            int r;
            int sum;
            SegmentTreeNode lChild;
            SegmentTreeNode rChild;
            public SegmentTreeNode(int _l, int _r, int _sum) {
                l = _l;
                r = _r;
                sum = _sum;
            }
        }
    }
}
