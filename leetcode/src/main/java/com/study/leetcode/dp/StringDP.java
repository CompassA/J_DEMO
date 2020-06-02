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

    /**
     * 72. Edit Distance
     * Hard
     *
     * 3431
     *
     * 52
     *
     * Add to List
     *
     * Share
     * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
     *
     * You have the following 3 operations permitted on a word:
     *
     * Insert a character
     * Delete a character
     * Replace a character
     * Example 1:
     *
     * Input: word1 = "horse", word2 = "ros"
     * Output: 3
     * Explanation:
     * horse -> rorse (replace 'h' with 'r')
     * rorse -> rose (remove 'r')
     * rose -> ros (remove 'e')
     * Example 2:
     *
     * Input: word1 = "intention", word2 = "execution"
     * Output: 5
     * Explanation:
     * intention -> inention (remove 't')
     * inention -> enention (replace 'i' with 'e')
     * enention -> exention (replace 'n' with 'x')
     * exention -> exection (replace 'n' with 'c')
     * exection -> execution (insert 'u')
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i = 0; i < word1.length(); ++i) {
            dp[i+1][0] = i+1;
        }
        for (int j = 0; j < word2.length(); ++j) {
            dp[0][j+1] = j+1;
        }
        for (int i = 0; i < word1.length(); ++i) {
            for (int j = 0; j < word2.length(); ++j) {
                int possibleResA = dp[i][j] + (word1.charAt(i) == word2.charAt(j) ? 0 : 1);
                int possibleResB = dp[i+1][j]+1;
                int possibleResC = dp[i][j+1]+1;
                dp[i+1][j+1] = Math.min(possibleResA, Math.min(possibleResB, possibleResC));
            }
        }
        return dp[word1.length()][word2.length()];
    }

    /**
     * 44. Wildcard Matching
     * Hard
     *
     * 1819
     *
     * 101
     *
     * Add to List
     *
     * Share
     * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
     *
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence).
     * The matching should cover the entire input string (not partial).
     *
     * Note:
     *
     * s could be empty and contains only lowercase letters a-z.
     * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
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
     * p = "*"
     * Output: true
     * Explanation: '*' matches any sequence.
     * Example 3:
     *
     * Input:
     * s = "cb"
     * p = "?a"
     * Output: false
     * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
     * Example 4:
     *
     * Input:
     * s = "adceb"
     * p = "*a*b"
     * Output: true
     * Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
     * Example 5:
     *
     * Input:
     * s = "acdcb"
     * p = "a*c?b"
     * Output: false
     */
    public boolean isMatch44(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int i = 0; i < p.length() && p.charAt(i) == '*'; ++i) {
            for (int j = 0; j <= s.length(); ++j) {
                dp[j][i+1] = true;
            }
        }
        for (int i = 0; i < s.length(); ++i) {
            for (int j = 0; j < p.length(); ++j) {
                char curPatternChar = p.charAt(j);
                if (curPatternChar == '?') {
                    dp[i+1][j+1] = dp[i][j];
                } else if (curPatternChar == '*') {
                    dp[i+1][j+1] = dp[i][j+1] || dp[i+1][j] || dp[i][j];
                } else {
                    dp[i+1][j+1] = dp[i][j] && curPatternChar == s.charAt(i);
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    /**
     * 97. Interleaving String
     * Hard
     *
     * 1313
     *
     * 78
     *
     * Add to List
     *
     * Share
     * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
     *
     * Example 1:
     *
     * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
     * Output: true
     * Example 2:
     *
     * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
     * Output: false
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        boolean[][][] dp = new boolean[s3.length()+1][s1.length()+1][s2.length()+1];
        dp[0][0][0] = true;
        for (int i = 1; i <= s3.length(); ++i) {
            for (int j = 0; j <= s1.length() && j <= i; ++j) {
                int k = i - j;
                if (k > s2.length()) {
                    continue;
                }
                if (j == 0) {
                    dp[i][j][k] = dp[i-1][j][k-1] && s2.charAt(k-1) == s3.charAt(i-1);
                } else if (k == 0) {
                    dp[i][j][k] = dp[i-1][j-1][k] && s1.charAt(j-1) == s3.charAt(i-1);
                } else {
                    dp[i][j][k] =
                            (dp[i-1][j-1][k] && s1.charAt(j-1) == s3.charAt(i-1)) ||
                                    (dp[i-1][j][k-1] && s2.charAt(k-1) == s3.charAt(i-1));
                }
            }
        }
        return dp[s3.length()][s1.length()][s2.length()];
    }

    public boolean isInterleaveII(String s1, String s2, String s3) {
        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
        dp[0][0] = true;
        for (int i = 1; i <= s3.length(); ++i) {
            boolean[][] tmpDp = new boolean[s1.length()+1][s2.length()+1];
            for (int j = 0; j <= s1.length() && j <= i; ++j) {
                int k = i - j;
                if (k > s2.length()) {
                    continue;
                }
                if (j == 0) {
                    tmpDp[j][k] = dp[j][k-1] && s2.charAt(k-1) == s3.charAt(i-1);
                } else if (k == 0) {
                    tmpDp[j][k] = dp[j-1][k] && s1.charAt(j-1) == s3.charAt(i-1);
                } else {
                    tmpDp[j][k] =
                            (dp[j-1][k] && s1.charAt(j-1) == s3.charAt(i-1)) ||
                                    (dp[j][k-1] && s2.charAt(k-1) == s3.charAt(i-1));
                }
            }
            dp = tmpDp;
        }
        return dp[s1.length()][s2.length()];
    }
}
