package com.study.leetcode.math;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author fanqie
 * @date 2020/6/7
 */
public class Permutation {

    /**
     * 46. Permutations
     * Medium
     * Given a collection of distinct integers, return all possible permutations.
     *
     * for each pos, fill unused numbers
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs46(0, new HashSet<>(), nums, new ArrayList<>(), res);
        return res;
    }

    private void dfs46(int depth, Set<Integer> preUsedIndex, int[] nums, List<Integer> path, List<List<Integer>> res) {
        if (depth == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (!preUsedIndex.contains(i)) {
                preUsedIndex.add(i);
                path.add(nums[i]);
                dfs46(depth + 1, preUsedIndex, nums, path, res);
                preUsedIndex.remove(i);
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 47. Permutations II
     * Medium
     * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs47(0, new HashSet<>(), nums, new ArrayList<>(), res);
        return res;
    }

    private void dfs47(int depth, Set<Integer> preUsedIndex, int[] nums, List<Integer> path, List<List<Integer>> res) {
        if (depth == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        Set<Integer> posUsed = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (!preUsedIndex.contains(i) && !posUsed.contains(nums[i])) {
                preUsedIndex.add(i);
                path.add(nums[i]);
                posUsed.add(nums[i]);
                dfs47(depth+1, preUsedIndex, nums, path, res);
                preUsedIndex.remove(i);
                path.remove(path.size()-1);
            }
        }
    }

    /**
     * 1467. Probability of a Two Boxes Having The Same Number of Distinct Balls
     * Hard
     */

    private static class SolutionTLE {
        private double total = 0;
        private double valid = 0;

        public double getProbability(int[] balls) {
            List<Integer> ballList = new ArrayList<>();
            for (int i = 0; i < balls.length; ++i) {
                int counts = balls[i];
                for (int j = 0; j < counts; ++j) { ballList.add(i); }
            }
            dfs(0, new HashSet<>(), 0, 0, ballList);
            return valid / total;
        }

        private void dfs(int depth, Set<Integer> used, int left, int right, List<Integer> ballList) {
            if (depth == ballList.size()) {
                total += 1;
                if (cntOne(left) == cntOne(right)) { valid += 1; }
                return;
            }
            for (int i = 0; i < ballList.size(); ++i) {
                if (used.contains(i)) { continue; }
                if (i > 0 && ballList.get(i-1).equals(ballList.get(i)) && !used.contains(i-1)) { continue; }
                int color = ballList.get(i);
                used.add(i);
                if (depth < ballList.size() / 2) { dfs(depth+1, used, (left | (1 << color)), right, ballList); }
                else { dfs(depth+1, used, left, (right | (1 << color)), ballList); }
                used.remove(i);
            }
        }

        private int cntOne(int n) {
            int cnt = 0;
            while (n != 0) { n &= (n - 1); ++cnt; }
            return cnt;
        }
    }

    private static class SolutionPartition {
        private double total = 0;
        private double valid = 0;
        private int[] fact = new int[25];
        private int[] balls;
        private int halfLen = 0;

        public double getProbability(int[] balls) {
            this.balls = balls;
            for (int num : balls) {
                halfLen += num;
            }
            halfLen /= 2;
            fact[0] = 1;
            for (int i = 1; i <= 24; ++i) { fact[i] = fact[i-1] * i; }
            dfs(0, 0, 0, 0, 0, 1.0, 1.0);
            return valid / total;
        }

        private void dfs(int depth, int leftNum, int rightNum, int leftColor, int rightColor,
                         double leftFact, double rightFact) {
            if (leftNum > halfLen || rightNum > halfLen) {
                return;
            }
            if (depth == balls.length) {
                double sum = fact[leftNum] / leftFact * fact[rightNum] / rightFact;
                total += sum;
                if (leftColor == rightColor) {
                    valid += sum;
                }
                return;
            }
            for (int leftBall = 0; leftBall <= balls[depth]; ++leftBall) {
                int rightBall = balls[depth] - leftBall;

                dfs(depth+1, leftNum + leftBall, rightNum + rightBall,
                        leftColor + (leftBall == 0 ? 0 : 1), rightColor + (rightBall == 0 ? 0 : 1),
                        leftFact * fact[leftBall], rightFact * fact[rightBall]);
            }
        }
    }
}
