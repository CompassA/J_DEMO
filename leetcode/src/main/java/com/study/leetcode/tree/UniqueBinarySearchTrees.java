package com.study.leetcode.tree;

/**
 * @author fanqie
 * @date 2020/3/22
 */
public class UniqueBinarySearchTrees {

    /**
     * 96. Unique Binary Search Trees
     * Medium
     *
     * 2638
     *
     * 98
     *
     * Add to List
     *
     * Share
     * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
     *
     * Example:
     *
     * Input: 3
     * Output: 5
     * Explanation:
     * Given n = 3, there are a total of 5 unique BST's:
     *
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     */
    public int numTrees(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        return (int) initCatalan(n)[n];
    }

    private long[] initCatalan(int n) {
        long[] catalan = new long[n + 1];
        catalan[1] = catalan[0] = 1;
        for (int i = 2; i <= n; ++i) {
            catalan[i] = catalan[i-1] * (4 * i - 2) / (i + 1) ;
        }
        return catalan;
    }

    private int otherWay(int n) {
        int[] catalan = new int[n+1];
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                catalan[i] += catalan[i - j] * catalan[j];
            }
        }
        return catalan[n];
    }
}
