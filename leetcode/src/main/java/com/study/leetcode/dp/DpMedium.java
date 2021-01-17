package com.study.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author tomato
 * Created on 2020.10.18
 */
public class DpMedium {

    /**
     * 746. Min Cost Climbing Stairs
     * Easy
     * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
     *
     * Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.
     *
     * Example 1:
     * Input: cost = [10, 15, 20]
     * Output: 15
     * Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
     * Example 2:
     * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
     * Output: 6
     * Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
     * Note:
     * cost will have a length in the range [2, 1000].
     * Every cost[i] will be an integer in the range [0, 999].
     */
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length == 0) {
            return 0;
        }
        if (cost.length == 1) {
            return cost[0];
        }
        if (cost.length == 2) {
            return Math.min(cost[0], cost[1]);
        }

        final int[] state = initState(cost);
        for (int i = 2; i < cost.length; ++i) {
            final int newState = Math.min(state[0], state[1]) + cost[i];
            state[0] = state[1];
            state[1] = newState;
        }

        return Math.min(state[0], state[1]);
    }

    private int[] initState(final int[] cost) {
        final int[] state = new int[2];
        state[0] = cost[0];
        state[1] = cost[1];
        return state;
    }

    /**
     * 53. Maximum Subarray
     * Given an integer array nums, find the contiguous subarray (containing at least one number)
     * which has the largest sum and return its sum.
     * Follow up:
     *
     * If you have figured out the O(n) solution, try coding another solution
     * using the divide and conquer approach,
     * which is more subtle.
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
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
     *
     * Note that you cannot sell a stock before you buy one.
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
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
     *
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
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
     * Given an integer array nums,
     * find the contiguous subarray within an array (containing at least one number)
     * which has the largest product.
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
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
     *
     * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
     *
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
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
     *
     * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
     *
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
     * Write a program to find the n-th ugly number.
     *
     * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
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
     * Write a program to find the nth super ugly number.
     *
     * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.
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
     * 70. Climbing Stairs
     * Easy
     * You are climbing a stair case. It takes n steps to reach to the top.
     *
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     *
     * Note: Given n will be a positive integer.
     */
    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int[] state = new int[n+1];
        state[0] = state[1] = 1;
        for (int i = 2; i <= n; ++i) {
            state[i] = state[i-1] + state[i-2];
        }
        return state[n];
    }

    /**
     * 62. Unique Paths
     * Medium
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     *
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     *
     * How many possible unique paths are there?
     *
     *
     * Above is a 7 x 3 grid. How many possible unique paths are there?
     *
     * Constraints:
     *
     * 1 <= m, n <= 100
     * It's guaranteed that the answer will be less than or equal to 2 * 10 ^ 9.
     */
    public int uniquePaths(int m, int n) {
        int[][] ways = new int[m][n];
        Arrays.fill(ways[0], 1);
        for (int i = 0; i < m; ++i) {
            ways[i][0] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                ways[i][j] = ways[i-1][j] + ways[i][j-1];
            }
        }
        return ways[m-1][n-1];
    }

    /**
     * 63. Unique Paths II
     * Medium
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     *
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     *
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     *
     *
     * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
     *
     */
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (m == 0 || n == 0 || grid[m-1][n-1] == 1) {
            return 0;
        }

        int[][] state = new int[m][n];
        for (int i = 0; i < n && grid[0][i] != 1; ++i) {
            state[0][i] = 1;
        }
        for (int i = 0; i < m && grid[i][0] != 1; ++i) {
            state[i][0] = 1;
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (grid[i][j] == 0) {
                    state[i][j] = state[i][j-1] + state[i-1][j];
                }
            }
        }

        return state[m-1][n-1];
    }

    /**
     * 91. Decode Ways
     * Medium
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:
     *
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * Given a non-empty string containing only digits, determine the total number of ways to decode it.
     */
    public int numDecodings(String s) {
        if (s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int[] state = new int[s.length()+1];
        state[1] = state[0] = 1;
        for (int i = 2; i <= s.length(); ++i) {
            if (s.charAt(i-1) != '0') {
                state[i] += state[i-1];
            }
            int num = (s.charAt(i-2) - '0') * 10 + s.charAt(i-1) - '0';
            if (num > 9 && num < 27) {
                state[i] += state[i-2];
            }
        }
        return state[s.length()];
    }

    /**
     * 300. Longest Increasing Subsequence
     * https://leetcode.com/problems/longest-increasing-subsequence/
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) { return 0; }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < nums.length; ++i) {
            int max = 1;
            for (int j = i - 1; j >= 0; --j) {
                if (nums[j] < nums[i] && max < (dp[j] + 1)) { max = dp[j] + 1; }
            }
            dp[i] = max;
            if (res < max) { res = max; }
        }
        return res;
    }

    public int lengthOfLIS2(int[] nums) {
        List<Integer> sorted = new ArrayList<>();
        for (int num : nums) {
            replaceOrAppend(sorted, num);
        }
        return sorted.size();
    }

    private void replaceOrAppend(List<Integer> nums, int target) {
        int left = 0, right = nums.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            int midNum = nums.get(mid);
            if (midNum < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (left == nums.size()) {
            nums.add(target);
        } else {
            nums.set(left, target);
        }
    }

    /**
     * 1143. Longest Common Subsequence
     * https://leetcode.com/problems/longest-common-subsequence/
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        for (int i = 1; i <= text1.length(); ++i) {
            for (int j = 1; j <= text2.length(); ++j) {
                dp[i][j] = Math.max(
                        dp[i-1][j-1] + (text1.charAt(i-1) == text2.charAt(j-1) ? 1 : 0),
                        Math.max(dp[i-1][j], dp[i][j-1])
                );
            }
        }
        return dp[text1.length()][text2.length()];
    }

    /**
     * 368. Largest Divisible Subset
     * Medium
     *
     * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:
     *
     * Si % Sj = 0 or Sj % Si = 0.
     *
     * If there are multiple solutions, return any subset is fine.
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums.length == 0) { return Collections.emptyList(); }
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        int[] pre = new int[nums.length];

        int maxIndex = 0;
        int max = 0;
        for (int i = 0; i < nums.length; ++i) {
            dp[i] = 1;
            pre[i] = -1;
            for (int j = i - 1; j >= 0; --j) {
                if (nums[i] % nums[j] == 0) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        pre[i] = j;
                    }
                }
            }
            if (dp[i] > max) { max = dp[i]; maxIndex = i; }
        }
        List<Integer> res = new ArrayList<>();
        int index = maxIndex;
        while (index != -1) {
            res.add(nums[index]);
            index = pre[index];
        }
        return res;
    }

    public int bestTeamScore(int[] scores, int[] ages) {
        List<Info> infos = new ArrayList<>();
        for (int i = 0; i < scores.length; ++i) {
            infos.add(new Info(scores[i], ages[i]));
        }
        infos.sort((a, b) -> {
            if (a.age != b.age) {
                return a.age - b.age;
            } else {
                return a.score - b.score;
            }
        });
        int[] increScores = new int[infos.size()];
        for (int i = 0; i < infos.size(); ++i) {
            increScores[i] = infos.get(i).score;
        }
        int[] dp = new int[infos.size()];
        dp[0] = increScores[0];
        int res = dp[0];
        for (int i = 1; i < increScores.length; ++i) {
            int preSumMax = 0;
            for (int j = i - 1; j >= 0; --j) {
                if (increScores[j] <= increScores[i]) {
                    preSumMax = Math.max(dp[j], preSumMax);
                }
            }
            dp[i] = preSumMax + increScores[i];
            res = Math.max(dp[i], res);
        }
        return res;
    }

    private static class Info {
        public int score;
        public int age;

        public Info(int score, int age) {
            this.score = score;
            this.age = age;
        }
    }

    /**
     * 714. Best Time to Buy and Sell Stock with Transaction Fee
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
     */
    public int maxProfit(int[] prices, int fee) {
        // to buy
        int state1 = 0;
        // to sell
        int state2 = -prices[0];
        for (int i = 1; i < prices.length; ++i) {
            int newState1 = Math.max(state1, state2 + prices[i] - fee);
            int newState2 = Math.max(state2, state1 - prices[i]);
            state1 = newState1;
            state2 = newState2;
        }
        return Math.max(state1, state2);
    }
}
