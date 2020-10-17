package com.study.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author fanqie
 * @date 2020/3/8
 */
public class MinStack {

    private Deque<Integer> stack;
    private int min = Integer.MAX_VALUE;

    public MinStack() {
        stack = new ArrayDeque<>();
    }

    public void push(int x) {
        stack.offerLast(x);
        if (x <= min) {
            stack.offerLast(x);
            min = x;
        }
    }

    public void pop() {
        final int top = stack.pollLast();
        if (top == min) {
            stack.pollLast();
        }
    }

    public int top() {
        return stack.peekLast();
    }

    public int getMin() {
        return min;
    }

//    //method 2
//    private Deque<Integer> stack;
//    private Deque<Integer> minStack;
//
//    /** initialize your data structure here. */
//    public MinStack() {
//        stack = new ArrayDeque<Integer>();
//        minStack = new ArrayDeque<Integer>();
//    }
//
//    public void push(int x) {
//        stack.offerLast(x);
//        if (minStack.isEmpty() || minStack.peekLast() > x) {
//            minStack.offerLast(x);
//        } else {
//            minStack.offerLast(minStack.peekLast());
//        }
//    }
//
//    public void pop() {
//        stack.pollLast();
//        minStack.pollLast();
//    }
//
//    public int top() {
//        return stack.peekLast();
//    }
//
//    public int getMin() {
//        return minStack.peekLast();
//    }

}
