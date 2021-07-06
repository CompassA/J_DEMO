package com.study.leetcode.dp;

import java.util.Arrays;
import java.util.List;

/**
 * @author fanqie
 * @date 2020/6/1
 */
public class DpMatrix {

    /**
     * 85. Maximal Rectangle
     * Hard
     *
     * 2507
     *
     * 63
     *
     * Add to List
     *
     * Share
     * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
     *
     * Example:
     *
     * Input:
     * [
     *   ["1","0","1","0","0"],
     *   ["1","0","1","1","1"],
     *   ["1","1","1","1","1"],
     *   ["1","0","0","1","0"]
     * ]
     * Output: 6
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[] left = new int[col];
        int[] right = new int[col];
        int[] height = new int[col];
        Arrays.fill(right, col);
        int res = 0;
        for (int i = 0; i < row; ++i) {
            int leftBoundary = 0;
            for (int j = 0; j < col; ++j) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                    left[j] = Math.max(leftBoundary, left[j]);
                } else {
                    height[j] = 0;
                    left[j] = 0;
                    leftBoundary = j + 1;
                }
            }
            int rightBoundary = col;
            for (int j = col-1; j >= 0; --j) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(rightBoundary, right[j]);
                } else {
                    right[j] = col;
                    rightBoundary = j;
                }
            }
            for (int j = 0; j < col; ++j) {
                if (matrix[i][j] == '1') {
                    res = Math.max(res, (right[j]-left[j]) * height[j]);
                }
            }
        }
        return res;
    }

    /**
     * 84. Largest Rectangle in Histogram
     * Hard
     *
     * 3315
     *
     * 77
     *
     * Add to List
     *
     * Share
     * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
     *
     *
     *
     *
     * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
     *
     *
     *
     *
     * The largest rectangle is shown in the shaded area, which has area = 10 unit.
     *
     *
     *
     * Example:
     *
     * Input: [2,1,5,6,2,3]
     * Output: 10
     */
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        int[] left = new int[heights.length];
        left[0] = -1;
        for (int i = 0; i < heights.length; ++i) {
            int leftBoundary = i-1;
            while (leftBoundary >= 0 && heights[leftBoundary] >= heights[i]) {
                leftBoundary = left[leftBoundary];
            }
            left[i] = leftBoundary;
        }
        int[] right = new int[heights.length];
        right[heights.length-1] = heights.length;
        for (int i = heights.length-1; i >= 0; --i) {
            int rightBoundary = i + 1;
            while (rightBoundary < heights.length && heights[rightBoundary] >= heights[i]) {
                rightBoundary = right[rightBoundary];
            }
            right[i] = rightBoundary;
        }
        int res = 0;
        for (int i = 0; i < heights.length; ++i) {
            res = Math.max(res, (right[i] - left[i] - 1) * heights[i]);
        }
        return res;
    }

    /**
     * 221. Maximal Square
     * Medium
     *
     * 2294
     *
     * 58
     *
     * Add to List
     *
     * Share
     * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
     *
     * Example:
     *
     * Input:
     *
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     *
     * Output: 4
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int res = 0;
        int[][] dp = new int[matrix.length+1][matrix[0].length+1];
        for (int i = matrix.length-1; i >= 0; --i) {
            for (int j = matrix[i].length-1; j >= 0; --j) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    int possibleLenA = dp[i][j+1];
                    int possibleLenB = dp[i+1][j];
                    int possibleLenC = dp[i+1][j+1];
                    dp[i][j] = Math.min(possibleLenA, Math.min(possibleLenB, possibleLenC))+1;
                    if (res < dp[i][j]) {
                        res = dp[i][j];
                    }
                }
            }
        }
        return res*res;
    }


    /**
     * 1463. Cherry Pickup II
     * Hard
     *
     * Given a rows x cols matrix grid representing a field of cherries. Each cell in grid represents the number of cherries that you can collect.
     *
     * You have two robots that can collect cherries for you, Robot #1 is located at the top-left corner (0,0) , and Robot #2 is located at the top-right corner (0, cols-1) of the grid.
     *
     * Return the maximum number of cherries collection using both robots  by following the rules below:
     *
     * From a cell (i,j), robots can move to cell (i+1, j-1) , (i+1, j) or (i+1, j+1).
     * When any robot is passing through a cell, It picks it up all cherries, and the cell becomes an empty cell (0).
     * When both robots stay on the same cell, only one of them takes the cherries.
     * Both robots cannot move outside of the grid at any moment.
     * Both robots should reach the bottom row in the grid.
     */
    public int cherryPickup(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][][] dp = new int[row+1][col+2][col+2];
        for (int x = row-1; x >= 0; --x) {
            for (int i = 1; i <= col; ++i) {
                for (int j = 1; j <= col; ++j) {
                    int res = 0;
                    for (int a = -1; a <= 1; ++a) {
                        for (int b = -1; b <= 1; ++b) {
                            res = Math.max(res, dp[x+1][i+a][j+b] + grid[x][i-1] + (i == j ? 0 : grid[x][j-1]));
                        }
                    }
                    dp[x][i][j] = res;
                }
            }
        }
        return dp[0][1][col];
    }

    public int cherryPickupSolution2(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[col+2][col+2];
        for (int x = row-1; x >= 0; --x) {
            int[][] tmpDp = new int[col+2][col+2];
            for (int i = 1; i <= col; ++i) {
                for (int j = 1; j <= col; ++j) {

                    int res = 0;
                    for (int a = -1; a <= 1; ++a) {
                        for (int b = -1; b <= 1; ++b) {
                            res = Math.max(res, dp[i+a][j+b] + grid[x][i-1] + (i == j ? 0 : grid[x][j-1]));
                        }
                    }
                    tmpDp[i][j] = res;
                }
            }
            dp = tmpDp;
        }
        return dp[1][col];
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
        Integer[] dp = new Integer[grid[0].length+1];
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                Integer leftPre = dp[j];
                Integer upPre = dp[j+1];
                boolean leftPreIsNull = leftPre == null;
                boolean upPreIsNull = upPre == null;
                if (!leftPreIsNull && !upPreIsNull) {
                    dp[j+1] = Math.min(leftPre, upPre) + grid[i][j];
                } else if (leftPreIsNull && upPreIsNull) {
                    dp[j+1] = grid[i][j];
                } else if (upPreIsNull) {
                    dp[j+1] = dp[j] + grid[i][j];
                } else {
                    dp[j+1] = dp[j+1] + grid[i][j];
                }
            }
        }
        return dp[grid[0].length];
    }

    public int minPathSum_Solve2(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        Integer[][] dp = new Integer[row+1][col+1];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                Integer leftPre = dp[i][j+1];
                Integer upPre = dp[i+1][j];
                if (leftPre != null && upPre != null) {
                    dp[i+1][j+1] = Math.min(leftPre, upPre) + grid[i][j];
                } else if (leftPre == null && upPre == null) {
                    dp[i+1][j+1] = grid[i][j];
                } else if (leftPre == null) {
                    dp[i+1][j+1] = upPre + grid[i][j];
                } else {
                    dp[i+1][j+1] = leftPre + grid[i][j];
                }
            }
        }
        return dp[row][col];
    }

    /**
     * 542. 01 Matrix
     * https://leetcode.com/problems/01-matrix/
     */
    public int[][] updateMatrix(int[][] mat) {
        if (mat.length == 0 || mat[0].length == 0) {
            return null;
        }
        int row = mat.length;
        int col = mat[0].length;
        Integer[][] dp = new Integer[row+2][col+2];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (mat[i][j] == 0) {
                    dp[i+1][j+1] = 0;
                    continue;
                }
                Integer leftPre = dp[i+1][j];
                Integer upPre = dp[i][j+1];
                if (leftPre != null && upPre != null) {
                    dp[i+1][j+1] = Math.min(leftPre, upPre) + 1;
                } else if (leftPre != null) {
                    dp[i+1][j+1] = leftPre + 1;
                } else if (upPre != null) {
                    dp[i+1][j+1] = upPre + 1;
                }
            }
        }
        for (int i = row-1; i >= 0; --i) {
            for (int j = col-1; j >= 0; --j) {
                if (mat[i][j] == 0) {
                    continue;
                }
                Integer downPre = dp[i+2][j+1];
                Integer rightPre = dp[i+1][j+2];
                boolean isCurDpNull = dp[i+1][j+1] == null;
                if (downPre != null && rightPre != null) {
                    int min = Math.min(downPre, rightPre) + 1;
                    dp[i+1][j+1] = isCurDpNull ? min : Math.min(min, dp[i+1][j+1]);
                } else if (downPre != null) {
                    dp[i+1][j+1] = isCurDpNull ? downPre + 1 : Math.min(dp[i+1][j+1], downPre+1);
                } else if (rightPre != null) {
                    dp[i+1][j+1] = isCurDpNull ? rightPre + 1 : Math.min(dp[i+1][j+1], rightPre+1);
                }
            }
        }
        int[][] res = new int[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                res[i][j] = dp[i+1][j+1];
            }
        }
        return res;
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
     * 1727. Largest Submatrix With Rearrangements
     * https://leetcode.com/problems/largest-submatrix-with-rearrangements/
     */
    public int largestSubmatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < col; ++i) {
            int[] cnt = new int[row];
            for (int j = 0; j < row; ++j) {
                if (matrix[j][i] == 1) {
                    cnt[j] = (j > 0 ? (cnt[j-1] +1) : 1);
                    dp[j][i] = cnt[j];
                }
            }
        }
        int res = 0;
        for (int i = 0; i < row; ++i) {
            Arrays.sort(dp[i]);
            for (int j = col - 1; j >= 0; --j) {
                res = Math.max(dp[i][j] * (col - j), res);
            }
        }
        return res;
    }
}
