package com.study.leetcode.array;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class TwoSumII {

    /**
     * 167. Two Sum II - Input array is sorted
     *
     * Given an array of integers that is already sorted in ascending order,
     * find two numbers such that they add up to a specific target number.
     * The function twoSum should return indices of the two numbers such that they add up to the target,
     * where index1 must be less than index2.
     *
     * Note:
     *
     * Your returned answers (both index1 and index2) are not zero-based.
     * You may assume that each input would have exactly one solution
     * and you may not use the same element twice.
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            final int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[] {left, right};
            } else if (sum > target) {
                --right;
            } else {
                ++left;
            }
        }
        throw new UnsupportedOperationException();
    }
}
