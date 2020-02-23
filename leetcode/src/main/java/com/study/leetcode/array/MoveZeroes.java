package com.study.leetcode.array;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class MoveZeroes {
    /**
     * 283. Move Zeroes
     *
     * Given an array nums, write a function to move
     * all 0's to the end of it while maintaining the relative order of the non-zero elements.
     */
    public void moveZeroes(int[] nums) {
        int toBeReplaced = 0;
        for (int cur = 0; cur < nums.length; ++cur) {
            //当前数字不等于0
            if (nums[cur] != 0) {
                //若位置相同则不交换
                if (cur != toBeReplaced) {
                    int tmp = nums[cur];
                    nums[cur] = nums[toBeReplaced];
                    nums[toBeReplaced] = tmp;
                }
                ++toBeReplaced;
            }
        }
    }
}
