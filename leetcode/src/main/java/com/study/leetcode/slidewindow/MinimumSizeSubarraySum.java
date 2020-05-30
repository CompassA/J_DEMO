package com.study.leetcode.slidewindow;

import java.util.ArrayDeque;
import java.util.Deque;

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

    /**
     * 862. Shortest Subarray with Sum at Least K
     * Hard
     *
     * 979
     *
     * 28
     *
     * Add to List
     *
     * Share
     * Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
     *
     * If there is no non-empty subarray with sum at least K, return -1.
     *
     *
     *
     * Example 1:
     *
     * Input: A = [1], K = 1
     * Output: 1
     * Example 2:
     *
     * Input: A = [1,2], K = 4
     * Output: -1
     * Example 3:
     *
     * Input: A = [2,-1,2], K = 3
     * Output: 3
     *
     *
     * Note:
     *
     * 1 <= A.length <= 50000
     * -10 ^ 5 <= A[i] <= 10 ^ 5
     * 1 <= K <= 10 ^ 9
     */
    public int shortestSubarray(int[] A, int K) {
        int[] preSum = new int[A.length+1];
        for (int i = 0; i < A.length; ++i) {
            preSum[i+1] = A[i] + preSum[i];
        }
        Deque<Integer> queue = new ArrayDeque<>();
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= A.length; ++i) {
            while (!queue.isEmpty() && preSum[i] - preSum[queue.getFirst()] >= K) {
                res = Math.min(res, i - queue.pollFirst());
            }
            while (!queue.isEmpty() && preSum[queue.getLast()] > preSum[i]) {
                queue.pollLast();
            }
            queue.offer(i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
