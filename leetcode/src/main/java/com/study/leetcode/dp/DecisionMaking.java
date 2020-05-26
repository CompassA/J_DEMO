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

    /**
     * 1458. Max Dot Product of Two Subsequences
     * Hard
     *
     * 150
     *
     * 3
     *
     * Add to List
     *
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

    public static void main(String[] args) {
        new DecisionMaking().maxDotProduct(new int[]{5,-4,-3}, new int[]{-4,-3,0,-4,2});
    }
}
