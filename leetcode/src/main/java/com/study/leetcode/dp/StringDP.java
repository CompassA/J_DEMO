package com.study.leetcode.dp;

import java.util.List;

/**
 * @author fanqie
 * @date 2020/3/31
 */
public class StringDP {

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
}
