package com.study.leetcode.quicksort;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class SortColors {
    /**
     * 75. Sort Colors
     *
     * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
     *
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     *
     * Note: You are not suppose to use the library's sort function for this problem.
     */
    public void sortColors(int[] nums) {
        //[0, left] [right, nums.length-1]
        int left = -1, right = nums.length, i = 0;
        while (i < right) {
            switch (nums[i]) {
                case 0:
                    ++left;
                    swap(nums, left, i);
                    ++i;
                    break;
                case 1:
                    ++i;
                    break;
                case 2:
                    --right;
                    swap(nums, right, i);
                    break;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}
