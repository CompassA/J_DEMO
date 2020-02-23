package com.study.leetcode.math;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class Pow {

    /**
     * NO.50 Pow(x, n)
     *
     * Implement pow(x, n), which calculates x raised to the power n (x^n).
     */
    public double myPow(double x, int n) {
        //对n进行检查
        if (n == 0) {
            return 1;
        } else if (n < 0 && n != Integer.MIN_VALUE) {
            return myPow(1 / x, -n);
        }

        //递归
        final double t = myPow(x, n / 2);
        if (n % 2 == 1) {
            return t * t * x;
        }
        return t * t;
    }
}
