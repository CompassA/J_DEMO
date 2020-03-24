package com.study.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fanqie
 * @date 2020/3/25
 */
public class Subsets {
    /**
     * 78. Subsets
     * Medium
     *
     * 3019
     *
     * 72
     *
     * Add to List
     *
     * Share
     * Given a set of distinct integers, nums, return all possible subsets (the power set).
     *
     * Note: The solution set must not contain duplicate subsets.
     *
     * Example:
     *
     * Input: nums = [1,2,3]
     * Output:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     */
    List<List<Integer>> res = new ArrayList<>();
    int[] nums;

    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        dfs(0, new ArrayList<>());
        return res;
    }

    void dfs(int index, List<Integer> res) {
        if (index == nums.length) {
            this.res.add(new ArrayList<Integer>(res));
            return;
        }
        res.add(nums[index]);
        dfs(index+1, res);
        res.remove(res.size() - 1);

        dfs(index+1, res);
    }
}
