package com.study.leetcode.dp;

import java.util.PriorityQueue;
import java.util.Queue;

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

    /**
     * 213. House Robber II
     * Medium
     *
     * 1416
     *
     * 43
     *
     * Add to List
     *
     * Share
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
     *
     * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
     *
     * Example 1:
     *
     * Input: [2,3,2]
     * Output: 3
     * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
     *              because they are adjacent houses.
     * Example 2:
     *
     * Input: [1,2,3,1]
     * Output: 4
     * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
     *              Total amount you can rob = 1 + 3 = 4.
     */
    public int rob2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 1, nums.length), rob(nums, 0, nums.length-1));
    }

    private int rob(int[] nums, int start, int end) {
        int s0 = 0;
        int s1 = nums[start];
        for (int i = start + 1; i < end; ++i) {
            int newS0 = Math.max(s0, s1);
            int newS1 = s0 + nums[i];
            s0 = newS0;
            s1 = newS1;
        }
        return Math.max(s0, s1);
    }

    /**
     * 264. Ugly Number II
     * Medium
     *
     * 1395
     *
     * 84
     *
     * Add to List
     *
     * Share
     * Write a program to find the n-th ugly number.
     *
     * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
     *
     * Example:
     *
     * Input: n = 10
     * Output: 12
     * Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
     * Note:
     *
     * 1 is typically treated as an ugly number.
     * n does not exceed 1690.
     */
    public int nthUglyNumber(int n) {
        int[] res = new int[n];
        res[0] = 1;

        int pos2 = 0;
        int pos3 = 0;
        int pos5 = 0;
        for (int i = 1; i < n; ++i) {
            int nextMul2 = res[pos2] * 2;
            int nextMul3 = res[pos3] * 3;
            int nextMul5 = res[pos5] * 5;
            res[i] = Math.min(nextMul2, Math.min(nextMul3, nextMul5));
            if (res[i] == nextMul2) {
                ++pos2;
            }
            if (res[i] == nextMul3) {
                ++pos3;
            }
            if (res[i] == nextMul5) {
                ++pos5;
            }
        }
        return res[n-1];
    }

    /**
     * 313. Super Ugly Number
     * Medium
     *
     * 549
     *
     * 134
     *
     * Add to List
     *
     * Share
     * Write a program to find the nth super ugly number.
     *
     * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.
     *
     * Example:
     *
     * Input: n = 12, primes = [2,7,13,19]
     * Output: 32
     * Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12
     *              super ugly numbers given primes = [2,7,13,19] of size 4.
     * Note:
     *
     * 1 is a super ugly number for any given primes.
     * The given numbers in primes are in ascending order.
     * 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
     * The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        Queue<NextVal> minHeap = new PriorityQueue<>();
        for (int num : primes) {
            minHeap.offer(new NextVal(num, num, 1));
        }

        int[] uglyNum = new int[n];
        uglyNum[0] = 1;
        for (int i = 1; i < n; ++i) {
            uglyNum[i] = minHeap.peek().curVal;
            while (minHeap.peek().curVal == uglyNum[i]) {
                NextVal v = minHeap.poll();
                minHeap.offer(new NextVal(v.prime * uglyNum[v.index], v.prime, v.index+1));
            }
        }
        return uglyNum[n-1];
    }

    private static class NextVal implements Comparable<NextVal> {
        int curVal;
        int prime;
        int index;
        public NextVal(int _curVal, int _prime, int _index) {
            curVal = _curVal;
            prime = _prime;
            index = _index;
        }
        public int compareTo(NextVal other) {
            return this.curVal - other.curVal;
        }
    }
}
