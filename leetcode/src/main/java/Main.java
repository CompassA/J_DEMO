import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author FanQie
 * @date 2019/11/20 23:40
 */
public class Main {

    /**
     * NO.1  two sum
     *
     * Given an array of integers,
     * return indices of the two numbers such that they add up to a specific target.
     * You may assume that each input would have exactly one solution,
     * and you may not use the same element twice.
     */
    public int[] twoSum(int[] nums, int target) {
        final Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; ++i) {
            final int otherNum = target - nums[i];
            if (map.containsKey(otherNum) && map.get(otherNum) != i) {
                return new int[] { i, map.get(otherNum) };
            }
            map.put(nums[i], i);
        }
        return null;
    }

    /**
     * NO.2 Add Two Numbers
     *
     * You are given two non-empty linked lists representing two non-negative integers.
     * The digits are stored in reverse order and each of their nodes contain a single digit.
     * Add the two numbers and return it as a linked list.
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //设置头节点、前驱节点、进位数
        final ListNode head = new ListNode(-1);
        ListNode pre = head;
        int carry = 0;

        //只要有一个节点非空就进行循环
        while (Objects.nonNull(l1) || Objects.nonNull(l2)) {
            if (Objects.nonNull(l1)) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (Objects.nonNull(l2)) {
                carry += l2.val;
                l2 = l2.next;
            }
            final ListNode cur = new ListNode(carry % 10);
            carry /= 10;
            pre.next = cur;
            pre = cur;
        }

        //位数是否增加
        if (carry != 0) {
            pre.next = new ListNode(carry);
        }

        return head.next;
    }

    /**
     * No.3 Longest Substring Without Repeating Characters
     *
     * Given a string, find the length of the longest substring without repeating characters.
     */
    public int lengthOfLongestSubstring(String s) {
        final int[] lastOccur = new int[256];
        final char[] str = s.toCharArray();
        //start表示未出现重复的左边界
        int res = 0, start = 0;
        for (int i = 0; i < 256; ++i) {
            lastOccur[i] = -1;
        }

        for (int i = 0; i < str.length; ++i) {
            //检查当前字母上次出现的位置是否在区间内
            //若在区间内则更新左边界
            if (lastOccur[str[i]] >= start) {
                start = lastOccur[str[i]] + 1;
            }
            //检查长度
            if (res < i - start + 1) {
                res = i - start + 1;
            }
            //更新位置
            lastOccur[str[i]] = i;
        }
        return res;
    }

    /**
     * NO.50 Pow(x, n)
     *
     * Implement pow(x, n), which calculates x raised to the power n (x^n).
     */
    public double myPow(double x, int n) {
        //对n进行检查
        if (n == 0) {
            return 1;
        } else if (n < 0 && n != Integer.MIN_VALUE) {
            return myPow(1 / x, -n);
        }

        //递归
        final double t = myPow(x, n / 2);
        if (n % 2 == 1) {
            return t * t * x;
        }
        return t * t;
    }

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

    /**
     * No. 26 Remove Duplicates from Sorted Array
     *
     * Given a sorted array nums, remove the duplicates in-place
     * such that each element appear only once and return the new length.
     * Do not allocate extra space for another array,
     * you must do this by modifying the input array in-place with O(1) extra memory.
     */
    public int removeDuplicates(int[] nums) {
//        int toBeReplaced = 0;
//        for (int i = 0; i < nums.length; ) {
//            //寻找重复区间[i+1, nextPos)
//            int nextPos = i + 1;
//            while (nextPos < nums.length && nums[nextPos] == nums[i]) {
//                ++nextPos;
//            }
//            //防止原地交换
//            if (toBeReplaced != i) {
//                nums[toBeReplaced] = nums[i];
//            }
//            i = nextPos;
//            ++toBeReplaced;
//        }
//        return toBeReplaced;
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

    /**
     * 75. Sort Colors
     *
     * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
     *
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     *
     * Note: You are not suppose to use the library's sort function for this problem.
     */
    public void sortColors(int[] nums) {
        //[0, left] [right, nums.length-1]
        int left = -1, right = nums.length, i = 0;
        while (i < right) {
            switch (nums[i]) {
            case 0:
                ++left;
                swap(nums, left, i);
                ++i;
                break;
            case 1:
                ++i;
                break;
            case 2:
                --right;
                swap(nums, right, i);
                break;
            }
        }
    }

    /**
     * 88. Merge Sorted Array
     *
     * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
     *
     * Note:
     *
     * The number of elements initialized in nums1 and nums2 are m and n respectively.
     * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1, index2 = n - 1;
        int toBeFilled = m + n - 1;
        while (index1 >= 0 && index2 >= 0) {
            if (nums1[index1] > nums2[index2]) {
                nums1[toBeFilled--] = nums1[index1--];
            } else {
                nums1[toBeFilled--] = nums1[index2--];
            }
        }
        while (index1 >= 0) {
            nums1[toBeFilled--] = nums1[index1--];
        }
        while (index2 >= 0) {
            nums1[toBeFilled--] = nums2[index2--];
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}

class ListNode {
      public int val;
      public ListNode next;
      ListNode(int x) { val = x; }
 }