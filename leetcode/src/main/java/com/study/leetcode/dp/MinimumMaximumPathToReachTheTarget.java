package com.study.leetcode.dp;

import java.util.Arrays;
import java.util.List;

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

    /**
     * 322. Coin Change
     * Medium
     *
     * 3168
     *
     * 105
     *
     * Add to List
     *
     * Share
     * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
     *
     * Example 1:
     *
     * Input: coins = [1, 2, 5], amount = 11
     * Output: 3
     * Explanation: 11 = 5 + 5 + 1
     * Example 2:
     *
     * Input: coins = [2], amount = 3
     * Output: -1
     * Note:
     * You may assume that you have an infinite number of each kind of coin.
     */
    public int coinChange(int[] coins, int amount) {
        final int impossible = Integer.MAX_VALUE;
        int[] pack = new int[amount+1];
        Arrays.fill(pack, impossible);
        pack[0] = 0;

        for (final int coin : coins) {
            for (int i = coin; i <= amount; ++i) {
                if (pack[i - coin] != impossible) {
                    pack[i] = Math.min(pack[i - coin] + 1, pack[i]);
                }
            }
        }
        return pack[amount] == impossible ? -1 : pack[amount];
    }

    /**
     * 518. Coin Change 2
     * Medium
     *
     * 1291
     *
     * 50
     *
     * Add to List
     *
     * Share
     * You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.
     *
     *
     *
     * Example 1:
     *
     * Input: amount = 5, coins = [1, 2, 5]
     * Output: 4
     * Explanation: there are four ways to make up the amount:
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     * Example 2:
     *
     * Input: amount = 3, coins = [2]
     * Output: 0
     * Explanation: the amount of 3 cannot be made up just with coins of 2.
     * Example 3:
     *
     * Input: amount = 10, coins = [10]
     * Output: 1
     *
     *
     * Note:
     *
     * You can assume that
     *
     * 0 <= amount <= 5000
     * 1 <= coin <= 5000
     * the number of coins is less than 500
     * the answer is guaranteed to fit into signed 32-bit integer
     */
    public int change(int amount, int[] coins) {
        int[] pack = new int[amount+1];
        Arrays.fill(pack, 0);
        pack[0] = 1;

        for (final int coin : coins) {
            for (int i = coin; i <= amount; ++i) {
                pack[i] += pack[i - coin];
            }
        }

        return pack[amount];
    }

    /**
     * 931. Minimum Falling Path Sum
     * Medium
     *
     * 491
     *
     * 49
     *
     * Add to List
     *
     * Share
     * Given a square array of integers A, we want the minimum sum of a falling path through A.
     *
     * A falling path starts at any element in the first row, and chooses one element from each row.  The next row's choice must be in a column that is different from the previous row's column by at most one.
     *
     *
     *
     * Example 1:
     *
     * Input: [[1,2,3],[4,5,6],[7,8,9]]
     * Output: 12
     * Explanation:
     * The possible falling paths are:
     * [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
     * [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
     * [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
     * The falling path with the smallest sum is [1,4,7], so the answer is 12.
     *
     *
     *
     * Note:
     *
     * 1 <= A.length == A[0].length <= 100
     * -100 <= A[i][j] <= 100
     */
    public int minFallingPathSum(int[][] A) {
        for (int i = 1; i < A.length; ++i) {
            for (int j = 0; j < A.length; ++j) {
                int preRow = i - 1;
                int left = Math.max(0, j - 1);
                int right = Math.min(j + 1, A.length - 1);
                A[i][j] += Math.min(Math.min(A[preRow][left], A[preRow][right]), A[preRow][j]);
            }
        }
        return Arrays.stream(A[A.length-1]).min().getAsInt();
    }

    /**
     * 120. Triangle
     * Medium
     *
     * 1637
     *
     * 197
     *
     * Add to List
     *
     * Share
     * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
     *
     * For example, given the following triangle
     *
     * [
     *      [2],
     *     [3,4],
     *    [6,5,7],
     *   [4,1,8,3]
     * ]
     * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
     *
     * Note:
     *
     * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int row;
        if ((row = triangle.size()) == 0) {
            return 0;
        }
        int col = row;
        int[] state = new int[col + 1];
        for (int i = row - 1; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                state[j] = Math.min(state[j], state[j+1]) + triangle.get(i).get(j);
            }
        }
        return state[0];
    }
//    public int minimumTotal(List<List<Integer>> triangle) {
//        int row;
//        if ((row = triangle.size()) == 0 || triangle.get(0).size() == 0) {
//            return 0;
//        }
//        int col = row;
//        int[] state = new int[col];
//        state[0] = triangle.get(0).get(0);
//        for (int i = 1; i < row; ++i) {
//            int right = state[i-1] + triangle.get(i).get(i);
//            for (int j = i-1; j > 0; --j) {
//                state[j] = Math.min(state[j], state[j-1]) + triangle.get(i).get(j);
//            }
//            state[i] = right;
//            state[0] += triangle.get(i).get(0);
//        }
//
//        int min = state[0];
//        for (int i = 1; i < col; ++i) {
//            if (min > state[i]) {
//                min = state[i];
//            }
//        }
//        return min;
//    }
}
