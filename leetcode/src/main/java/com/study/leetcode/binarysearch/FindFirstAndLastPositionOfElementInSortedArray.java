package com.study.leetcode.binarysearch;

/**
 * @author fanqie
 * @date 2020/2/26
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    /**
     * 34. Find First and Last Position of Element in Sorted Array
     * Medium
     *
     * 2605
     *
     * 116
     *
     * Add to List
     *
     * Share
     * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
     *
     * Your algorithm's runtime complexity must be in the order of O(log n).
     *
     * If the target is not found in the array, return [-1, -1].
     *
     * Example 1:
     *
     * Input: nums = [5,7,7,8,8,10], target = 8
     * Output: [3,4]
     * Example 2:
     *
     * Input: nums = [5,7,7,8,8,10], target = 6
     * Output: [-1,-1]
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[] {-1, -1};
        }
        final int leftBorder = findLeftBorder(nums, target);
        if (leftBorder == -1) {
            return new int[] {-1, -1};
        }
        final int rightBorder = findRightBorder(nums, target);
        return new int[] {leftBorder, rightBorder};
    }

    private int findLeftBorder(final int[] nums, final int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            final int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left] == target ? left : -1;
    }

    private int findRightBorder(final int[] nums, final int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            final int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        final int targetPos = left - 1;
        if (targetPos >= 0 && nums[targetPos] == target) {
            return targetPos;
        }
        return -1;
    }

    public static void main(String[] args) {
        new FindFirstAndLastPositionOfElementInSortedArray()
                .searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
    }
}
