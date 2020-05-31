package com.study.leetcode.greedy;

import java.util.Arrays;

/**
 * @author fanqie
 * @date 2020/5/31
 */
public class GreedyProblem {

    /**
     * 1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts
     * Medium
     *
     * 40
     *
     * 35
     *
     * Add to List
     *
     * Share
     * Given a rectangular cake with height h and width w, and two arrays of integers horizontalCuts and verticalCuts where horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
     *
     * Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a huge number, return this modulo 10^9 + 7.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
     * Output: 4
     * Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.
     * Example 2:
     *
     *
     *
     * Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
     * Output: 6
     * Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green and yellow pieces of cake have the maximum area.
     * Example 3:
     *
     * Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
     * Output: 9
     *
     *
     * Constraints:
     *
     * 2 <= h, w <= 10^9
     * 1 <= horizontalCuts.length < min(h, 10^5)
     * 1 <= verticalCuts.length < min(w, 10^5)
     * 1 <= horizontalCuts[i] < h
     * 1 <= verticalCuts[i] < w
     * It is guaranteed that all elements in horizontalCuts are distinct.
     * It is guaranteed that all elements in verticalCuts are distinct.
     */
    public int maxArea(int h, int w, int[] hs, int[] vs) {
        int maxHinterval = calcMaxInterval(hs, 0, h);
        int maxVinterval = calcMaxInterval(vs, 0, w);
        int res = 0;
        if (maxVinterval < maxHinterval) {
            for (int i = 0; i < maxVinterval; ++i) {
                res = (res + maxHinterval) % 1000_000_007;
            }
        } else {
            for (int i = 0; i < maxHinterval; ++i) {
                res = (res + maxVinterval) % 1000_000_007;
            }
        }
        return res;
    }

    private int calcMaxInterval(int[] nums, int leftBorder, int rightBorder) {
        Arrays.sort(nums);
        int pre = leftBorder;
        int res = 0;
        for (int num : nums) {
            res = Math.max(res, num - pre);
            pre = num;
        }
        return Math.max(res, rightBorder - pre);
    }
}
