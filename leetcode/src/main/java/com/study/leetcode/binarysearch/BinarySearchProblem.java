package com.study.leetcode.binarysearch;

/**
 * @author fanqie
 * @date 2020/4/7
 */
public class BinarySearchProblem {

    /**
     * 69. Sqrt(x)
     * Easy
     *
     * 1125
     *
     * 1749
     *
     * Add to List
     *
     * Share
     * Implement int sqrt(int x).
     *
     * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
     *
     * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
     *
     * Example 1:
     *
     * Input: 4
     * Output: 2
     * Example 2:
     *
     * Input: 8
     * Output: 2
     * Explanation: The square root of 8 is 2.82842..., and since
     *              the decimal part is truncated, 2 is returned.
     */
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }
        int left = 1, right = x / 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }
}
