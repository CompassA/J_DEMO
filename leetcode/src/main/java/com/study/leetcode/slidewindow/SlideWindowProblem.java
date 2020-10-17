package com.study.leetcode.slidewindow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author tomato
 * Created on 2020.10.17
 */
public class SlideWindowProblem {

    /**
     * 239. Sliding Window Maximum
     * Hard
     *
     * Given an array nums, there is a sliding window of size k which is moving from the very left
     * of the array to the very right. You can only see the k numbers in the window.
     * Each time the sliding window moves right by one position. Return the max sliding window.
     *
     * Example:
     *
     * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
     * Output: [3,3,5,5,6,7]
     * Explanation:
     *
     * Window position                Max
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     * Note:
     * You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        //the queue records the possible max sliding window num
        final Deque<Integer> possibleMaxNums = new ArrayDeque<>();
        final List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            //exclude numbers outside the left border
            if (!possibleMaxNums.isEmpty() && possibleMaxNums.peekFirst() < i - k + 1) {
                possibleMaxNums.pollFirst();
            }

            //exclude numbers which are smaller than the currentNum from the tail of queue
            while (!possibleMaxNums.isEmpty() && nums[possibleMaxNums.peekLast()] < nums[i]) {
                possibleMaxNums.pollLast();
            }

            //push the index of current number to the tail of queue
            possibleMaxNums.offerLast(i);

            //the max number is always at the front of deque
            if (i >= k - 1) {
                res.add(nums[possibleMaxNums.peekFirst()]);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 209. Minimum Size Subarray Sum
     */
    public int minSubArrayLen(int s, int[] nums) {
        int[] preSum = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            preSum[i+1] = preSum[i] + nums[i];
        }
        int minLen = Integer.MAX_VALUE;
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i <= nums.length; ++i) {
            while (!queue.isEmpty() && preSum[i] - preSum[queue.getFirst()] >= s) {
                int curLen = i - queue.pollFirst();
                if (curLen < minLen) {
                    minLen = curLen;
                }
            }
            queue.offerLast(i);
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    /**
     * 862. Shortest Subarray with Sum at Least K
     * Hard
     *
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
            preSum[i+1] = preSum[i] + A[i];
        }
        //keep the dequeue monotonically increasing
        Deque<Integer> queue = new ArrayDeque<>();
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i <= A.length; ++i) {
            while (!queue.isEmpty() && preSum[i] - preSum[queue.getFirst()] >= K) {
                int curLen = i - queue.pollFirst();
                if (curLen < minLen) {
                    minLen = curLen;
                }
            }
            while (!queue.isEmpty() && preSum[queue.getLast()] > preSum[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }
}
