package com.study.leetcode.array;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class RemoveElement {
    /**
     * NO.27 Remove Element
     *
     * Given an array nums and a value val,
     * remove all instances of that value in-place and return the new length.
     *
     * Do not allocate extra space for another array,
     * you must do this by modifying the input array in-place with O(1) extra memory.
     *
     * The order of elements can be changed.
     * It doesn't matter what you leave beyond the new length.
     */
    public int removeElement(int[] nums, int val) {
        int toBeReplaced = 0;
        //扫描数组，把符合条件的数放到toBeReplaced上
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != val) {
                if (toBeReplaced != i) {
                    nums[toBeReplaced] = nums[i];
                }
                ++toBeReplaced;
            }
        }
        return toBeReplaced;
    }
}
