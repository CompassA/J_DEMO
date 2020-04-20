package com.study.leetcode.threads;

/**
 * 1115. Print FooBar Alternately
 * Medium
 *
 * 223
 *
 * 19
 *
 * Add to List
 *
 * Share
 * Suppose you are given the following code:
 *
 * class FooBar {
 *   public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 *   }
 *
 *   public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 *   }
 * }
 * The same instance of FooBar will be passed to two different threads. Thread A will call foo() while thread B will call bar(). Modify the given program to output "foobar" n times.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: "foobar"
 * Explanation: There are two threads being fired asynchronously. One of them calls foo(), while the other calls bar(). "foobar" is being output 1 time.
 * Example 2:
 *
 * Input: n = 2
 * Output: "foobarfoobar"
 * Explanation: "foobar" is being output 2 times.
 * @author fanqie
 * @date 2020/4/20
 */
public class PrintFooBarAlternately {

    private class FooBarSolution1 {
        private int n;
        private int state;

        public FooBarSolution1(int n) {
            this.n = n;
            this.state = 1;
        }

        public synchronized void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                while (this.state != 1) {
                    wait();
                }

                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                this.state = 2;
                notifyAll();
            }
        }

        public synchronized void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                while (this.state != 2) {
                    wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                this.state = 1;
                notifyAll();
            }
        }
    }
}
