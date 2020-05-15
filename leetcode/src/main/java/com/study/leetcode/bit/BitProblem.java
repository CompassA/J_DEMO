package com.study.leetcode.bit;

/**
 * @author fanqie
 * @date 2020/5/15
 */
public class BitProblem {
    /**
     * 136. Single Number
     * Easy
     *
     * 4157
     *
     * 154
     *
     * Add to List
     *
     * Share
     * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
     *
     * Note:
     *
     * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     *
     * Example 1:
     *
     * Input: [2,2,1]
     * Output: 1
     * Example 2:
     *
     * Input: [4,1,2,1,2]
     * Output: 4
     */
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            res ^= nums[i];
        }
        return res;
    }
}
