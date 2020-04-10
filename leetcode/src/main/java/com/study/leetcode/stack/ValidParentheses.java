package com.study.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author fanqie
 * @date 2020/3/7
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        final Deque<Character> stack = new ArrayDeque<>();
        final int[] map = new int[256];
        map['}'] = '{';
        map[')'] = '(';
        map[']'] = '[';

        for (final char c : s.toCharArray()) {
            if (c == '{' || c == '(' || c == '[') {
                stack.offerLast(c);
            } else {
                final Character top = stack.pollLast();
                if (top == null || map[c] != top) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }


    /**
     * 32. Longest Valid Parentheses
     * Hard
     *
     * 2965
     *
     * 126
     *
     * Add to List
     *
     * Share
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
     *
     * Example 1:
     *
     * Input: "(()"
     * Output: 2
     * Explanation: The longest valid parentheses substring is "()"
     * Example 2:
     *
     * Input: ")()())"
     * Output: 4
     * Explanation: The longest valid parentheses substring is "()()"
     */
    public int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>(s.length());
        stack.offerLast(-1);
        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            switch (s.charAt(i)) {
                case '(':
                    stack.offerLast(i);
                    break;
                case ')':
                    stack.pollLast();
                    if (stack.isEmpty()) {
                        stack.offerLast(i);
                    } else {
                        int len = i - stack.peekLast();
                        if (res < len) {
                            res = len;
                        }
                    }
                    break;
                default:
            }
        }
        return res;
    }
}
