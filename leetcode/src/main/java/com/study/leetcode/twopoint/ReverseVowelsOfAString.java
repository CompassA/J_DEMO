package com.study.leetcode.twopoint;

import java.util.HashSet;
import java.util.Set;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class ReverseVowelsOfAString {
    /**
     * 345. Reverse Vowels of a String
     *
     * Write a function that takes a string as input and reverse only the vowels of a string.
     */
    public String reverseVowels(String s) {
        final Set<Character> target = new HashSet<Character>() {{
            add('a'); add('e'); add('i'); add('o'); add('u');
            add('A'); add('E'); add('I'); add('O'); add('U');
        }};

        final char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            while (left < right && !target.contains(chars[left])) {
                ++left;
            }
            while (left < right && !target.contains(chars[right])) {
                --right;
            }
            if (left < right) {
                char tmp = chars[left];
                chars[left] = chars[right];
                chars[right] = tmp;
                ++left;
                --right;
            } else {
                break;
            }
        }
        return String.copyValueOf(chars);
    }
}
