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
}
