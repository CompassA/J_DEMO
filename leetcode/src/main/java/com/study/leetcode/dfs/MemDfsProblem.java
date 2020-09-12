package com.study.leetcode.dfs;

/**
 * @author fanqie
 * Created on 2020.09.12
 */
public class MemDfsProblem {

    /** 343. Integer Break */
    public int integerBreak(int n) {
        return dfs(n, new Integer[n+1]);
    }
    private int dfs(int num, Integer[] dp) {
        if (dp[num] != null) { return dp[num]; }
        if (num == 1) { return 0; }
        int max = 0;
        for (int i = 1; i < num; ++i) {
            int curMul = Math.max(num - i, dfs(num - i, dp)) * i;
            if (curMul > max) {
                max = curMul;
            }
        }
        dp[num] = max;
        return max;
    }
}
