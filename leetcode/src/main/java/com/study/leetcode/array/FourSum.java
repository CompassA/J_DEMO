package com.study.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class FourSum {

    /**
     * 18. 4Sum
     * Medium
     *
     * 1395
     *
     * 271
     *
     * Favorite
     *
     * Share
     * Given an array nums of n integers and an integer target,
     * are there elements a, b, c, and d in nums such that a + b + c + d = target?
     * Find all unique quadruplets in the array which gives the sum of target.
     *
     * Note:
     *
     * The solution set must not contain duplicate quadruplets.
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        final List<List<Integer>> sets = new ArrayList<>(0);
        if (nums.length == 0) {
            return sets;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ) {
            final int num1 = nums[i];
            if (num1 > target) {
                break;
            }
            for (int j = i + 1; j < nums.length; ) {
                final int num2 = nums[j];
                if (num1 + num2 > target) {
                    break;
                }
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    final int num3 = nums[left];
                    final int num4 = nums[right];
                    if (num1 + num2 == target - num3 - num4) {
                        sets.add(Arrays.asList(num1, num2, num3, num4));
                        while (left < right && nums[left] == num3) {
                            ++left;
                        }
                        while (right > left && nums[right] == num4) {
                            --right;
                        }
                    } else if (num1 + num2 > target - num3 - num4) {
                        --right;
                    } else {
                        ++left;
                    }
                }
                while (j < nums.length && num2 == nums[j]) {
                    ++j;
                }
            }
            while (i < nums.length && num1 == nums[i]) {
                ++i;
            }
        }
        return sets;
    }
}
