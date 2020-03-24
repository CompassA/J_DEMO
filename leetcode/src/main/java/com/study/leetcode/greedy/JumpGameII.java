package com.study.leetcode.greedy;

/**
 * @author fanqie
 * @date 2020/3/25
 */
public class JumpGameII {

    /**
     * 45. Jump Game II
     * Hard
     *
     * 1986
     *
     * 117
     *
     * Add to List
     *
     * Share
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     *
     * Each element in the array represents your maximum jump length at that position.
     *
     * Your goal is to reach the last index in the minimum number of jumps.
     *
     * Example:
     *
     * Input: [2,3,1,1,4]
     * Output: 2
     * Explanation: The minimum number of jumps to reach the last index is 2.
     *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * Note:
     *
     * You can assume that you can always reach the last index.
     */
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int times = 0;
        for (int i = 0; ; ) {
            int left = i;
            int right = i + nums[i];
            if (right >= nums.length - 1) {
                ++times;
                break;
            }
            int next = right;
            int maxReach = right;
            for (int j = left + 1; j <= right; ++j) {
                if (j + nums[j] > maxReach) {
                    maxReach = j + nums[j];
                    next = j;
                }
            }
            ++times;
            i = next;
        }
        return times;
    }
}
