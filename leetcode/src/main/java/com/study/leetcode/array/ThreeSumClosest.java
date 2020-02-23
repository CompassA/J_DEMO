package com.study.leetcode.array;

import java.util.Arrays;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class ThreeSumClosest {

    /**
     *16. 3Sum Closest
     * Medium
     *
     * 1489
     *
     * 109
     *
     * Favorite
     *
     * Share
     * Given an array nums of n integers and an integer target,
     * find three integers in nums such that the sum is closest to target.
     * Return the sum of the three integers.
     * You may assume that each input would have exactly one solution.
     *
     *
     * */
    public int threeSumClosest(int[] nums, int target) {
        int tolerance = Integer.MAX_VALUE;
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ++i) {
            final int curNum = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                final int sum = curNum + nums[left] + nums[right];
                int difference = Math.abs(target - sum);
                if (difference == 0) {
                    return target;
                }
                if (tolerance > difference) {
                    res = sum;
                    tolerance = difference;
                }
                if (sum < target) {
                    ++left;
                } else {
                    --right;
                }
            }
        }
        return res;
    }

}
