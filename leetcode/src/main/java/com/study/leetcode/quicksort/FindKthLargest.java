package com.study.leetcode.quicksort;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class FindKthLargest {

    /**
     * 215. Kth Largest Element in an Array
     *
     * Find the kth largest element in an unsorted array.
     * Note that it is the kth largest element in the sorted order,
     * not the kth distinct element.
     */
    public int findKthLargest(int[] nums, int k) {
        final int targetPos = k - 1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int pos = partition(nums, left, right);
            if (pos == targetPos) {
                return nums[pos];
            } else if (pos > targetPos) {
                right = pos - 1;
            } else {
                left = pos + 1;
            }
        }
        return -1;
    }

    private int partition(int[] nums, int left, int right) {
        //[left, leftBorder] bigger than nums[right]
        int randomPos = (int) (Math.random() * (right - left + 1) + left);
        swap(nums, right, randomPos);

        int leftBorder = left - 1;
        int target = nums[right];
        for (int i = left; i < right; ++i) {
            if (nums[i] > target) {
                ++leftBorder;
                swap(nums, i, leftBorder);
            }
        }
        ++leftBorder;
        swap(nums, leftBorder, right);
        return leftBorder;
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}
