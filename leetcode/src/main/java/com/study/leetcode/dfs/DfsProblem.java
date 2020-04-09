package com.study.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fanqie
 * @date 2020/4/9
 */
public class DfsProblem {

    /**
     * 39. Combination Sum
     * Medium
     *
     * 3189
     *
     * 101
     *
     * Add to List
     *
     * Share
     * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
     *
     * The same repeated number may be chosen from candidates unlimited number of times.
     *
     * Note:
     *
     * All numbers (including target) will be positive integers.
     * The solution set must not contain duplicate combinations.
     * Example 1:
     *
     * Input: candidates = [2,3,6,7], target = 7,
     * A solution set is:
     * [
     *   [7],
     *   [2,2,3]
     * ]
     * Example 2:
     *
     * Input: candidates = [2,3,5], target = 8,
     * A solution set is:
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     */
    private static class Solution {
        private List<List<Integer>> res = new ArrayList<>();
        private int[] data;
        private int target;

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            if (candidates.length == 0) {
                return res;
            }
            this.data = candidates;
            this.target = target;
            dfs(0, 0, new ArrayList<Integer>());
            return res;
        }

        private void dfs(int index, int weight, List<Integer> path) {
            if (weight == target) {
                res.add(new ArrayList<>(path));
                return;
            }
            if (index == data.length) {
                return;
            }
            if (weight + data[index] <= target) {
                path.add(data[index]);
                dfs(index, weight + data[index], path);
                path.remove(path.size()-1);
            }
            dfs(index+1, weight, path);
        }
    }
}
