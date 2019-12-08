package com.study.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fanqie
 * @date 2019/12/7
 */
public class MapAndSet {

    /**
     * 349. Intersection of Two Arrays
     *
     * Given two arrays, write a function to compute their intersection.
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        final Set<Integer> occurs = Arrays.stream(nums1)
                .boxed()
                .collect(Collectors.toSet());
        final Set<Integer> res = Arrays.stream(nums2)
                .filter(occurs::contains)
                .boxed()
                .collect(Collectors.toSet());
        final int[] nums = new int[res.size()];
        int index = 0;
        for (Integer num : res) {
            nums[index++] = num;
        }
        return nums;
    }

    /**
     * 350. Intersection of Two Arrays II
     *
     * Given two arrays, write a function to compute their intersection.
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        final Map<Integer, Integer> numCnt = new HashMap<>(0);
        for (final int num : nums1) {
            int cnt = numCnt.getOrDefault(num, 0);
            numCnt.put(num, cnt + 1);
        }
        final List<Integer> res = new ArrayList<>(0);
        for (final int num : nums2) {
            int cnt = numCnt.getOrDefault(num, 0);
            if (cnt > 0) {
                res.add(num);
                numCnt.put(num, cnt - 1);
            }
        }
        int[] nums = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            nums[i] = res.get(i);
        }
        return nums;
    }

    /**
     * 242. Valid Anagram
     *
     * Given two strings s and t ,
     * write a function to determine if t is an anagram of s.
     * Note:
     * You may assume the string contains only lowercase alphabets.
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] cnt = new int[256];
        for (final char c : s.toCharArray()) {
            ++cnt[c];
        }
        for (final char c : t.toCharArray()) {
            --cnt[c];
        }
        for (int c = 'a'; c <= 'z'; ++c) {
            if (cnt[c] != 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 202. Happy Number
     * A happy number is a number defined by the following process:
     * Starting with any positive integer,
     * replace the number by the sum of the squares of its digits,
     * and repeat the process until the number equals 1 (where it will stay),
     * or it loops endlessly in a cycle which does not include 1.
     * Those numbers for which this process ends in 1 are happy numbers.
     */
    public boolean isHappy(int n) {
        final Set<Integer> occurredAns = new HashSet<>(0);
        while (n != 1) {
            int sum = 0;
            do {
                sum += Math.pow(n % 10, 2);
                n /= 10;
            } while (n != 0);
            if (occurredAns.contains(sum)) {
                return false;
            }
            n = sum;
            occurredAns.add(sum);
        }
        return true;
    }

    /**
     * 290. Word Pattern
     * Easy
     *
     * 821
     *
     * 113
     *
     * Favorite
     *
     * Share
     * Given a pattern and a string str, find if str follows the same pattern.
     *
     * Here follow means a full match,
     * such that there is a bijection between a letter in pattern
     * and a non-empty word in str.
     */
    public boolean wordPattern(String pattern, String str) {
        final String[] strings = str.split(" ");
        final char[] p = pattern.toCharArray();
        if (strings.length != p.length) {
            return false;
        }

        final Map<Character, String> charToStr = new HashMap<>(0);
        final Map<String, Character> strToChar = new HashMap<>(0);
        for (int i = 0; i < p.length; ++i) {
            if (!charToStr.containsKey(p[i])
                    && !strToChar.containsKey(strings[i])) {
                charToStr.put(p[i], strings[i]);
                strToChar.put(strings[i], p[i]);
            } else if (charToStr.containsKey(p[i])
                    && strToChar.containsKey(strings[i])) {
                if (!charToStr.get(p[i]).equals(strings[i]) ||
                        !strToChar.get(strings[i]).equals(p[i])) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 205. Isomorphic Strings
     * Easy
     *
     * 985
     *
     * 286
     *
     * Favorite
     *
     * Share
     * Given two strings s and t, determine if they are isomorphic.
     *
     * Two strings are isomorphic if the characters in s can be replaced to get t.
     *
     * All occurrences of a character must be replaced with another character while preserving the order of characters.
     * No two characters may map to the same character but a character may map to itself.
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        final int[] sToT = new int[256];
        final int[] tToS = new int[256];

        final char[] sChars = s.toCharArray();
        final char[] tChars = t.toCharArray();

        //s和t每个位置的字母都有唯一的映射关系
        //遍历每个位置
        //检测映射关系是否被破坏
        for (int i = 0; i < sChars.length; ++i) {
            final char sChar = sChars[i];
            final char tChar = tChars[i];
            if (sToT[sChar] == 0 && tToS[tChar] == 0) {
                sToT[sChar] = tChar;
                tToS[tChar] = sChar;
            } else if (sToT[sChar] != 0 && tToS[tChar] != 0) {
                if (sToT[sChar] != tChar || tToS[tChar] != sChar) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 451. Sort Characters By Frequency
     * Medium
     *
     * 920
     *
     * 84
     *
     * Favorite
     *
     * Share
     * Given a string, sort it in decreasing order based on the frequency of characters.
     */
    public String frequencySort(String s) {
        if (s.length() == 0) {
            return "";
        }
        final Map<Character, Integer> cnt = new HashMap<>(0);
        final char[] chars = s.toCharArray();
        for (final char c : chars) {
            final int occurredNums = cnt.getOrDefault(c, 0);
            cnt.put(c, occurredNums + 1);
        }
        final Queue<Character> q = new PriorityQueue<>(cnt.keySet().size(),
                Comparator.comparing(character -> -cnt.get(character)));
        for (final char c : cnt.keySet()) {
            q.offer(c);
        }
        final char[] res = new char[chars.length];
        int index = 0;
        while (!q.isEmpty()) {
            final char curChar = q.poll();
            final int occurredTimes = cnt.get(curChar);
            for (int i = 0; i < occurredTimes; ++i) {
                res[index++] = curChar;
            }
        }
        return String.copyValueOf(res);
    }
}
