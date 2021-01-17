package com.study.leetcode.binarysearch;

/**
 * @author fanqie
 * @date 2020/4/7
 */
public class BinarySearchProblem {

    //---------------------------------Hard--------------------------------------------
    /**
     * 154. Find Minimum in Rotated Sorted Array II
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
     */
    public int findMinII(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[left] == nums[right]) {
                ++left;
            } else if (nums[mid] <= nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }

    //---------------------------------Medium------------------------------------------
    /**
     * 34. Find First and Last Position of Element in Sorted Array
     * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
     */
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (left == nums.length || nums[left] != target) {
            return new int[]{-1, -1};
        }
        int posLeft = left;
        left = 0;
        right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return new int[]{posLeft, right - 1};
    }

    /**
     * 81. Search in Rotated Sorted Array II
     * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
     */
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[left] == nums[right]) {
                ++left;
            } else if (nums[mid] <= nums[right]) {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return false;
    }

    /**
     * 540. Single Element in a Sorted Array
     * https://leetcode.com/problems/single-element-in-a-sorted-array/
     */
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid % 2 == 1) {
                --mid;
            }
            if (nums[mid] != nums[mid+1]) {
                right = mid;
            } else {
                left = mid + 2;
            }
        }
        return nums[right];
    }

    /**
     * 215. Kth Largest Element in an Array
     * https://leetcode.com/problems/kth-largest-element-in-an-array/
     */
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            int left = 0, right = nums.length - 1;
            int target = nums.length - k;
            while (left <= right) {
                int mid = partition(nums, left, right);
                if (mid == target) {
                    return nums[mid];
                } else if (mid > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return -1;
        }

        private int partition(int[] nums, int left, int right) {
            int randomPos = (int) (Math.random() * (right - left)) + left;
            swap(nums, right, randomPos);
            int pivot = nums[right];

            int lowIndex = left - 1;
            while (left < right) {
                if (nums[left] < pivot) {
                    ++lowIndex;
                    swap(nums, lowIndex, left);
                }
                ++left;
            }
            ++lowIndex;
            swap(nums, lowIndex, right);
            return lowIndex;
        }

        private void swap(int[] nums, int a, int b) {
            int tmp = nums[a];
            nums[a] = nums[b];
            nums[b] = tmp;
        }
    }

    //========================================Easy==============================================
    /**
     * 69. Sqrt(x)
     * https://leetcode.com/problems/sqrtx/
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
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
}
