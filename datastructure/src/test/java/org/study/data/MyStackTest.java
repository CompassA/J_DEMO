package org.study.data;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author fanqie
 * @date 2020/3/7
 */
public class MyStackTest {

    @Test
    public void baseTest() {
        final MyStack<Integer> stack = new MyStack<>();
        for (int i = 0; i < 100; ++i) {
            stack.push(i);
        }
        System.out.println(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    @Test
    public void patternTest() {
        Assert.assertTrue(isValid("[({{{}}})]"));
        Assert.assertTrue(isValid("{}"));
        Assert.assertTrue(isValid("{{}}[]()"));
        Assert.assertFalse(isValid("[({{{}}})"));
        Assert.assertFalse(isValid("({[]})({[]})({[]})({[]})({[]})({[]})({[]})" +
                "({[]})({[]})({[]})({[]})({[]})({[]}"));
        Assert.assertTrue(isValid("{({[()()()()()()()]})}{({[()()()()()()()]})}" +
                "{}{}{}{}{}{}{}{}{}{}{({[()()()()()()()]})}{({[()()()()()()()]})}" +
                "{({[()()()()()()()]})}"));

    }

    public boolean isValid(String s) {
        final MyStack<Character> stack = new MyStack<>();
        final int[] map = new int[256];
        map['}'] = '{';
        map[')'] = '(';
        map[']'] = '[';

        for (final char c : s.toCharArray()) {
            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
            } else {
                final Character top = stack.pop();
                if (top == null || map[c] != top) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
