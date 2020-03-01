package com.study.leetcode.dp;

/**
 * @author fanqie
 * @date 2020/3/1
 */
public class MinimumMaximumPathToReachTheTarget {
    /**
     * 746. Min Cost Climbing Stairs
     * Easy
     *
     * 1570
     *
     * 360
     *
     * Add to List
     *
     * Share
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
}
