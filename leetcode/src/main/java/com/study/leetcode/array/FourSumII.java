package com.study.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class FourSumII {

    /**
     * 454. 4Sum II
     * Medium
     *
     * 861
     *
     * 62
     *
     * Favorite
     *
     * Share
     * Given four lists A, B, C, D of integer values,
     * compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
     *
     * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500.
     * All integers are in the range of -2^28 to 2^28 - 1 and the result is guaranteed to be at most 2^31 - 1.
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        final Map<Integer, Integer> map = new HashMap<>(0);
        for (final int numA : A) {
            for (final int numB : B) {
                final int sum = numA + numB;
                final int cnt = map.getOrDefault(sum, 0);
                map.put(sum, cnt + 1);
            }
        }
        int res = 0;
        for (final int numC : C) {
            for (final int numD : D) {
                final int target = -(numC + numD);
                res += map.getOrDefault(target, 0);
            }
        }
        return res;
    }

}
