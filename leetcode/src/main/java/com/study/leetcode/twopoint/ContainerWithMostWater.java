package com.study.leetcode.twopoint;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class ContainerWithMostWater {

    /**
     * 11. Container With Most Water
     *
     * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
     * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
     * which together with x-axis forms a container, such that the container contains the most water.
     *
     * Note: You may not slant the container and n is at least 2.
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        while (left < right) {
            final int h = Math.min(height[left], height[right]);
            final int width = right - left;
            final int area = h * width;
            if (area > res) {
                res = area;
            }

            if (height[left] < height[right]) {
                ++left;
            } else {
                --right;
            }
        }
        return res;
    }
}
