package com.study.leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fanqie
 * Created on 2020.09.15
 */
public class PackageProblem {

    //============================Medium=========================
    /**
     * 322. Coin Change
     * Medium
     * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
     *
     * Example 1:
     *
     * Input: coins = [1, 2, 5], amount = 11
     * Output: 3
     * Explanation: 11 = 5 + 5 + 1
     * Example 2:
     *
     * Input: coins = [2], amount = 3
     * Output: -1
     * Note:
     * You may assume that you have an infinite number of each kind of coin.
     */
    public int coinChange(int[] coins, int amount) {
        final int impossible = Integer.MAX_VALUE;
        int[] pack = new int[amount+1];
        Arrays.fill(pack, impossible);
        pack[0] = 0;

        for (final int coin : coins) {
            for (int i = coin; i <= amount; ++i) {
                if (pack[i - coin] != impossible) {
                    pack[i] = Math.min(pack[i - coin] + 1, pack[i]);
                }
            }
        }
        return pack[amount] == impossible ? -1 : pack[amount];
    }

    /**
     * 518. Coin Change 2
     * Medium
     * You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.
     *
     *
     *
     * Example 1:
     *
     * Input: amount = 5, coins = [1, 2, 5]
     * Output: 4
     * Explanation: there are four ways to make up the amount:
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     * Example 2:
     *
     * Input: amount = 3, coins = [2]
     * Output: 0
     * Explanation: the amount of 3 cannot be made up just with coins of 2.
     * Example 3:
     *
     * Input: amount = 10, coins = [10]
     * Output: 1
     *
     *
     * Note:
     *
     * You can assume that
     *
     * 0 <= amount <= 5000
     * 1 <= coin <= 5000
     * the number of coins is less than 500
     * the answer is guaranteed to fit into signed 32-bit integer
     */
    public int change(int amount, int[] coins) {
        int[] pack = new int[amount+1];
        Arrays.fill(pack, 0);
        pack[0] = 1;

        for (final int coin : coins) {
            for (int i = coin; i <= amount; ++i) {
                pack[i] += pack[i - coin];
            }
        }

        return pack[amount];
    }

    /** 279. Perfect Squares Medium https://leetcode.com/problems/perfect-squares */
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, 1, dp.length, Integer.MAX_VALUE);
        for (int i = 1; i <= n; ++i) {
            int limit = (int) Math.floor(Math.sqrt(i));
            for (int j = 1; j <= limit; ++j) {
                dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
            }
        }
        return dp[n];
    }

    /**
     * 416. Partition Equal Subset Sum
     * Medium
     * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
     *
     * Note:
     *
     * Each of the array element will not exceed 100.
     * The array size will not exceed 200.
     *
     *
     * Example 1:
     *
     * Input: [1, 5, 11, 5]
     *
     * Output: true
     *
     * Explanation: The array can be partitioned as [1, 5, 5] and [11].
     *
     *
     * Example 2:
     *
     * Input: [1, 2, 3, 5]
     *
     * Output: false
     *
     * Explanation: The array cannot be partitioned into equal sum subsets.
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) { sum += num; }
        if (sum % 2 == 1) { return false; }
        int target = sum / 2;
        boolean[] dp = new boolean[target+1];
        dp[0] = true;
        for (int num : nums) {
            for (int j = target; j >= num; --j) {
                dp[j] = dp[j] || dp[j-num];
            }
        }
        return dp[target];
    }

    /**
     * 494. Target Sum
     * Medium
     * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
     *
     * Find out how many ways to assign symbols to make sum of integers equal to target S.
     * Note:
     * The length of the given array is positive and will not exceed 20.
     * The sum of elements in the given array will not exceed 1000.
     * Your output answer is guaranteed to be fitted in a 32-bit integer.
     */
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (S > sum || S < -sum) {
            return 0;
        }
        int offset = sum;
        int max = sum + offset;
        int[][] dp = new int[nums.length+1][offset+sum+1];
        dp[0][0 + offset] = 1;
        for (int i = 1; i <= nums.length; ++i) {
            int curNum = nums[i-1];
            for (int j = 0; j <= max; ++j) {
                dp[i][j] = (j - curNum < 0 ? 0 : dp[i-1][j-curNum]) + (j + curNum > max ? 0 : dp[i-1][j+curNum]);
            }
        }
        return dp[nums.length][S + offset];
    }

    /**
     * 698. Partition to K Equal Sum Subsets
     * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            max = Math.max(num, max);
        }
        if (sum % k != 0 || sum / k < max) {
            return false;
        }
        Map<Integer, Boolean> dp = new HashMap<Integer, Boolean>();
        dp.put((1 << nums.length) - 1, true);
        return dfs(0, sum, sum / k, dp, nums);
    }

    private boolean dfs(int bitMap, int rest, int target, Map<Integer, Boolean> dp, int[] nums) {
        Boolean cache = dp.get(bitMap);
        if (cache != null) {
            return cache;
        }
        dp.put(bitMap, false);
        int curTarget = (rest - 1) % target + 1;
        for (int i = 0; i < nums.length; ++i) {
            if (((bitMap >> i) & 1) == 0 && nums[i] <= curTarget) {
                if (dfs(bitMap | (1 << i), rest - nums[i], target, dp, nums)) {
                    dp.put(bitMap, true);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 473. Matchsticks to Square
     * https://leetcode.com/problems/matchsticks-to-square/
     */
    public boolean makesquare(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if (sum == 0 || sum % 4 != 0 || sum / 4 < max) {
            return false;
        }
        Map<Integer, Boolean> dp = new HashMap<>();
        dp.put((1 << nums.length) - 1, true);
        return dfs(0, sum, sum / 4, nums, dp);
    }

    private boolean dfs(int visited, int left, int target, int[] nums, Map<Integer, Boolean> dp) {
        Boolean cache = dp.get(visited);
        if (cache != null) {
            return cache;
        }
        int curTarget = (left - 1) % target + 1;
        dp.put(visited, false);
        for (int i = 0; i < nums.length; ++i) {
            if ((visited >> i & 1) == 0 && nums[i] <= curTarget) {
                if (dfs(visited | (1 << i), left - nums[i], target, nums, dp)) {
                    dp.put(visited, true);
                    return true;
                }
            }
        }
        return false;
    }


    //======================Hard===========================
    /**
     * 1723. Find Minimum Time to Finish All Jobs
     * https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs/
     */
}
