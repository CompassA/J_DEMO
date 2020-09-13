package com.study.leetcode.array;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author fanqie
 * @date 2020/5/15
 */
public class DoubleArrayProblem {
    /**
     * 48. Rotate Image
     * Medium
     * You are given an n x n 2D matrix representing an image.
     * Rotate the image by 90 degrees (clockwise).
     * Note:
     * You have to rotate the image in-place,
     * which means you have to modify the input 2D matrix directly.
     * DO NOT allocate another 2D matrix and do the rotation.
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

    /** 363. Max Sum of Rectangle No Larger Than K */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int res = Integer.MIN_VALUE;
        for (int left = 0; left < col; ++left) {
            int[] sums = new int[row];
            for (int right = left; right < col; ++right) {
                for (int i = 0; i < row; ++i) {
                    sums[i] += matrix[i][right];
                }

                TreeSet<Integer> preSum = new TreeSet<>();
                preSum.add(0);
                int maxSum = Integer.MIN_VALUE;
                int curSum = 0;
                for (int sum : sums) {
                    curSum += sum;
                    //curSum — target <= k
                    //curSum - k <= target
                    Integer targetPreSum = preSum.ceiling(curSum - k);
                    if (targetPreSum != null) {
                        maxSum = Math.max(maxSum, curSum - targetPreSum);
                    }
                    preSum.add(curSum);
                }
                res = Math.max(res, maxSum);
            }
        }
        return res;
    }
}
