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
}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }