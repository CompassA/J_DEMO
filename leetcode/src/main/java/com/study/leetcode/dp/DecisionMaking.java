package com.study.leetcode.dp;

/**
 * @author fanqie
 * @date 2020/3/28
 */
public class DecisionMaking {

    public static void main(String[] args) {
        new DecisionMaking().maxProfit4(2, new int[] {1,2,4,7,11});
    }
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

    /**
     * 123. Best Time to Buy and Sell Stock III
     * Hard
     *
     * 1713
     *
     * 69
     *
     * Add to List
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

    /**
     * 188. Best Time to Buy and Sell Stock IV
     * Hard
     *
     * 1195
     *
     * 74
     *
     * Add to List
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
        if (prices.length == 0 || k == 0) {
            return 0;
        }
        if (prices.length <= k * 2) {
            return simpleSolve(prices);
        }
        int[][] state = new int[prices.length][2 * k + 1];
        state[0][0] = 0;
        state[0][1] = -prices[0];

        for (int i = 1; i < prices.length; ++i) {
            int ratio = -1;
            for (int j = 1; j < i + 1 && j < state[i].length; ++j) {
                state[i][j] = Math.max(state[i-1][j], state[i-1][j-1] + prices[i] * ratio);
                ratio = -ratio;
            }
            if (i + 1 < state[i].length) {
                state[i][i+1] = state[i-1][i] + prices[i] * ratio;
            }
        }

        int max = 0;
        for (int i = 2; i < state[prices.length-1].length; i+= 2) {
            if (max < state[prices.length-1][i]) {
                max = state[prices.length-1][i];
            }
        }
        return max;
    }

    private int simpleSolve(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; ++i) {
            res += Math.max(0, prices[i] - prices[i-1]);
        }
        return res;
    }

    /**
     * 152. Maximum Product Subarray
     * Medium
     *
     * 3363
     *
     * 140
     *
     * Add to List
     *
     * Share
     * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
     *
     * Example 1:
     *
     * Input: [2,3,-2,4]
     * Output: 6
     * Explanation: [2,3] has the largest product 6.
     * Example 2:
     *
     * Input: [-2,0,-1]
     * Output: 0
     * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
     */
    public int maxProduct(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }

        int res = nums[0];
        int curMin = res;
        int curMax = res;

        for (int i = 1; i < nums.length; ++i) {
            int num = nums[i];
            int maxNextState = curMax * num;
            int minNextState = curMin * num;
            if (num == 0) {
                curMin = curMax = 0;
            } else if (num > 0) {
                curMin = Math.min(minNextState, num);
                curMax = Math.max(maxNextState, num);
            } else {
                curMin = Math.min(maxNextState, num);
                curMax = Math.max(minNextState, num);
            }

            if (curMax > res) {
                res = curMax;
            }
        }

        return res;
    }

    /**
     * 198. House Robber
     * Easy
     *
     * 3972
     *
     * 123
     *
     * Add to List
     *
     * Share
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
     *
     * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
     *
     * Example 1:
     *
     * Input: [1,2,3,1]
     * Output: 4
     * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
     *              Total amount you can rob = 1 + 3 = 4.
     * Example 2:
     *
     * Input: [2,7,9,3,1]
     * Output: 12
     * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
     *              Total amount you can rob = 2 + 9 + 1 = 12.
     */
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int s1 = nums[0];
        int s2 = 0;
        for (int i = 1; i < nums.length; ++i) {
            int newS1 = s2 + nums[i];
            int newS2 = Math.max(s2, s1);
            s1 = newS1;
            s2 = newS2;
        }
        return Math.max(s1, s2);
    }
}
