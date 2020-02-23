package com.study.leetcode.array;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class RemoveDuplicatesFromSortedArrayII {
    /**
     * NO. 80 Remove Duplicates from Sorted Array II
     * Given a sorted array nums, remove the duplicates in-place such that
     * duplicates appeared at most twice and return the new length.
     *
     * Do not allocate extra space for another array,
     * you must do this by modifying the input array in-place with O(1) extra memory.
     */
    public int removeDuplicatesVersionTwo(int[] nums) {
        int toBeReplaced = 2;
        int curPos = 2;
        while (curPos < nums.length) {
            //只要当前数和倒数第二个数字不等就交换
            if (nums[curPos] != nums[toBeReplaced - 2]) {
                nums[curPos] = nums[toBeReplaced++];
            }
            ++curPos;
        }
        return toBeReplaced;
//        int toBeReplaced = 0;
//        int curPos = 0;
//        while (curPos < nums.length) {
//            //当前数置于toBeReplaced
//            nums[toBeReplaced++] = nums[curPos++];
//
//            //处理第二位数相同的情况
//            if (curPos < nums.length && nums[curPos] == nums[curPos-1]) {
//                nums[toBeReplaced++] = nums[curPos++];
//                while (curPos < nums.length && nums[curPos] == nums[curPos-1]) {
//                    ++curPos;
//                }
//            }
//        }
//        return toBeReplaced;
    }
}
