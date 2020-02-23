package com.study.leetcode.array;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class ReverseString {

    /**
     * 344. Reverse String
     *
     * Write a function that reverses a string. The input string is given as an array of characters char[].
     *
     * Do not allocate extra space for another array, you must do this by modifying the input array
     * in-place with O(1) extra memory.
     *
     * You may assume all the characters consist of printable ascii characters.
     */
    public void reverseString(char[] s) {
        final int leftBoundary = s.length / 2;
        for (int i = 0; i < leftBoundary; i++) {
            final int otherPos = s.length - 1 - i;
            char tmp = s[i];
            s[i] = s[otherPos];
            s[otherPos] = tmp;
        }
    }

}
