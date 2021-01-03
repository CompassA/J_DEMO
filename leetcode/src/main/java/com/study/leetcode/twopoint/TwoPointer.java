package com.study.leetcode.twopoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author fanqie
 * @date 2020/6/7
 */
public class TwoPointer {

    //==============================Easy=======================

    /**
     * 88. Merge Sorted Array
     * https://leetcode.com/problems/merge-sorted-array/
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int cur = m + n - 1;
        int index1 = m - 1;
        int index2 = n - 1;
        while (index1 >= 0 && index2 >= 0) {
            if (nums1[index1] > nums2[index2]) {
                nums1[cur--] = nums1[index1--];
            } else {
                nums1[cur--] = nums2[index2--];
            }
        }
        while (index2 >= 0) {
            nums1[cur--] = nums2[index2--];
        }
    }

    /**
     * 680. Valid Palindrome II
     * https://leetcode.com/problems/valid-palindrome-ii/
     */
    public boolean validPalindrome(String s) {
        if (s.length() < 2) {
            return true;
        }
        return valid(s.toCharArray(), 0, s.length() - 1, 1);
    }

    private boolean valid(char[] str, int left, int right, int chance) {
        while (left < right) {
            if (str[left] != str[right]) {
                return chance > 0 && (valid(str, left, right - 1, 0) || valid(str, left + 1, right, 0));
            } else {
                ++left;
                --right;
            }
        }
        return true;
    }

    //=======================================Medium==================================
    /**
     * 633. Sum of Square Numbers
     * https://leetcode.com/problems/sum-of-square-numbers/
     */
    public boolean judgeSquareSum(int c) {
        int left = 0, right = (int) Math.sqrt(c);
        while (left <= right) {
            int sum = left * left + right * right;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                --right;
            } else {
                ++left;
            }
        }
        return false;
    }

    /**
     * 1471. The k Strongest Values in an Array
     * https://leetcode.com/problems/the-k-strongest-values-in-an-array/
     */
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int[] res = new int[k];
        int m = arr[(arr.length - 1) / 2];
        int left = 0, right = arr.length - 1;
        for (int i = 0; i < k; ++i) {
            if (Math.abs(arr[left] - m) > Math.abs(arr[right] - m)) {
                res[i] = arr[left];
                ++left;
            } else {
                res[i] = arr[right];
                --right;
            }
        }
        return res;
    }

    /**
     * 524. Longest Word in Dictionary through Deleting
     * https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/
     */
    public String findLongestWord(String s, List<String> d) {
        if (d.isEmpty()) {
            return "";
        }
        Map<Integer, List<String>> dictionary = new TreeMap<>((a, b) -> b - a);
        for (String str : d) {
            if (s.length() < str.length()) {
                continue;
            }
            dictionary.putIfAbsent(str.length(), new ArrayList<>());
            dictionary.get(str.length()).add(str);
        }
        for (int length : dictionary.keySet()) {
            List<String> strings = dictionary.get(length);
            Collections.sort(strings);
            for (String str : strings) {
                if (isSub(str, s)) {
                    return str;
                }
            }
        }
        return "";
    }

    private boolean isSub(String str, String target) {
        int curIndex = 0;
        for (int i = 0; i < str.length(); ++i) {
            boolean found = false;
            while (curIndex < target.length()) {
                if (str.charAt(i) == target.charAt(curIndex)) {
                    found = true;
                    ++curIndex;
                    break;
                } else {
                    ++curIndex;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }
}
