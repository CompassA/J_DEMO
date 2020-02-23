package com.study.leetcode.slidewindow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class FindAllAnagramsInAString {


    /**
     * 438. Find All Anagrams in a String
     *
     * Given a string s and a non-empty string p,
     * find all the start indices of p's anagrams in s.
     *
     * Strings consists of lowercase English letters only and the length of
     * both strings s and p will not be larger than 20,100.
     *
     * The order of output does not matter.
     */
    public List<Integer> findAnagrams(String s, String p) {
        final List<Integer> res = new ArrayList<>(0);
        if (s.length() < p.length()) {
            return res;
        }

        int left = 0, right = -1, charInTarget = 0;
        final char[] target = p.toCharArray();
        final char[] chars = s.toCharArray();
        final int[] cnt = new int[256];
        for (final char c : target) {
            ++cnt[c];
        }

        while (right + 1 < chars.length) {
            //增加右边框
            ++right;
            if (cnt[chars[right]] > 0) {
                ++charInTarget;
            }
            --cnt[chars[right]];

            //循环增加左边框
            while (charInTarget == target.length) {
                if (right - left + 1 == target.length) {
                    res.add(left);
                }
                ++cnt[chars[left]];
                if (cnt[chars[left]] > 0) {
                    --charInTarget;
                }
                ++left;
            }
        }
        return res;
    }
}
