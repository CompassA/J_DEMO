package com.study.leetcode.slidewindow;

/**
 * 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
 * Medium
 *
 * 235
 *
 * 5
 *
 * Add to List
 *
 * Share
 * Given an array of integers nums and an integer limit, return the size of the longest continuous subarray such that the absolute difference between any two elements is less than or equal to limit.
 *
 * In case there is no subarray satisfying the given condition return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 * [2] with maximum absolute diff |2-2| = 0 <= 4.
 * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 * [4] with maximum absolute diff |4-4| = 0 <= 4.
 * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 * [7] with maximum absolute diff |7-7| = 0 <= 4.
 * Therefore, the size of the longest subarray is 2.
 * Example 2:
 *
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4
 * Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
 * Example 3:
 *
 * Input: nums = [4,2,2,2,4,4,2,2], limit = 0
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 * @author fanqie
 * @date 2020/5/7
 */
public class LongestContinuousSubArrayWithAbsoluteDiff {

    public int longestSubarray(int[] nums, int limit) {
        int res = 1;
        for (int begin = 0; begin < nums.length; ++begin) {
            int min = nums[begin], max = nums[begin];
            for (int len = 2; len <= nums.length; ++len) {
                int end = begin + len - 1;
                if (end >= nums.length) {
                    break;
                }
                min = Math.min(min, nums[end]);
                max = Math.max(max, nums[end]);
                int newDiff = max - min;
                if (newDiff <= limit) {
                    if (len > res) {
                        res = len;
                    }
                } else {
                    break;
                }
            }
            if (res >= nums.length - begin - 1) {
                break;
            }
        }
        return res;
    }
}
