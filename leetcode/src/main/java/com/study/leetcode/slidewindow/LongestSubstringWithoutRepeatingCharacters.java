package com.study.leetcode.slidewindow;

import java.util.Arrays;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class LongestSubstringWithoutRepeatingCharacters {
    /**
     * No.3 Longest Substring Without Repeating Characters
     *
     * Given a string, find the length of the longest substring without repeating characters.
     */
    public int lengthOfLongestSubstring(String s) {
        final int[] lastOccur = new int[256];
        final char[] str = s.toCharArray();
        //start表示未出现重复的左边界
        int res = 0, start = 0;
        for (int i = 0; i < 256; ++i) {
            lastOccur[i] = -1;
        }

        for (int i = 0; i < str.length; ++i) {
            //检查当前字母上次出现的位置是否在区间内
            //若在区间内则更新左边界
            if (lastOccur[str[i]] >= start) {
                start = lastOccur[str[i]] + 1;
            }
            //检查长度
            if (res < i - start + 1) {
                res = i - start + 1;
            }
            //更新位置
            lastOccur[str[i]] = i;
        }
        return res;

//        final char[] chars = s.toCharArray();
//        final int[] lastOccur = new int[256];
//        Arrays.fill(lastOccur, -1);
//
//        int left = 0, right = -1;
//        int length = 0;
//        while (left < chars.length) {
//            final int nextPos = right + 1;
//            if (nextPos < chars.length && lastOccur[chars[nextPos]] < left) {
//                lastOccur[chars[nextPos]] = nextPos;
//                right = nextPos;
//                int curLength = right - left + 1;
//                if (curLength > length) {
//                    length = curLength;
//                }
//            } else {
//                ++left;
//            }
//        }
//        return length;
    }
}
