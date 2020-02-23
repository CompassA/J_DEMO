package com.study.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class ThreeSum {

    /**
     * 15. 3Sum
     * Medium
     *
     * 5023
     *
     * 610
     *
     * Favorite
     *
     * Share
     * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
     * Find all unique triplets in the array which gives the sum of zero.
     *
     * Note:
     * The solution set must not contain duplicate triplets.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        final List<List<Integer>> res = new ArrayList<>(0);
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ) {
            final int target = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                final int leftNum = nums[left];
                final int rightNum = nums[right];
                if (target + leftNum == -rightNum) {
                    res.add(Arrays.asList(target, leftNum, rightNum));
                    //数组有序时去重 记录当前数  while 数组下标未越界且数字与当前数相等 递增数组
                    while (left < nums.length && nums[left] == leftNum) {
                        ++left;
                    }
                    while (right >= 0 && nums[right] == rightNum) {
                        --right;
                    }
                } else if (target + leftNum < -rightNum) {
                    ++left;
                } else {
                    --right;
                }
            }
            while (i < nums.length && nums[i] == target) {
                ++i;
            }
        }
        return res;
    }

}
