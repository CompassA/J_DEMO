package com.study.leetcode.str;

/**
 * @author fanqie
 * @date 2020/4/5
 */
public class StringProblem {
    /**
     * 6. ZigZag Conversion
     * Medium
     *
     * 1472
     *
     * 4225
     *
     * Add to List
     *
     * Share
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
     *
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * And then read line by line: "PAHNAPLSIIGYIR"
     *
     * Write the code that will take a string and make this conversion given a number of rows:
     *
     * string convert(string s, int numRows);
     * Example 1:
     *
     * Input: s = "PAYPALISHIRING", numRows = 3
     * Output: "PAHNAPLSIIGYIR"
     * Example 2:
     *
     * Input: s = "PAYPALISHIRING", numRows = 4
     * Output: "PINALSIGYAHRPI"
     * Explanation:
     *
     * P     I    N
     * A   L S  I G
     * Y A   H R
     * P     I
     */
    public String convert(String s, int numRows) {
        if (s.length() == 0 || numRows == 0) {
            return "";
        }
        if (numRows == 1) {
            return s;
        }
        int[] indexMap = new int[numRows * 2 - 2];
        for (int i = 1; i < numRows; ++i) {
            indexMap[i] = i;
            indexMap[indexMap.length-i] = i;
        }
        int index = 0;
        StringBuilder[] builders = new StringBuilder[numRows];
        //Arrays.fill 严重错误
        for (int i = 0; i < numRows; ++i) {
            builders[i] = new StringBuilder();
        }
        for (int i = 0; i < s.length(); ++i) {
            int nextIndex = indexMap[index];
            index = (index+1) % indexMap.length;
            builders[nextIndex].append(s.charAt(i));
        }
        for (int i = 1; i < builders.length; ++i) {
            builders[0].append(builders[i].toString());
        }
        return builders[0].toString();
    }

    /**
     * 28. Implement strStr()
     * Easy
     *
     * 1358
     *
     * 1742
     *
     * Add to List
     *
     * Share
     * Implement strStr().
     *
     * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     *
     * Example 1:
     *
     * Input: haystack = "hello", needle = "ll"
     * Output: 2
     * Example 2:
     *
     * Input: haystack = "aaaaa", needle = "bba"
     * Output: -1
     * Clarification:
     *
     * What should we return when needle is an empty string? This is a great question to ask during an interview.
     *
     * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
     */
    public int strStr(String haystack, String needle) {
        int end = haystack.length() - needle.length();
        for (int i = 0; i <= end; ++i) {
            if (haystack.startsWith(needle, i)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        new StringProblem().convert("PAYPALISHIRING", 3);
    }
}
