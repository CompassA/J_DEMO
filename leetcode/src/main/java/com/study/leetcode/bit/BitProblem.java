package com.study.leetcode.bit;

/**
 * @author fanqie
 * @date 2020/5/15
 */
public class BitProblem {
    /**
     * 136. Single Number
     * Easy
     * Given a non-empty array of integers, every element appears twice except for one.
     * Find that single one.
     *
     * Note:
     * Your algorithm should have a linear runtime complexity.
     * Could you implement it without using extra memory?
     */
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            res ^= nums[i];
        }
        return res;
    }

    /**
     * 1461. Check If a String Contains All Binary Codes of Size K
     * Medium
     * Given a binary string s and an integer k.
     *
     * Return True if any binary code of length k is a substring of s. Otherwise, return False.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "00110110", k = 2
     * Output: true
     * Explanation: The binary codes of length 2 are "00", "01", "10" and "11". They can be all found as substrings at indicies 0, 1, 3 and 2 respectively.
     * Example 2:
     *
     * Input: s = "00110", k = 2
     * Output: true
     * Example 3:
     *
     * Input: s = "0110", k = 1
     * Output: true
     * Explanation: The binary codes of length 1 are "0" and "1", it is clear that both exist as a substring.
     * Example 4:
     *
     * Input: s = "0110", k = 2
     * Output: false
     * Explanation: The binary code "00" is of length 2 and doesn't exist in the array.
     * Example 5:
     *
     * Input: s = "0000000001011100", k = 4
     * Output: false
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 5 * 10^5
     * s consists of 0's and 1's only.
     * 1 <= k <= 20
     */
    public boolean hasAllCodes(String s, int k) {
        if (s.length() < k) {
            return false;
        }
        int curNum = 0;
        for (int i = 0; i < k; ++i) {
            curNum <<= 1;
            curNum ^= (s.charAt(i)-'0');
        }
        boolean[] occured = new boolean[1 << k];
        occured[curNum] = true;
        int right = k;
        while (right < s.length()) {
            curNum <<= 1;
            curNum ^= (s.charAt(right) - '0');
            curNum &= ~(1 << k);
            occured[curNum] = true;
            ++right;
        }
        for (boolean res : occured) {
            if (!res) {
                return false;
            }
        }
        return true;
    }

    /**
     * 338. Counting Bits
     * Medium
     *
     * Given a non negative integer number num.
     * For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary
     * representation and return them as an array.
     */
    public int[] countBits(int num) {
        int[] res = new int[num+1];
        for (int i = 1; i <= num; ++i) {
            res[i] = res[i >> 1] + (i & 1);
        }
        return res;
    }
}
