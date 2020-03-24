package com.study.leetcode.pat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1068 Find More Coins (30分)
 * Eva loves to collect coins from all over the universe, including some other planets like Mars. One day she visited a universal shopping mall which could accept all kinds of coins as payments. However, there was a special requirement of the payment: for each bill, she must pay the exact amount. Since she has as many as 10
 * ​4
 * ​​  coins with her, she definitely needs your help. You are supposed to tell her, for any given amount of money, whether or not she can find some coins to pay for it.
 *
 * Input Specification:
 * Each input file contains one test case. For each case, the first line contains 2 positive numbers: N (≤10
 * ​4
 * ​​ , the total number of coins) and M (≤10
 * ​2
 * ​​ , the amount of money Eva has to pay). The second line contains N face values of the coins, which are all positive numbers. All the numbers in a line are separated by a space.
 *
 * Output Specification:
 * For each test case, print in one line the face values V
 * ​1
 * ​​ ≤V
 * ​2
 * ​​ ≤⋯≤V
 * ​k
 * ​​  such that V
 * ​1
 * ​​ +V
 * ​2
 * ​​ +⋯+V
 * ​k
 * ​​ =M. All the numbers must be separated by a space, and there must be no extra space at the end of the line. If such a solution is not unique, output the smallest sequence. If there is no solution, output "No Solution" instead.
 *
 * Note: sequence {A[1], A[2], ...} is said to be "smaller" than sequence {B[1], B[2], ...} if there exists k≥1 such that A[i]=B[i] for all i<k, and A[k] < B[k].
 *
 * Sample Input 1:
 * 8 9
 * 5 9 8 7 2 3 4 1
 *
 *
 *
 * Sample Output 1:
 * 1 3 5
 *
 *
 *
 * Sample Input 2:
 * 4 8
 * 7 2 4 3
 *
 *
 *
 * Sample Output 2:
 * No Solution
 * @author fanqie
 * @date 2020/3/23
 */
public class FindMoreCoins {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(token.nextToken());
        int m = Integer.parseInt(token.nextToken());

        token = new StringTokenizer(reader.readLine());
        int[] coins = new int[n];
        for (int i = 0; i < n; ++i) {
            coins[i] = Integer.parseInt(token.nextToken());
        }
        Arrays.sort(coins);

        boolean[][] pack = new boolean[coins.length + 1][m + 1];
        pack[coins.length][0] = true;
        for (int i = coins.length - 1; i >= 0; --i) {
            int val = coins[i];
            for (int j = m; j >= 0; --j) {
                if (j >= val) {
                    pack[i][j] = pack[i+1][j] || pack[i+1][j - val];
                } else {
                    pack[i][j] = pack[i+1][j];
                }
            }
        }

        int[] res = new int[coins.length];
        int index = 0;
        int target = m;
        for (int i = 0; i < coins.length; ++i) {
            int val = coins[i];
            if (target >= val && pack[i + 1][target - val] && pack[i][target]) {
                target -= val;
                res[index++] = val;
            }
        }

        if (!pack[0][m]) {
            System.out.println("No Solution");
        }

        for (int i = 0; i < index; ++i) {
            System.out.printf("%d", res[i]);
            if (i != index - 1) {
                System.out.print(" ");
            }
        }
    }
}
