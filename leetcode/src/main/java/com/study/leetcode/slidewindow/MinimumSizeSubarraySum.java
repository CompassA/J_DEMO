package com.study.leetcode.slidewindow;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class MinimumSizeSubarraySum {
    /**
     * 209. Minimum Size Subarray Sum
     *
     * Given an array of n positive integers and a positive integer s,
     * find the minimal length of a contiguous subarray of which the sum ≥ s.
     * If there isn't one, return 0 instead.
     */
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0, right = -1, sum = 0;
        int res = nums.length + 1;
        while (right + 1 < nums.length) {
            //增加右边框
            ++right;
            sum += nums[right];

            //循环增加左边框
            while (sum >= s) {
                final int curLength = right - left + 1;
                if (res > curLength) {
                    res = curLength;
                }
                sum -= nums[left];
                ++left;
            }

        }
        return nums.length + 1 == res ? 0 : res;
    }
}
