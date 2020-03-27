package com.study.leetcode.dp;

/**
 * @author fanqie
 * @date 2020/3/28
 */
public class DecisionMaking {

    /**
     * 53. Maximum Subarray
     * Easy
     *
     * 6607
     *
     * 299
     *
     * Add to List
     *
     * Share
     * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
     *
     * Example:
     *
     * Input: [-2,1,-3,4,-1,2,1,-5,4],
     * Output: 6
     * Explanation: [4,-1,2,1] has the largest sum = 6.
     * Follow up:
     *
     * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int res = nums[0];
        int curMax = nums[0];

        for (int i = 1; i < nums.length; ++i) {
            curMax = Math.max(nums[i], curMax + nums[i]);
            if (res < curMax) {
                res = curMax;
            }
        }

        return res;
    }

    /**
     * 121. Best Time to Buy and Sell Stock
     * Easy
     *
     * 4196
     *
     * 192
     *
     * Add to List
     *
     * Share
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
     *
     * Note that you cannot sell a stock before you buy one.
     *
     * Example 1:
     *
     * Input: [7,1,5,3,6,4]
     * Output: 5
     * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
     *              Not 7-1 = 6, as selling price needs to be larger than buying price.
     * Example 2:
     *
     * Input: [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int max = 0;
        int curMax = 0;
        for (int i = 1; i < prices.length; ++i) {
            int profit = prices[i] - prices[i-1];
            curMax = Math.max(profit, curMax + profit);
            if (max < curMax) {
                max = curMax;
            }
        }
        return max;
    }

    /**
     * 309. Best Time to Buy and Sell Stock with Cooldown
     * Medium
     *
     * 1977
     *
     * 74
     *
     * Add to List
     *
     * Share
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
     *
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
     * Example:
     *
     * Input: [1,2,3,0,2]
     * Output: 3
     * Explanation: transactions = [buy, sell, cooldown, buy, sell]
     */
    public int maxProfitWithCoolDown(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int s1 = 0;
        int s2 = -prices[0];
        int s3 = 0;

        for (int i = 1; i < prices.length; ++i) {
            int newS1 = Math.max(s3, s1);
            int newS2 = Math.max(s1 - prices[i], s2);
            int newS3 = s2 + prices[i];
            s1 = newS1;
            s2 = newS2;
            s3 = newS3;
        }

        return Math.max(s1, s3);
    }
}
