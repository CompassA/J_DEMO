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
}
