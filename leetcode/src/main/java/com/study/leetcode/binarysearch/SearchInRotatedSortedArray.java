package com.study.leetcode.binarysearch;

/**
 * @author fanqie
 * @date 2020/2/26
 */
public class SearchInRotatedSortedArray {
    /**
     * 33. Search in Rotated Sorted Array
     * Medium
     *
     * 3776
     *
     * 405
     *
     * Add to List
     *
     * Share
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     *
     * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
     *
     * You are given a target value to search. If found in the array return its index, otherwise return -1.
     *
     * You may assume no duplicate exists in the array.
     *
     * Your algorithm's runtime complexity must be in the order of O(log n).
     *
     * Example 1:
     *
     * Input: nums = [4,5,6,7,0,1,2], target = 0
     * Output: 4
     * Example 2:
     *
     * Input: nums = [4,5,6,7,0,1,2], target = 3
     * Output: -1
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        final int offset = calcOffset(nums);
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            final int originMid = left + (right - left) / 2;
            final int mid = (originMid + offset) % nums.length;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = originMid - 1;
            } else {
                left = originMid + 1;
            }
        }
        return -1;
    }

    private int calcOffset(int[] nums) {
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < nums[i - 1]) {
                return i;
            }
        }
        return 0;
    }
}
