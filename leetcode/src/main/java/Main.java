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
        int toBeReplaced = 0;
        for (int i = 0; i < nums.length; ) {
            //寻找重复区间[i+1, nextPos)
            int nextPos = i + 1;
            while (nextPos < nums.length && nums[nextPos] == nums[i]) {
                ++nextPos;
            }
            //防止原地交换
            if (toBeReplaced != i) {
                nums[toBeReplaced] = nums[i];
            }
            i = nextPos;
            ++toBeReplaced;
        }
        return toBeReplaced;
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }