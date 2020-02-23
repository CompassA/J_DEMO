package com.study.leetcode.bfs;

import java.util.LinkedList;
import java.util.List;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class LetterCombinationsOfAPhoneNumber {
    /**
     * 17. Letter Combinations of a Phone Number
     * Medium
     *
     * 2854
     *
     * 351
     *
     * Favorite
     *
     * Share
     * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
     *
     * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
     *
     *
     *
     * Example:
     *
     * Input: "23"
     * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     */
    public List<String> letterCombinations(String digits) {
        final String[] mapping = new String[]
                {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        final LinkedList<String> queue = new LinkedList<>();
        queue.offer("");
        int curLength = 0;
        for (int i = 0; i < digits.length(); ++i) {
            final String chars = mapping[Character.getNumericValue(digits.charAt(i))];
            while (queue.peek().length() == curLength) {
                final String peek = queue.peek();
                queue.poll();
                for (int j = 0; j < chars.length(); ++j) {
                    queue.offer(peek + chars.charAt(j));
                }
            }
            ++curLength;
        }
        return curLength == 0 ? new LinkedList<>() : queue;
    }
}
