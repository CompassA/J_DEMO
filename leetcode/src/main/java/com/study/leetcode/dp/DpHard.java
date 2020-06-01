package com.study.leetcode.dp;

/**
 * @author fanqie
 * @date 2020/6/1
 */
public class DpHard {

    /**
     * 123. Best Time to Buy and Sell Stock III
     * Hard
     *
     * Share
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     *
     * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
     *
     * Example 1:
     *
     * Input: [3,3,5,0,0,3,1,4]
     * Output: 6
     * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
     *              Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
     * Example 2:
     *
     * Input: [1,2,3,4,5]
     * Output: 4
     * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
     *              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
     *              engaging multiple transactions at the same time. You must sell before buying again.
     * Example 3:
     *
     * Input: [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     */
    public int maxProfit3(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        //toBuy -> toSell -> toBuy -> toSell -> end
        int s1 = 0;
        int s2 = -prices[0];
        int s3 = 0;
        int s4 = Integer.MIN_VALUE;
        int s5 = Integer.MIN_VALUE;

        for (int i = 1; i < prices.length; ++i) {
            int newS2 = Math.max(s2, s1 - prices[i]);
            int newS3 = Math.max(s2 + prices[i], s3);
            int newS4 = Math.max(s4, s3 - prices[i]);
            int newS5 = Math.max(s5, s4 + prices[i]);
            s2 = newS2;
            s3 = newS3;
            s4 = newS4;
            s5 = newS5;
        }

        return Math.max(s3, s5);
    }

    public int maxProfit3Other(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        //toBuy -> toSell -> toBuy -> toSell -> end
        int[][] state = new int[prices.length][5];
        state[0][1] = -prices[0];

        for (int i = 1; i < prices.length; ++i) {
            int ratio = -1;
            for (int j = 1; j < i + 1 && j < 5; ++j) {
                state[i][j] = Math.max(state[i-1][j], state[i-1][j-1] + prices[i] * ratio);
                ratio = -ratio;
            }
            if (i + 1 < 5) {
                state[i][i+1] = state[i-1][i] + prices[i] * ratio;
            }
        }
        int res = Math.max(state[prices.length-1][2], state[prices.length-1][4]);
        return Math.max(res, 0);
    }

    /**
     * 188. Best Time to Buy and Sell Stock IV
     * Hard
     *
     * Share
     * Say you have an array for which the i-th element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete at most k transactions.
     *
     * Note:
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     *
     * Example 1:
     *
     * Input: [2,4,1], k = 2
     * Output: 2
     * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
     * Example 2:
     *
     * Input: [3,2,6,5,0,3], k = 2
     * Output: 7
     * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
     *              Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
     */
    public int maxProfit4(int k, int[] prices) {
        if (prices.length / 2 <= k) {
            int profit = 0;
            for (int i = 1; i < prices.length; ++i) {
                int diff = prices[i] - prices[i-1];
                if (diff > 0) {
                    profit += diff;
                }
            }
            return profit;
        }
        int[][] maxProfit = new int[k+1][prices.length];
        for (int i = 1; i <= k; ++i) {
            for (int j = 1; j < prices.length; ++j) {
                int max = prices[j] - prices[0];
                for (int t = 1; t < j; ++t) {
                    max = Math.max(max, maxProfit[i-1][t-1] + prices[j] - prices[t]);
                }
                maxProfit[i][j] = Math.max(max, maxProfit[i][j-1]);
            }
        }
        return maxProfit[k][prices.length-1];
    }

    public int maxProfit4Other(int k, int[] prices) {
        if (prices.length / 2 <= k) {
            int profit = 0;
            for (int i = 1; i < prices.length; ++i) {
                int diff = prices[i] - prices[i-1];
                if (diff > 0) {
                    profit += diff;
                }
            }
            return profit;
        }
        int[][] maxProfit = new int[k+1][prices.length];
        for (int i = 1; i <= k; ++i) {
            int max = -prices[0];
            for (int j = 1; j < prices.length; ++j) {
                maxProfit[i][j] = Math.max(maxProfit[i][j-1], max + prices[j]);
                max = Math.max(max, maxProfit[i-1][j-1] - prices[j]);
            }
        }
        return maxProfit[k][prices.length-1];
    }

