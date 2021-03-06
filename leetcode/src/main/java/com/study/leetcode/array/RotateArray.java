package com.study.leetcode.array;

/**
 * @author fanqie
 * @date 2020/3/26
 */
public class RotateArray {
    /**
     * 189. Rotate Array
     * Easy
     *
     * 2274
     *
     * 756
     *
     * Add to List
     *
     * Share
     * Given an array, rotate the array to the right by k steps, where k is non-negative.
     *
     * Example 1:
     *
     * Input: [1,2,3,4,5,6,7] and k = 3
     * Output: [5,6,7,1,2,3,4]
     * Explanation:
     * rotate 1 steps to the right: [7,1,2,3,4,5,6]
     * rotate 2 steps to the right: [6,7,1,2,3,4,5]
     * rotate 3 steps to the right: [5,6,7,1,2,3,4]
     * Example 2:
     *
     * Input: [-1,-100,3,99] and k = 2
     * Output: [3,99,-1,-100]
     * Explanation:
     * rotate 1 steps to the right: [99,-1,-100,3]
     * rotate 2 steps to the right: [3,99,-1,-100]
     * Note:
     *
     * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
     * Could you do it in-place with O(1) extra space?
     */
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0) {
            return;
        }

        myReverse(nums, nums.length - k, nums.length - 1);
        myReverse(nums, 0, nums.length - k - 1);
        myReverse(nums, 0, nums.length -1);
    }

    private void myReverse(int[] nums, int left, int right) {
        int swapTimes = (right - left + 1) / 2;
        for (int i = 0; i < swapTimes; ++i) {
            int thePos = left + i;
            int otherPos = right - i;
            int tmp = nums[thePos];
            nums[thePos] = nums[otherPos];
            nums[otherPos] = tmp;
        }
    }
}
