package com.study.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author fanqie
 * @date 2020/3/1
 */
public class MinimumMaximumPathToReachTheTarget {
    /**
     * 746. Min Cost Climbing Stairs
     * Easy
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
     * 931. Minimum Falling Path Sum
     * Medium
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

    public int minimumTotalOther(List<List<Integer>> triangle) {
        int row;
        if ((row = triangle.size()) == 0 || triangle.get(0).size() == 0) {
            return 0;
        }
        int col = row;
        int[] state = new int[col];
        state[0] = triangle.get(0).get(0);
        for (int i = 1; i < row; ++i) {
            int right = state[i-1] + triangle.get(i).get(i);
            for (int j = i-1; j > 0; --j) {
                state[j] = Math.min(state[j], state[j-1]) + triangle.get(i).get(j);
            }
            state[i] = right;
            state[0] += triangle.get(i).get(0);
        }

        int min = state[0];
        for (int i = 1; i < col; ++i) {
            if (min > state[i]) {
                min = state[i];
            }
        }
        return min;
    }

    /**
     * 300. Longest Increasing Subsequence
     * Medium
     * Given an unsorted array of integers, find the length of longest increasing subsequence.
     *
     * Example:
     *
     * Input: [10,9,2,5,3,7,101,18]
     * Output: 4
     * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
     * Note:
     *
     * There may be more than one LIS combination, it is only necessary for you to return the length.
     * Your algorithm should run in O(n2) complexity.
     * Follow up: Could you improve it to O(n log n) time complexity?
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) { return 0; }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < nums.length; ++i) {
            int max = 1;
            for (int j = i - 1; j >= 0; --j) {
                if (nums[j] < nums[i] && max < (dp[j] + 1)) { max = dp[j] + 1; }
            }
            dp[i] = max;
            if (res < max) { res = max; }
        }
        return res;
    }

    /**
     * 368. Largest Divisible Subset
     * Medium
     *
     * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:
     *
     * Si % Sj = 0 or Sj % Si = 0.
     *
     * If there are multiple solutions, return any subset is fine.
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums.length == 0) { return Collections.emptyList(); }
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        int[] pre = new int[nums.length];

        int maxIndex = 0;
        int max = 0;
        for (int i = 0; i < nums.length; ++i) {
            dp[i] = 1;
            pre[i] = -1;
            for (int j = i - 1; j >= 0; --j) {
                if (nums[i] % nums[j] == 0) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        pre[i] = j;
                    }
                }
            }
            if (dp[i] > max) { max = dp[i]; maxIndex = i; }
        }
        List<Integer> res = new ArrayList<>();
        int index = maxIndex;
        while (index != -1) {
            res.add(nums[index]);
            index = pre[index];
        }
        return res;
    }

    /**
     * 416. Partition Equal Subset Sum
     * Medium
     * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
     *
     * Note:
     *
     * Each of the array element will not exceed 100.
     * The array size will not exceed 200.
     *
     *
     * Example 1:
     *
     * Input: [1, 5, 11, 5]
     *
     * Output: true
     *
     * Explanation: The array can be partitioned as [1, 5, 5] and [11].
     *
     *
     * Example 2:
     *
     * Input: [1, 2, 3, 5]
     *
     * Output: false
     *
     * Explanation: The array cannot be partitioned into equal sum subsets.
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        boolean[] dp = new boolean[target+1];
        dp[0] = true;
        for (int num : nums) {
            for (int j = target; j >= num; --j) {
                dp[j] = dp[j] || dp[j-num];
            }
        }
        return dp[target];
    }

    /**
     * 303. Range Sum Query - Immutable
     * Easy
     * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
     *
     * Example:
     * Given nums = [-2, 0, 3, -5, 2, -1]
     *
     * sumRange(0, 2) -> 1
     * sumRange(2, 5) -> -1
     * sumRange(0, 5) -> -3
     * Note:
     * You may assume that the array does not change.
     * There are many calls to sumRange function.
     */
    class NumArray {

        private int[] dp;

        public NumArray(int[] nums) {
            dp = new int[nums.length];
            int sum = 0;
            for (int i = 0; i < nums.length; ++i) {
                sum += nums[i];
                dp[i] = sum;
            }
        }

        public int sumRange(int i, int j) {
            if (i == 0) {
                return dp[j];
            }
            return dp[j] - dp[i-1];
        }
    }

    /**
     * 304. Range Sum Query 2D - Immutable
     * Medium
     * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
     *
     * Range Sum Query 2D
     * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
     *
     * Example:
     * Given matrix = [
     *   [3, 0, 1, 4, 2],
     *   [5, 6, 3, 2, 1],
     *   [1, 2, 0, 1, 5],
     *   [4, 1, 0, 1, 7],
     *   [1, 0, 3, 0, 5]
     * ]
     *
     * sumRegion(2, 1, 4, 3) -> 8
     * sumRegion(1, 1, 2, 2) -> 11
     * sumRegion(1, 2, 2, 4) -> 12
     * Note:
     * You may assume that the matrix does not change.
     * There are many calls to sumRegion function.
     * You may assume that row1 ≤ row2 and col1 ≤ col2.
     */
    class NumMatrix {

        private int[][] dp;
        private int row;
        private int col;

        public NumMatrix(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                return;
            }
            row = matrix.length;
            col = matrix[0].length;
            dp = new int[row][col];
            dp[0][0] = matrix[0][0];
            for (int i = 1; i < row; ++i) {
                dp[i][0] = dp[i-1][0] + matrix[i][0];
            }
            for (int i = 1; i < col; ++i) {
                dp[0][i] = dp[0][i-1] + matrix[0][i];
            }
            for (int i = 1; i < row; ++i) {
                for (int j = 1; j < col; ++j) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            boolean rowOverLimit = row1 - 1 < 0;
            boolean colOverLimit = col1 - 1 < 0;
            return dp[row2][col2]
                    - (colOverLimit ? 0 : dp[row2][col1-1])
                    - (rowOverLimit ? 0 : dp[row1-1][col2])
                    + (rowOverLimit || colOverLimit ? 0 :dp[row1-1][col1-1]);
        }
    }

}
