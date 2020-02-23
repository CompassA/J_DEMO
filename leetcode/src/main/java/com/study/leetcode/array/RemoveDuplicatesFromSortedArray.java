package com.study.leetcode.array;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class RemoveDuplicatesFromSortedArray {

    /**
     * No. 26 Remove Duplicates from Sorted Array
     *
     * Given a sorted array nums, remove the duplicates in-place
     * such that each element appear only once and return the new length.
     * Do not allocate extra space for another array,
     * you must do this by modifying the input array in-place with O(1) extra memory.
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int toBeReplaced = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] != nums[toBeReplaced-1]) {
                if (i != toBeReplaced) {
                    nums[toBeReplaced] = nums[i];
                }
                ++toBeReplaced;
            }
        }
        return toBeReplaced;
    }

}
