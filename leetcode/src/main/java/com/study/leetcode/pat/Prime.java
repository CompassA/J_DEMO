package com.study.leetcode.pat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author fanqie
 * @date 2020/3/29
 */
public class Prime {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(reader.readLine());

        int m = Integer.parseInt(tokens.nextToken());
        int n = Integer.parseInt(tokens.nextToken());

        //way1(m, n);
        way2(m, n);
    }

    private static void way1(int m, int n) {
        int[] table = getPrimeTable();
        int targetNum = n - m + 1;

        for (int i = 0; i < targetNum; ++i) {
            System.out.printf("%d%c", table[i + m - 1], (i == targetNum - 1 || i % 10 == 9) ? '\n' : ' ');
        }

    }

    private static int[] getPrimeTable() {
        boolean[] isNotPrime = new boolean[1000001];
        int[] table = new int[1000001];
        int index = 0;
        isNotPrime[0] = isNotPrime[1] = true;
        for (int i = 2; i < isNotPrime.length; ++i) {
            if (!isNotPrime[i]) {
                table[index++] = i;
                for (int j = i + i; j < isNotPrime.length; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }
        return table;
    }

    /*---------------------------------------------------*/
    private static void way2(int m, int n) {
        int index = 0;
        int[] primes = new int[n];

        int curNum = 2;
        while (index < n) {
            if (isPrime(curNum)) {
                primes[index++] = curNum;
            }
            ++curNum;
        }

        int cnt = 1;
        for (int i = m; i <= n; ++i) {
            System.out.printf("%d%c", primes[i-1], (cnt % 10 == 0 || i == n) ? '\n' : ' ');
            ++cnt;
        }
    }

    private static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        int limit = (int) Math.sqrt(num);
        for (int i = 2; i <= limit; ++i) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}