    /**
     * 1444 切披萨
     */
    private static class Solution1444 {
        int row;
        int col;
        int[][] preSum;
        Integer[][][] dp;
        public int ways(String[] pizza, int k) {
            if (pizza.length == 0) {
                return 0;
            }
            row = pizza.length;
            col = pizza[0].length();
            preSum = new int[row+1][col+1];
            for (int i = row - 1; i >=0 ; --i) {
                for (int j = col - 1; j >= 0; --j) {
                    preSum[i][j] = preSum[i+1][j] + preSum[i][j+1] - preSum[i+1][j+1];
                    if (pizza[i].charAt(j) == 'A') {
                        preSum[i][j] += 1;
                    }
                }
            }
            dp = new Integer[k][pizza.length][pizza[0].length()];
            return dfs(k-1, 0, 0);
        }

        private int dfs(int k, int x, int y) {
            if (preSum[x][y] == 0) {
                return 0;
            }
            if (k == 0) {
                return 1;
            }
            if (dp[k][x][y] != null) {
                return dp[k][x][y];
            }
            int res = 0;
            for (int i = x + 1; i < row; ++i) {
                if (preSum[x][y] - preSum[i][y] > 0) {
                    res = (res + dfs(k-1, i, y)) % 1000000007;
                }
            }
            for (int i = y + 1; i < col; ++i) {
                if (preSum[x][y] - preSum[x][i] > 0) {
                    res = (res + dfs(k-1, x, i)) % 1000000007;
                }
            }
            dp[k][x][y] = res;
            return res;
        }
    }

    /**
     * 1458. Max Dot Product of Two Subsequences

     * Share
     * Given two arrays nums1 and nums2.
     *
     * Return the maximum dot product between non-empty subsequences of nums1 and nums2 with the same length.
     *
     * A subsequence of a array is a new array which is formed from the original array by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, [2,3,5] is a subsequence of [1,2,3,4,5] while [1,5,3] is not).
     *
     *
     *
     * Example 1:
     *
     * Input: nums1 = [2,1,-2,5], nums2 = [3,0,-6]
     * Output: 18
     * Explanation: Take subsequence [2,-2] from nums1 and subsequence [3,-6] from nums2.
     * Their dot product is (2*3 + (-2)*(-6)) = 18.
     * Example 2:
     *
     * Input: nums1 = [3,-2], nums2 = [2,-6,7]
     * Output: 21
     * Explanation: Take subsequence [3] from nums1 and subsequence [7] from nums2.
     * Their dot product is (3*7) = 21.
     * Example 3:
     *
     * Input: nums1 = [-1,-1], nums2 = [1,1]
     * Output: -1
     * Explanation: Take subsequence [-1] from nums1 and subsequence [1] from nums2.
     * Their dot product is -1.
     *
     *
     * Constraints:
     *
     * 1 <= nums1.length, nums2.length <= 500
     * -1000 <= nums1[i], nums2[i] <= 1000
     */
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length][nums2.length];
        dp[0][0] = nums1[0] * nums2[0];
        for (int i = 1; i < nums1.length; ++i) {
            dp[i][0] = Math.max(dp[i-1][0], nums1[i] * nums2[0]);
        }
        for (int i = 1; i < nums2.length; ++i) {
            dp[0][i] = Math.max(dp[0][i-1], nums1[0] * nums2[i]);
        }
        for (int i = 1; i < nums1.length; ++i) {
            for (int j = 1; j < nums2.length; ++j) {
                int mul = nums1[i] * nums2[j];
                dp[i][j] = Math.max(dp[i-1][j-1] + mul, mul);
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
                dp[i][j] = Math.max(dp[i][j], dp[i][j-1]);
            }
        }
        return dp[nums1.length-1][nums2.length-1];
    }
}
