package com.study.leetcode.dp;

import java.util.Arrays;

/**
 * @author fanqie
 * @date 2020/6/1
 */
public class LargestArea {

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
}
