package com.study.leetcode.slidewindow;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class MinimumWindowSubstring {
    /**
     * 76. Minimum Window Substring
     *
     * Share
     * Given a string S and a string T,
     * find the minimum window in S which will contain
     * all the characters in T in complexity O(n).
     */
    public String minWindow(String s, String t) {
        String res = "";
        if (s.length() < t.length()) {
            return res;
        }
        final int[] cnt = new int[256];
        final char[] chars = s.toCharArray();
        final char[] target = t.toCharArray();
        for (final char c : target) {
            ++cnt[c];
        }

        int left = 0, right = -1;
        int charInTarget = 0;
        int minLength = Integer.MAX_VALUE;
        while (right + 1 < chars.length) {
            //扩张右边界
            ++right;
            //cnt[i] > 0 表示该字符在集合中
            if (cnt[chars[right]] > 0) {
                ++charInTarget;
            }
            //减少字符数量，表示字符进入窗口
            --cnt[chars[right]];

            while (charInTarget == target.length) {
                final int curLength = right - left + 1;
                if (curLength < minLength) {
                    minLength = curLength;
                    res = s.substring(left, right + 1);
                }
                cnt[chars[left]]++;
                if (cnt[chars[left]] > 0) {
                    --charInTarget;
                }
                ++left;
            }
        }
        return res;
    }
}
