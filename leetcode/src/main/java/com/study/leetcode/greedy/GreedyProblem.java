package com.study.leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author fanqie
 * @date 2020/5/31
 */
public class GreedyProblem {

    //======================Hard==========================

    /**
     * 135. Candy
     * There are N children standing in a line. Each child is assigned a rating value.
     * You are giving candies to these children subjected to the following requirements:
     * Each child must have at least one candy.
     * Children with a higher rating get more candies than their neighbors.
     * What is the minimum candies you must give?
     */
    public int candy(int[] ratings) {
        int[] record = new int[ratings.length];
        Arrays.fill(record, 1);
        int res = ratings.length;
        for (int i = 1; i < ratings.length; ++i) {
            if (ratings[i] > ratings[i-1] && record[i] <= record[i-1]) {
                res += (record[i-1] - record[i] + 1);
                record[i] = record[i-1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i+1] && record[i] <= record[i+1]) {
                res += (record[i+1] - record[i] + 1);
                record[i] = record[i+1] + 1;
            }
        }
        return res;
    }

    //======================Medium========================
    /**
     * 1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts
     * Given a rectangular cake with height h and width w, and two arrays of integers horizontalCuts and verticalCuts
     * where horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly,
     * verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
     *
     * Return the maximum area of a piece of cake after you cut at each horizontal
     * and vertical position provided in the arrays horizontalCuts and verticalCuts.
     * Since the answer can be a huge number, return this modulo 10^9 + 7.
     * Example 1:
     *
     *
     *
     * Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
     * Output: 4
     * Explanation: The figure above represents the given rectangular cake.
     * Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.
     * Example 2:
     *
     *
     *
     * Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
     * Output: 6
     * Explanation: The figure above represents the given rectangular cake.
     * Red lines are the horizontal and vertical cuts. After you cut the cake,
     * the green and yellow pieces of cake have the maximum area.
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

    /**
     * 435. Non-overlapping Intervals
     * Given a collection of intervals,
     * find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length < 1) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparing(interval -> interval[1]));
        int end = intervals[0][1];
        int res = 0;
        for (int i = 1; i < intervals.length; ++i) {
            int[] curInterval = intervals[i];
            if (curInterval[0] < end) {
                ++res;
            } else {
                end = curInterval[1];
            }
        }
        return res;
    }

    /**
     * 452. Minimum Number of Arrows to Burst Balloons
     * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, Comparator.comparing(point -> point[1]));
        int end = points[0][1];
        int res = 1;
        for (int i = 1; i < points.length; ++i) {
            int[] curPoint = points[i];
            if (curPoint[0] > end) {
                ++res;
                end = curPoint[1];
            }
        }
        return res;
    }

    /**
     * 406. Queue Reconstruction by Height
     * https://leetcode.com/problems/queue-reconstruction-by-height/
     */
    public int[][] reconstructQueue(int[][] people) {
        if (people.length == 0) {
            return people;
        }
        // 高的人排在矮的人前面
        // 身高相同的 按p[1]的值从小到大排列
        Arrays.sort(people, (pA, pB) -> {
            if (pA[0] != pB[0]) {
                return pB[0] - pA[0];
            }
            return pA[1] - pB[1];
        });

        for (int i = 1; i < people.length; ++i) {
            int[] curPeople = people[i];
            int frontNum = i;
            if (curPeople[1] < frontNum) {
                int targetIndex = i - (frontNum - curPeople[1]);
                for (int j = i - 1; j >= targetIndex; --j) {
                    people[j+1] = people[j];
                }
                people[targetIndex] = curPeople;
            }
        }
        return people;
    }

    //========================Easy=============================

    /**
     * 455. Assign Cookies
     */
    public int findContentChildren(int[] children, int[] cookies) {
        Arrays.sort(children);
        Arrays.sort(cookies);
        int res = 0;
        int j = 0;
        for (int child : children) {
            boolean found = false;
            while (j < cookies.length) {
                if (cookies[j] >= child) {
                    ++res; ++j; found = true;
                    break;
                }
                ++j;
            }
            if (!found) {
                break;
            }
        }
        return res;
    }

    /**
     * 605. Can Place Flowers
     * You have a long flowerbed in which some of the plots are planted, and some are not.
     * However, flowers cannot be planted in adjacent plots.
     *
     * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n,
     * return if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int curIndex = 0;
        for (int i = 0; i < n; ++i) {
            boolean found = false;
            while (curIndex < flowerbed.length) {
                if (flowerbed[curIndex] == 0 && (curIndex == 0 || flowerbed[curIndex-1] == 0) &&
                        (curIndex == flowerbed.length - 1 || flowerbed[curIndex+1] == 0)) {
                    found = true;
                    flowerbed[curIndex] = 1;
                    ++curIndex;
                    break;
                }
                ++curIndex;
            }
            if (!found) { return false; }
        }
        return true;
    }

    /**
     * 665. Non-decreasing Array
     * https://leetcode.com/problems/non-decreasing-array/
     */
    public boolean checkPossibility(int[] nums) {
        int modifyChance = 1;
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] > nums[i+1]) {
                if (modifyChance == 0) {
                    return false;
                }
                // 如果已达末尾 增大末尾即可
                if (i+1 == nums.length-1) {
                    return true;
                }
                // 如果当前值可变更 把当前值变小
                else if (i == 0 || nums[i-1] <= nums[i+1]) {
                    modifyChance--;
                    nums[i] = nums[i+1];
                }
                // 把当前值后面的值变大
                else {
                    --modifyChance;
                    nums[i+1] = nums[i];
                }
            }
        }
        return true;
    }
}
