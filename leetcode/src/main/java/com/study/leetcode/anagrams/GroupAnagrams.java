package com.study.leetcode.anagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class GroupAnagrams {

    /**
     * 49. Group Anagrams
     * Medium
     *
     * 2296
     *
     * 138
     *
     * Favorite
     *
     * Share
     * Given an array of strings, group anagrams together.
     *
     * Example:
     *
     * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * Output:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     * Note:
     *
     * All inputs will be in lowercase.
     * The order of your output does not matter.
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        final Map<String, List<String>> map = new HashMap<>(0);
        final List<List<String>> res = new ArrayList<>(0);
        for (final String str : strs) {
            final char[] freq = new char[26];
            for (int i = 0; i < str.length(); ++i) {
                freq[str.charAt(i) - 'a']++;
            }
            final String key = String.valueOf(freq);
            if (!map.containsKey(key)) {
                final List<String> anagrams = new ArrayList<>(0);
                map.put(key, anagrams);
                res.add(anagrams);
            }
            map.get(key).add(str);
        }
        return res;
    }
}
