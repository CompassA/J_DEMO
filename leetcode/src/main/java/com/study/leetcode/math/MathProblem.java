package com.study.leetcode.math;

/**
 * @author fanqie
 * @date 2020/4/5
 */
public class MathProblem {

    /**
     * 8. String to Integer (atoi)
     * Medium
     *
     * 1375
     *
     * 8321
     *
     * Add to List
     *
     * Share
     * Implement atoi which converts a string to an integer.
     *
     * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
     *
     * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
     *
     * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
     *
     * If no valid conversion could be performed, a zero value is returned.
     *
     * Note:
     *
     * Only the space character ' ' is considered as whitespace character.
     * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
     * Example 1:
     *
     * Input: "42"
     * Output: 42
     * Example 2:
     *
     * Input: "   -42"
     * Output: -42
     * Explanation: The first non-whitespace character is '-', which is the minus sign.
     *              Then take as many numerical digits as possible, which gets 42.
     * Example 3:
     *
     * Input: "4193 with words"
     * Output: 4193
     * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
     * Example 4:
     *
     * Input: "words and 987"
     * Output: 0
     * Explanation: The first non-whitespace character is 'w', which is not a numerical
     *              digit or a +/- sign. Therefore no valid conversion could be performed.
     * Example 5:
     *
     * Input: "-91283472332"
     * Output: -2147483648
     * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
     *              Thefore INT_MIN (−231) is returned.
     */
    public int myAtoI(String str) {
        if (str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int index = 0;
        while (index < chars.length && chars[index] == ' ') {
            ++index;
        }
        int sign = 1;
        if (index < chars.length && (chars[index] == '-' || chars[index] == '+')) {
            if (chars[index] == '-') {
                sign = -1;
            }
            ++index;
        }
        int res = 0;
        int limit = Integer.MAX_VALUE / 10;
        while (index < chars.length && chars[index] >= '0' && chars[index] <= '9') {
            if (limit < res || (limit == res && chars[index] > '7')) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + (chars[index] - '0');
            ++index;
        }
        return res * sign;
    }

    /**
     * 7. Reverse Integer
     * Easy
     *
     * 3020
     *
     * 4762
     *
     * Add to List
     *
     * Share
     * Given a 32-bit signed integer, reverse digits of an integer.
     *
     * Example 1:
     *
     * Input: 123
     * Output: 321
     * Example 2:
     *
     * Input: -123
     * Output: -321
     * Example 3:
     *
     * Input: 120
     * Output: 21
     * Note:
     * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
     * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
     */
    public int reverse(int x) {
        int res = 0;
        int limit = Integer.MAX_VALUE / 10;
        while (x != 0) {
            if (Math.abs(res) > limit || (Math.abs(res) == limit && Math.abs(x) > 7)) {
                return 0;
            }
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }

    /**
     * 9. Palindrome Number
     * Easy
     *
     * 2048
     *
     * 1496
     *
     * Add to List
     *
     * Share
     * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
     *
     * Example 1:
     *
     * Input: 121
     * Output: true
     * Example 2:
     *
     * Input: -121
     * Output: false
     * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
     * Example 3:
     *
     * Input: 10
     * Output: false
     * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
     */
    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        int half = 0;
        while (half < x) {
            half = half * 10 + x % 10;
            x /= 10;
        }
        return half == x || half / 10 == x;
    }

    /**
     * 41. First Missing Positive
     * Hard
     *
     * 2869
     *
     * 737
     *
     * Add to List
     *
     * Share
     * Given an unsorted integer array, find the smallest missing positive integer.
     *
     * Example 1:
     *
     * Input: [1,2,0]
     * Output: 3
     * Example 2:
     *
     * Input: [3,4,-1,1]
     * Output: 2
     * Example 3:
     *
     * Input: [7,8,9,11,12]
     * Output: 1
     * Note:
     *
     * Your algorithm should run in O(n) time and uses constant extra space.
     */
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i]-1]) {
                int index = nums[i] - 1;
                int tmp = nums[i];
                nums[i] = nums[index];
                nums[index] = tmp;
            }
        }
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}