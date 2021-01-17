package com.study.leetcode.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fanqie
 * @date 2020/6/1
 */
public class DpHard {

    /**
     * 123. Best Time to Buy and Sell Stock III
     * Hard
     *
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     *
     * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
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

    public int maxProfit3II(int[] prices) {
        if (prices.length < 4) {
            int res = 0;
            for (int i = 1; i < prices.length; ++i) {
                if (prices[i] > prices[i-1]) {
                    res += prices[i] - prices[i-1];
                }
            }
            return res;
        }
        int[][] dp = new int[3][prices.length];
        for (int i = 1; i <= 2; ++i) {
            for (int j = 1; j < prices.length; ++j) {
                int max = prices[j] - prices[0];
                for (int k = 1; k < j; ++k) {
                    max = Math.max(max, dp[i-1][k-1] + prices[j] - prices[k]);
                }
                dp[i][j] = Math.max(max, dp[i][j-1]);
            }
        }
        return dp[2][prices.length-1];
    }

    public int maxProfit(int[] prices) {
        if (prices.length < 4) {
            int res = 0;
            for (int i = 1; i < prices.length; ++i) {
                if (prices[i] > prices[i-1]) {
                    res += prices[i] - prices[i-1];
                }
            }
            return res;
        }
        int[][] dp = new int[3][prices.length];
        for (int i = 1; i <= 2; ++i) {
            int max = -prices[0];
            for (int j = 1; j < prices.length; ++j) {
                dp[i][j] = Math.max(max + prices[j], dp[i][j-1]);
                max = Math.max(max, dp[i-1][j-1] - prices[j]);
            }
        }
        return dp[2][prices.length-1];
    }

    /**
     * 188. Best Time to Buy and Sell Stock IV
     * Hard
     *
     * Say you have an array for which the i-th element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete at most k transactions.
     *
     * Note:
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
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

    /**
     * 410. Split Array Largest Sum
     */
    public int splitArray(int[] nums, int m) {
        if (nums.length == 0 || nums.length < m) {
            return 0;
        }
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            prefixSum[i] = prefixSum[i-1] + nums[i];
        }
        return dfs(new Integer[nums.length][m+1], nums.length - 1, m, prefixSum);
    }

    private int dfs(Integer[][] dp, int lastIndex, int m, int[] sum) {
        if (lastIndex == 0) {
            return sum[0];
        }
        if (m == 1) {
            return sum[lastIndex];
        }
        if (dp[lastIndex][m] != null) {
            return dp[lastIndex][m];
        }
        int ans = Integer.MAX_VALUE;
        int nextM = m - 1;
        for (int splitPoint = lastIndex - 1; splitPoint+1 >= nextM; --splitPoint) {
            int maxSubSum = Math.max(
                    dfs(dp, splitPoint, nextM, sum),
                    sum[lastIndex] - sum[splitPoint]);
            if (maxSubSum < ans) {
                ans = maxSubSum;
            }
        }
        dp[lastIndex][m] = ans;
        return ans;
    }

    public int splitArrayWay2(int[] nums, int m) {
        if (nums.length == 0 || nums.length < m) {
            return 0;
        }

        int[] prefixSum = new int[nums.length];
        int[][] dp = new int[nums.length][m+1];
        prefixSum[0] = nums[0];
        dp[0][1] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            prefixSum[i] = prefixSum[i-1] + nums[i];
            dp[i][1] = prefixSum[i];
        }
        for (int subNum = 2; subNum <= m; ++subNum) {
            for (int lastIndex = 0; lastIndex < nums.length; ++lastIndex) {
                int res = Integer.MAX_VALUE;
                for (int splitPoint = lastIndex - 1; splitPoint+1 >= subNum-1; --splitPoint) {
                    int blockMax = Math.max(
                            dp[splitPoint][subNum-1],
                            prefixSum[lastIndex] - prefixSum[splitPoint]);
                    if (res > blockMax) {
                        res = blockMax;
                    }
                }
                dp[lastIndex][subNum] = res;
            }
        }
        return dp[nums.length-1][m];
    }

    /**
     * 329. Longest Increasing Path in a Matrix
     */
    private static class Solution {
        private static int[] deltaX = {0, 0, 1, -1};
        private static int[] deltaY = {1, -1, 0, 0};
        private static int DERECTION_NUM = 4;

        public int longestIncreasingPath(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            int maxPath = 1;
            Integer[][] dp = new Integer[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; ++i) {
                for (int j = 0; j < matrix[0].length; ++j) {
                    int path = dfs(i, j, dp, matrix);
                    if (path > maxPath) {
                        maxPath = path;
                    }
                }
            }
            return maxPath;
        }

        private int dfs(int x, int y, Integer[][] dp, int[][] matrix) {
            if (dp[x][y] != null) {
                return dp[x][y];
            }
            int res = 1;
            for (int i = 0; i < 4; ++i) {
                int newX = x + deltaX[i];
                int newY = y + deltaY[i];
                if (needContinue(matrix[x][y], newX, newY, matrix)) {
                    int path = dfs(newX, newY, dp, matrix);
                    if (res < path + 1) {
                        res = path + 1;
                    }
                }
            }
            dp[x][y] = res;
            return res;
        }

        private boolean needContinue(int preNum, int x, int y, int[][] matrix) {
            return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length
                    && preNum < matrix[x][y];
        }
    }


    /**
     * 1713. Minimum Operations to Make a Subsequence
     * https://leetcode.com/problems/minimum-operations-to-make-a-subsequence/
     */
    public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < target.length; ++i) {
            indexMap.put(target[i], i);
        }
        List<Integer> arrList = new ArrayList<>();
        for (int num : arr) {
            Integer index = indexMap.get(num);
            if (index != null) {
                arrList.add(index);
            }
        }

        List<Integer> lis = new ArrayList<>();
        for (int num : arrList) {
            int left = 0, right = lis.size();
            while (left < right) {
                int mid = left + (right - left) / 2;
                int midNum = lis.get(mid);
                if (midNum < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (right == lis.size()) {
                lis.add(num);
            } else {
                lis.set(right, num);
            }
        }
        return target.length - lis.size();
    }
}
