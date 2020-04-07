package com.study.leetcode.dp;

import java.util.List;

/**
 * @author fanqie
 * @date 2020/3/31
 */
public class StringDP {

    public static void main(String[] args) {
        new StringDP().isMatch("a", ".*");
    }
    /**
     * 139. Word Break
     * Medium
     *
     * 3588
     *
     * 193
     *
     * Add to List
     *
     * Share
     * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
     *
     * Note:
     *
     * The same word in the dictionary may be reused multiple times in the segmentation.
     * You may assume the dictionary does not contain duplicate words.
     * Example 1:
     *
     * Input: s = "leetcode", wordDict = ["leet", "code"]
     * Output: true
     * Explanation: Return true because "leetcode" can be segmented as "leet code".
     * Example 2:
     *
     * Input: s = "applepenapple", wordDict = ["apple", "pen"]
     * Output: true
     * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
     *              Note that you are allowed to reuse a dictionary word.
     * Example 3:
     *
     * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * Output: false
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return false;
        }
        final boolean[] dp = new boolean[s.length()];
        for (int begin = 0; begin < s.length(); ++begin) {
            if (begin == 0 || dp[begin-1]) {
                for (String word : wordDict) {
                    if (s.startsWith(word, begin)) {
                        dp[begin + word.length() - 1] = true;
                    }
                }
            }
        }
        return dp[s.length()-1];
    }

    /**
     * 5. Longest Palindromic Substring
     * Medium
     *
     * 5862
     *
     * 486
     *
     * Add to List
     *
     * Share
     * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
     *
     * Example 1:
     *
     * Input: "babad"
     * Output: "bab"
     * Note: "aba" is also a valid answer.
     * Example 2:
     *
     * Input: "cbbd"
     * Output: "bb"
     */
    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }
        char[] str = s.toCharArray();
        int maxLen = 0;
        int start = 0;
        for (int i = 0; i < str.length; ++i) {
            int len1 = maxLen(str, i, i);
            int len2 = maxLen(str, i, i + 1);
            int newMaxLen = Math.max(len1, len2);
            if (newMaxLen > maxLen) {
                maxLen = newMaxLen;
                if (newMaxLen == len1) {
                    start = i - maxLen / 2;
                } else {
                    start = i - maxLen / 2 + 1;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    private int maxLen(char[] str, int i, int j) {
        while (i >= 0 && j < str.length && str[i] == str[j]) {
            --i;
            ++j;
        }
        return j - i - 1;
    }

    /**
     * 10. Regular Expression Matching
     * Hard
     *
     * 3749
     *
     * 647
     *
     * Add to List
     *
     * Share
     * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
     *
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     * The matching should cover the entire input string (not partial).
     *
     * Note:
     *
     * s could be empty and contains only lowercase letters a-z.
     * p could be empty and contains only lowercase letters a-z, and characters like . or *.
     * Example 1:
     *
     * Input:
     * s = "aa"
     * p = "a"
     * Output: false
     * Explanation: "a" does not match the entire string "aa".
     * Example 2:
     *
     * Input:
     * s = "aa"
     * p = "a*"
     * Output: true
     * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
     * Example 3:
     *
     * Input:
     * s = "ab"
     * p = ".*"
     * Output: true
     * Explanation: ".*" means "zero or more (*) of any character (.)".
     * Example 4:
     *
     * Input:
     * s = "aab"
     * p = "c*a*b"
     * Output: true
     * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
     * Example 5:
     *
     * Input:
     * s = "mississippi"
     * p = "mis*is*p*."
     * Output: false
     */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int j = 0; j < p.length(); ++j) {
            int pLen = j + 1;
            if (p.charAt(j) == '*') {
                dp[0][pLen] = dp[0][pLen-2];
            }
        }
        for (int i = 0; i < s.length(); ++i) {
            int sLen = i + 1;
            for (int j = 0; j < p.length(); ++j) {
                int pLen = j + 1;
                if (p.charAt(j) == '*') {
                    dp[sLen][pLen] = dp[sLen][pLen-2] || (dp[sLen-1][pLen] && (s.charAt(i) == p.charAt(j-1) || p.charAt(j-1) == '.'));
                } else {
                    dp[sLen][pLen] = dp[sLen-1][pLen-1] && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
