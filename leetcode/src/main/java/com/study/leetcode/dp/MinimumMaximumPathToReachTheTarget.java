package com.study.leetcode.dp;

/**
 * @author fanqie
 * @date 2020/3/1
 */
public class MinimumMaximumPathToReachTheTarget {
    /**
     * 746. Min Cost Climbing Stairs
     * Easy
     *
     * 1570
     *
     * 360
     *
     * Add to List
     *
     * Share
     * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
     *
     * Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.
     *
     * Example 1:
     * Input: cost = [10, 15, 20]
     * Output: 15
     * Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
     * Example 2:
     * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
     * Output: 6
     * Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
     * Note:
     * cost will have a length in the range [2, 1000].
     * Every cost[i] will be an integer in the range [0, 999].
     */
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length == 0) {
            return 0;
        }
        if (cost.length == 1) {
            return cost[0];
        }
        if (cost.length == 2) {
            return Math.min(cost[0], cost[1]);
        }

        final int[] state = initState(cost);
        for (int i = 2; i < cost.length; ++i) {
            final int newState = Math.min(state[0], state[1]) + cost[i];
            state[0] = state[1];
            state[1] = newState;
        }

        return Math.min(state[0], state[1]);
    }

    private int[] initState(final int[] cost) {
        final int[] state = new int[2];
        state[0] = cost[0];
        state[1] = cost[1];
        return state;
    }

    /**
     * 64. Minimum Path Sum
     * Medium
     *
     * 2141
     *
     * 48
     *
     * Add to List
     *
     * Share
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
     *
     * Note: You can only move either down or right at any point in time.
     *
     * Example:
     *
     * Input:
     * [
     *   [1,3,1],
     *   [1,5,1],
     *   [4,2,1]
     * ]
     * Output: 7
     * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
     */
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        final int[] state = initState(grid);
        for (int i = 1; i < grid.length; ++i) {
            state[0] += grid[i][0];
            for (int j = 1; j < grid[i].length; ++j) {
                state[j] = Math.min(state[j-1], state[j]) + grid[i][j];
            }
        }
        return state[grid[0].length - 1];
    }

    private int[] initState(int[][] grid) {
        final int[] state = new int[grid[0].length];
        state[0] = grid[0][0];
        for (int i = 1; i < grid[0].length; ++i) {
            state[i] = state[i-1] + grid[0][i];
        }
        return state;
    }

    /**
     * 1314. Matrix Block Sum
     * Medium
     *
     * 114
     *
     * 20
     *
     * Add to List
     *
     * Share
     * Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.
     *
     *
     * Example 1:
     *
     * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
     * Output: [[12,21,16],[27,45,33],[24,39,28]]
     * Example 2:
     *
     * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
     * Output: [[45,45,45],[45,45,45],[45,45,45]]
     *
     *
     * Constraints:
     *
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n, K <= 100
     * 1 <= mat[i][j] <= 100
     */
    public int[][] matrixBlockSum(int[][] mat, int K) {
        final int[][] sum = new int[mat.length][mat[0].length];
        sum[0][0] = mat[0][0];
        for (int i = 1; i < mat[0].length; ++i) {
            sum[0][i] = sum[0][i-1] + mat[0][i];
        }
        for (int j = 1; j < mat.length; ++j) {
            sum[j][0] = sum[j-1][0] + mat[j][0];
        }
        for (int i = 1; i < mat.length; ++i) {
            for (int j = 1; j < mat[0].length; ++j) {
                sum[i][j] = sum[i-1][j] + sum[i][j-1] + mat[i][j] - sum[i-1][j-1];
            }
        }
        final int[][] res = new int[mat.length][mat[0].length];
        for (int i = 0; i < res.length; ++i) {
            for (int j = 0; j < res[0].length; ++j) {
                final int leftTopX = Math.max(i - K, 0);
                final int leftTopY = Math.max(j - K, 0);
                final int rightBottomX = Math.min(i + K, mat.length - 1);
                final int rightBottomY = Math.min(j + K, mat[0].length - 1);
                res[i][j] = calcBlockSum(sum, leftTopX, leftTopY, rightBottomX, rightBottomY);
            }
        }
        return res;
    }

    private int calcBlockSum(final int[][] sum, final int leftTopX, final int leftTopY,
                             final int rightBottomX, final int rightBottomY) {
        int res = sum[rightBottomX][rightBottomY];
        final int leftSubY = leftTopY - 1;
        if (leftSubY >= 0) {
            res -= sum[rightBottomX][leftSubY];
        }
        final int topSubX = leftTopX - 1;
        if (topSubX >= 0) {
            res -= sum[topSubX][rightBottomY];
        }
        if (leftSubY >= 0 && topSubX >= 0) {
            res += sum[topSubX][leftSubY];
        }
        return res;
    }
}