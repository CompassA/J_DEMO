package com.study.leetcode.dp;

import java.util.Arrays;

/**
 * @author fanqie
 * @date 2020/3/25
 */
public class DistinctWays {

    /**
     * 70. Climbing Stairs
     * Easy
     *
     * 3555
     *
     * 116
     *
     * Add to List
     *
     * Share
     * You are climbing a stair case. It takes n steps to reach to the top.
     *
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     *
     * Note: Given n will be a positive integer.
     *
     * Example 1:
     *
     * Input: 2
     * Output: 2
     * Explanation: There are two ways to climb to the top.
     * 1. 1 step + 1 step
     * 2. 2 steps
     * Example 2:
     *
     * Input: 3
     * Output: 3
     * Explanation: There are three ways to climb to the top.
     * 1. 1 step + 1 step + 1 step
     * 2. 1 step + 2 steps
     * 3. 2 steps + 1 step
     */
    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int[] state = new int[n+1];
        state[0] = state[1] = 1;
        for (int i = 2; i <= n; ++i) {
            state[i] = state[i-1] + state[i-2];
        }
        return state[n];
    }

    /**
     * 62. Unique Paths
     * Medium
     *
     * 2523
     *
     * 180
     *
     * Add to List
     *
     * Share
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     *
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     *
     * How many possible unique paths are there?
     *
     *
     * Above is a 7 x 3 grid. How many possible unique paths are there?
     *
     *
     *
     * Example 1:
     *
     * Input: m = 3, n = 2
     * Output: 3
     * Explanation:
     * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
     * 1. Right -> Right -> Down
     * 2. Right -> Down -> Right
     * 3. Down -> Right -> Right
     * Example 2:
     *
     * Input: m = 7, n = 3
     * Output: 28
     *
     *
     * Constraints:
     *
     * 1 <= m, n <= 100
     * It's guaranteed that the answer will be less than or equal to 2 * 10 ^ 9.
     */
    public int uniquePaths(int m, int n) {
        int[][] ways = new int[m][n];
        Arrays.fill(ways[0], 1);
        for (int i = 0; i < m; ++i) {
            ways[i][0] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                ways[i][j] = ways[i-1][j] + ways[i][j-1];
            }
        }
        return ways[m-1][n-1];
    }

    /**
     * 63. Unique Paths II
     * Medium
     *
     * 1370
     *
     * 217
     *
     * Add to List
     *
     * Share
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     *
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     *
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     *
     *
     *
     * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
     *
     * Note: m and n will be at most 100.
     *
     * Example 1:
     *
     * Input:
     * [
     *   [0,0,0],
     *   [0,1,0],
     *   [0,0,0]
     * ]
     * Output: 2
     * Explanation:
     * There is one obstacle in the middle of the 3x3 grid above.
     * There are two ways to reach the bottom-right corner:
     * 1. Right -> Right -> Down -> Down
     * 2. Down -> Down -> Right -> Right
     */
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (m == 0 || n == 0 || grid[m-1][n-1] == 1) {
            return 0;
        }

        int[][] state = new int[m][n];
        for (int i = 0; i < n && grid[0][i] != 1; ++i) {
            state[0][i] = 1;
        }
        for (int i = 0; i < m && grid[i][0] != 1; ++i) {
            state[i][0] = 1;
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (grid[i][j] == 0) {
                    state[i][j] = state[i][j-1] + state[i-1][j];
                }
            }
        }

        return state[m-1][n-1];
    }

    /**
     * 91. Decode Ways
     * Medium
     *
     * 2208
     *
     * 2453
     *
     * Add to List
     *
     * Share
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:
     *
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * Given a non-empty string containing only digits, determine the total number of ways to decode it.
     *
     * Example 1:
     *
     * Input: "12"
     * Output: 2
     * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
     * Example 2:
     *
     * Input: "226"
     * Output: 3
     * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
     */
    public int numDecodings(String s) {
        if (s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int[] state = new int[s.length()+1];
        state[1] = state[0] = 1;
        for (int i = 2; i <= s.length(); ++i) {
            if (s.charAt(i-1) != '0') {
                state[i] += state[i-1];
            }
            int num = (s.charAt(i-2) - '0') * 10 + s.charAt(i-1) - '0';
            if (num > 9 && num < 27) {
                state[i] += state[i-2];
            }
        }
        return state[s.length()];
    }

    /**
     * 494. Target Sum
     * Medium
     *
     * 2092
     *
     * 94
     *
     * Add to List
     *
     * Share
     * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
     *
     * Find out how many ways to assign symbols to make sum of integers equal to target S.
     *
     * Example 1:
     * Input: nums is [1, 1, 1, 1, 1], S is 3.
     * Output: 5
     * Explanation:
     *
     * -1+1+1+1+1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * +1+1+1-1+1 = 3
     * +1+1+1+1-1 = 3
     *
     * There are 5 ways to assign symbols to make the sum of nums be target 3.
     * Note:
     * The length of the given array is positive and will not exceed 20.
     * The sum of elements in the given array will not exceed 1000.
     * Your output answer is guaranteed to be fitted in a 32-bit integer.
     */
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (S > sum || S < -sum) {
            return 0;
        }
        int offset = sum;
        int max = sum + offset;
        int[][] dp = new int[nums.length+1][offset+sum+1];
        dp[0][0 + offset] = 1;
        for (int i = 1; i <= nums.length; ++i) {
            int curNum = nums[i-1];
            for (int j = 0; j <= max; ++j) {
                dp[i][j] = (j - curNum < 0 ? 0 : dp[i-1][j-curNum]) + (j + curNum > max ? 0 : dp[i-1][j+curNum]);
            }
        }
        return dp[nums.length][S + offset];
    }

    /**
     * 1444 切披萨
     */
    private static class Solution1444 {
        int row;
        int col;
        int[][] preSum;
        Integer[][][] dp;
        public int ways(String[] pizza, int k) {
            if (pizza.length == 0) {
                return 0;
            }
            row = pizza.length;
            col = pizza[0].length();
            preSum = new int[row+1][col+1];
            for (int i = row - 1; i >=0 ; --i) {
                for (int j = col - 1; j >= 0; --j) {
                    preSum[i][j] = preSum[i+1][j] + preSum[i][j+1] - preSum[i+1][j+1];
                    if (pizza[i].charAt(j) == 'A') {
                        preSum[i][j] += 1;
                    }
                }
            }
            dp = new Integer[k][pizza.length][pizza[0].length()];
            return dfs(k-1, 0, 0);
        }

        private int dfs(int k, int x, int y) {
            if (preSum[x][y] == 0) {
                return 0;
            }
            if (k == 0) {
                return 1;
            }
            if (dp[k][x][y] != null) {
                return dp[k][x][y];
            }
            int res = 0;
            for (int i = x + 1; i < row; ++i) {
                if (preSum[x][y] - preSum[i][y] > 0) {
                    res = (res + dfs(k-1, i, y)) % 1000000007;
                }
            }
            for (int i = y + 1; i < col; ++i) {
                if (preSum[x][y] - preSum[x][i] > 0) {
                    res = (res + dfs(k-1, x, i)) % 1000000007;
                }
            }
            dp[k][x][y] = res;
            return res;
        }
    }
}
