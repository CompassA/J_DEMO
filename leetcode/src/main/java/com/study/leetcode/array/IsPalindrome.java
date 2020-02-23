package com.study.leetcode.array;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class IsPalindrome {

    /**
     * 125. Valid Palindrome
     *
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     *
     * Note: For the purpose of this problem, we define empty string as valid palindrome.
     */
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            if (chars[i] >= 'a' && chars[i] <= 'z' ||
                    chars[i] >= '0' && chars[i] <= '9') {
                sb.append(chars[i]);
            } else if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] += 32;
                sb.append(chars[i]);
            }
        }
        chars = sb.toString().toCharArray();
        final int leftBorder = chars.length / 2;
        for (int i = 0; i < leftBorder; ++i) {
            final int otherPos = chars.length - 1 - i;
            if (chars[i] != chars[otherPos]) {
                return false;
            }
        }
        return true;
    }
}
