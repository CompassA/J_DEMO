package com.study.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fanqie
 * @date 2020/5/15
 */
public class DoubleArrayProblem {
    /**
     * 48. Rotate Image
     * Medium
     *
     * 2708
     *
     * 213
     *
     * Add to List
     *
     * Share
     * You are given an n x n 2D matrix representing an image.
     *
     * Rotate the image by 90 degrees (clockwise).
     *
     * Note:
     *
     * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
     *
     * Example 1:
     *
     * Given input matrix =
     * [
     *   [1,2,3],
     *   [4,5,6],
     *   [7,8,9]
     * ],
     *
     * rotate the input matrix in-place such that it becomes:
     * [
     *   [7,4,1],
     *   [8,5,2],
     *   [9,6,3]
     * ]
     * Example 2:
     *
     * Given input matrix =
     * [
     *   [ 5, 1, 9,11],
     *   [ 2, 4, 8,10],
     *   [13, 3, 6, 7],
     *   [15,14,12,16]
     * ],
     *
     * rotate the input matrix in-place such that it becomes:
     * [
     *   [15,13, 2, 5],
     *   [14, 3, 4, 1],
     *   [12, 6, 8, 9],
     *   [16, 7,10,11]
     * ]
     */
    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = i + 1; j < matrix[0].length; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        for (int i = 0; i < matrix.length; ++i) {
            int left = 0, right = matrix[i].length - 1;
            while (left < right) {
                int tmp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = tmp;
                ++left;
                --right;
            }
        }
    }

    /**
     * 1504. Count Submatrices With All Ones
     * Medium
     *
     * Given a rows * columns matrix mat of ones and zeros, return how many submatrices have all ones.
     */
    public int numSubmat(int[][] mat) {
        int[][] dp = new int[mat.length+1][mat[0].length+1];
        for (int i = mat.length-1; i >= 0; --i) {
            for (int j = mat[0].length-1; j >= 0; --j) {
                dp[i][j] = dp[i+1][j] + dp[i][j+1] - dp[i+1][j+1] + (mat[i][j] == 1 ? 1 : 0);
            }
        }
        int res = 0;
        for (int i = mat.length-1; i >= 0; --i) {
            for (int j = mat[0].length-1; j >= 0; --j) {
                if (mat[i][j] == 0) {
                    continue;
                }
                int xMax = mat.length - i;
                int yMax = mat[0].length - j;
                for (int x = 1; x <= xMax; ++x) {
                    for (int y = 1; y <= yMax; ++y) {
                        int xEnd = i + x - 1;
                        int yEnd = j + y - 1;
                        int oneNums = dp[i][j] - dp[xEnd+1][j] - dp[i][yEnd+1] + dp[xEnd+1][yEnd+1];
                        if (oneNums == x * y) {
                            ++res;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int row = matrix.length;
        int col = matrix[0].length;

        int keyNum = 0;
        int height = row;
        while (height > 0) {
            int colBegin = keyNum;
            int colEnd = col - keyNum - 1;
            if (colBegin > colEnd) {
                break;
            }
            int rowBegin = colBegin;
            int rowEnd = colBegin + height - 1;
            for (int y = colBegin; y <= colEnd; ++y) {
                res.add(matrix[rowBegin][y]);
            }
            for (int x = rowBegin + 1; x < rowEnd; ++x) {
                res.add(matrix[x][colEnd]);
            }
            if (height > 1) {
                for (int y = colEnd; y >= colBegin; --y) {
                    res.add(matrix[rowEnd][y]);
                }
                if (colBegin != colEnd) {
                    for (int x = rowEnd - 1; x > rowBegin; --x) {
                        res.add(matrix[x][colBegin]);
                    }
                }
            }
            keyNum++;
            height -= 2;
        }
        return res;
    }
}
