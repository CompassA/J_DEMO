package com.study.leetcode.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fanqie
 * @date 2020/4/5
 */
public class MathProblem {

    /**
     * 8. String to Integer (atoi)
     * Medium
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

    /**
     * 268. Missing Number
     * Easy
     * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
     *
     * Example 1:
     *
     * Input: [3,0,1]
     * Output: 2
     * Example 2:
     *
     * Input: [9,6,4,2,3,5,7,0,1]
     * Output: 8
     * Note:
     * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
     */
    public int missingNumber(int[] nums) {
        int sum = (1 + nums.length) * nums.length / 2;
        for (int i = 0; i < nums.length; ++i) {
            sum -= nums[i];
        }
        return sum;
    }

    /**
     * 263. Ugly Number
     * Easy
     * Write a program to check whether a given number is an ugly number.
     *
     * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
     *
     * Example 1:
     *
     * Input: 6
     * Output: true
     * Explanation: 6 = 2 × 3
     * Example 2:
     *
     * Input: 8
     * Output: true
     * Explanation: 8 = 2 × 2 × 2
     * Example 3:
     *
     * Input: 14
     * Output: false
     * Explanation: 14 is not ugly since it includes another prime factor 7.
     * Note:
     *
     * 1 is typically treated as an ugly number.
     * Input is within the 32-bit signed integer range: [−231,  231 − 1].
     */
    public boolean isUgly(int num) {
        if (num == 0) {
            return false;
        }
        if (num == 1) {
            return true;
        }
        while (num % 2 == 0) {
            num /= 2;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 5 == 0) {
            num /= 5;
        }
        return num == 1;
    }

    /**
     * 233. Number of Digit One
     * Hard
     * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
     *
     * Example:
     *
     * Input: 13
     * Output: 6
     * Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
     */
    public int countDigitOne(int n) {
        int mod = 1;
        int cache;
        int res = 0;
        while ((cache = n / mod) != 0) {
            int rightNum = n % mod;
            int leftNum = cache / 10;
            int curNum = cache % 10;
            if (curNum > 1) {
                res += ((leftNum + 1) * mod);
            } else if (curNum == 1) {
                res += (leftNum * mod + rightNum + 1);
            } else {
                res += (leftNum * mod);
            }
            if (mod == 1e9) {
                break;
            }
            mod *= 10;
        }
        return res;
    }

    /**
     * 1503. Last Moment Before All Ants Fall Out of a Plank
     */
    public int getLastMoment(int n, int[] left, int[] right) {
        Arrays.sort(left);
        Arrays.sort(right);
        if (right.length == 0) {
            return left[left.length - 1];
        }
        if (left.length == 0) {
            return n - right[0];
        }
        return Math.max(left[left.length-1], n - right[0]);
    }

    /** 357. Count Numbers with Unique Digits */
    private static class Solution {
        private static int[] dp;
        static {
            dp = new int[9];
            dp[0] = 1; dp[1] = 10; dp[2] = 9 * 9;
            for (int i = 3; i < 9; ++i) {
                dp[i] = dp[i-1] * (10 - i + 1);
            }
        }
        public int countNumbersWithUniqueDigits(int n) {
            if (n == 0) { return 1; }
            int res = 0;
            for (int i = 1; i <= n; ++i) { res += dp[i]; }
            return res;
        }
    }

    //=========================Medium==============================

    /**
     * 1726. Tuple with Same Product
     * https://leetcode.com/problems/tuple-with-same-product/
     */
    public int tupleSameProduct(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                int product = nums[i] * nums[j];
                cnt.put(product, cnt.getOrDefault(product, 0) + 1);
            }
        }
        int[] res = new int[1];
        cnt.forEach((k, v) -> {
            if (v < 2) {
                return;
            }
            res[0] += (v) * (v - 1) * 2 * 2;
        });
        return res[0];
    }

    //===========================Hard==============================

    /**
     * 1739. Building Boxes
     * https://leetcode.com/problems/building-boxes/
     */
    public int minimumBoxes(int n) {
        int total = 0;
        int curLevel = 0;
        int i;
        for (i = 0; total < n; ++i) {
            curLevel += i;
            total += curLevel;
        }
        if (total == n) {
            return curLevel;
        }
        total -= curLevel;
        curLevel -= i;
        for (i = 1; total < n; ++i) {
            total += i;
        }
        return curLevel + i;
    }

    /**
     * 1737. Change Minimum Characters to Satisfy One of Three Conditions
     * https://leetcode.com/problems/change-minimum-characters-to-satisfy-one-of-three-conditions/
     */
    public int minCharacters(String a, String b) {
        char[] strA = a.toCharArray();
        char[] strB = b.toCharArray();
        int[] cntA = new int[26];
        int[] cntB = new int[26];
        for (int i = 0; i < strA.length; ++i) {
            ++cntA[strA[i] - 'a'];
        }
        for (int i = 0; i < strB.length; ++i) {
            ++cntB[strB[i] - 'a'];
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 26; ++i) {
            res = Math.min(res, strA.length + strB.length - cntA[i] - cntB[i]);
            if (i > 0) {
                cntA[i] += cntA[i-1];
                cntB[i] += cntB[i-1];
            }
            if (i < 25) {
                res = Math.min(strA.length - cntA[i] + cntB[i], res);
                res = Math.min(strB.length - cntB[i] + cntA[i], res);
            }
        }
        return res;
    }
}
