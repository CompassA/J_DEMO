package com.study.leetcode.slidewindow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author fanqie
 * @date 2020/2/25
 */
public class SlidingWindowMaximum {
    /**
     * 239. Sliding Window Maximum
     * Hard
     *
     * 2599
     *
     * 152
     *
     * Add to List
     *
     * Share
     * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
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
}
