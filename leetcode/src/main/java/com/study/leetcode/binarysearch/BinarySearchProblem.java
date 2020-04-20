package com.study.leetcode.binarysearch;

/**
 * @author fanqie
 * @date 2020/4/7
 */
public class BinarySearchProblem {

    /**
     * 69. Sqrt(x)
     * Easy
     *
     * 1125
     *
     * 1749
     *
     * Add to List
     *
     * Share
     * Implement int sqrt(int x).
     *
     * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
     *
     * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
     *
     * Example 1:
     *
     * Input: 4
     * Output: 2
     * Example 2:
     *
     * Input: 8
     * Output: 2
     * Explanation: The square root of 8 is 2.82842..., and since
     *              the decimal part is truncated, 2 is returned.
     */
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }
        int left = 1, right = x / 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    /**
     * 4. Median of Two Sorted Arrays
     * Hard
     *
     * 6319
     *
     * 966
     *
     * Add to List
     *
     * Share
     * There are two sorted arrays nums1 and nums2 of size m and n respectively.
     *
     * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
     *
     * You may assume nums1 and nums2 cannot be both empty.
     *
     * Example 1:
     *
     * nums1 = [1, 3]
     * nums2 = [2]
     *
     * The median is 2.0
     * Example 2:
     *
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     *
     * The median is (2 + 3)/2 = 2.5
     */
    public double findMedianSortedArrays(int[] shorter, int[] longer) {
        if (shorter.length > longer.length) {
            return findMedianSortedArrays(longer, shorter);
        }
        int halfLength = (shorter.length + longer.length + 1) / 2;
        //interval
        int left = 0, right = shorter.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int otherMid = halfLength - mid;

            //move shorter array interval
            if (mid < shorter.length && shorter[mid] < longer[otherMid-1]) {
                left = mid + 1;
            } else if (mid > 0 && shorter[mid-1] > longer[otherMid]) {
                right = mid - 1;
            } else {
                //find mid
                int res;
                if (mid == 0) {
                    res = longer[halfLength-1];
                } else if (otherMid == 0) {
                    res = shorter[halfLength-1];
                } else {
                    res = Math.max(shorter[mid-1], longer[otherMid-1]);
                }
                if ((shorter.length + longer.length) % 2 == 1) {
                    return res;
                }
                //find mid right
                if (mid == shorter.length) {
                    return (longer[otherMid]+ res) / 2.0;
                } else if (otherMid == longer.length) {
                    return (shorter[mid] + res) / 2.0;
                } else {
                    return (res + Math.min(shorter[mid], longer[otherMid])) / 2.0;
                }
            }
        }
        return 0;
    }

    /**
     * 153. Find Minimum in Rotated Sorted Array
     * Medium
     *
     * 1768
     *
     * 217
     *
     * Add to List
     *
     * Share
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     *
     * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
     *
     * Find the minimum element.
     *
     * You may assume no duplicate exists in the array.
     *
     * Example 1:
     *
     * Input: [3,4,5,1,2]
     * Output: 1
     * Example 2:
     *
     * Input: [4,5,6,7,0,1,2]
     * Output: 0
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] < nums[right]) {
                return nums[left];
            }

            int mid = left + (right - left) / 2;
            if (nums[mid] >= nums[left]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[right];
    }
}
