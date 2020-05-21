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

    /**
     * 79. Word Search
     * Medium
     *
     * 3239
     *
     * 160
     *
     * Add to List
     *
     * Share
     * Given a 2D board and a word, find if the word exists in the grid.
     *
     * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
     *
     * Example:
     *
     * board =
     * [
     *   ['A','B','C','E'],
     *   ['S','F','C','S'],
     *   ['A','D','E','E']
     * ]
     *
     * Given word = "ABCCED", return true.
     * Given word = "SEE", return true.
     * Given word = "ABCB", return false.
     *
     *
     * Constraints:
     *
     * board and word consists only of lowercase and uppercase English letters.
     * 1 <= board.length <= 200
     * 1 <= board[i].length <= 200
     * 1 <= word.length <= 10^3
     */
    private static class Solution79 {
        private char[][] g;
        private char[] chars;
        private boolean[][] visited;

        public boolean exist(char[][] board, String word) {
            g = board;
            chars = word.toCharArray();
            visited = new boolean[board.length][board[0].length];
            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[0].length; ++j) {
                    if (exist(i, j, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean exist(int row, int col, int index) {
            visited[row][col] = true;
            if (chars[index] != g[row][col]) {
                visited[row][col] = false;
                return false;
            }
            if (index + 1 == chars.length) {
                return true;
            }
            if (row+1 < g.length && !visited[row+1][col] && exist(row+1, col, index+1)) {
                return true;
            }
            if (row - 1 >= 0 && !visited[row-1][col] && exist(row-1, col, index+1)) {
                return true;
            }
            if (col + 1 < g[0].length && !visited[row][col+1] && exist(row, col + 1, index+1)) {
                return true;
            }
            if (col - 1 >= 0 && !visited[row][col-1] && exist(row, col - 1, index+1)) {
                return true;
            }
            visited[row][col] = false;
            return false;
        }
    }

    /**
     * 46. Permutations
     * Medium
     *
     * 3506
     *
     * 101
     *
     * Add to List
     *
     * Share
     * Given a collection of distinct integers, return all possible permutations.
     *
     * Example:
     *
     * Input: [1,2,3]
     * Output:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     */
    class Solution46 {
        private int[] nums;
        private List<List<Integer>> res = new ArrayList<>();
        public List<List<Integer>> permute(int[] nums) {
            this.nums = nums;
            dfs(0, new ArrayList<>());
            return res;
        }

        private void dfs(int index, List<Integer> path) {
            if (index == nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = index; i < nums.length; ++i) {
                int tmp = nums[index];
                nums[index] = nums[i];
                nums[i] = tmp;
                path.add(nums[index]);
                dfs(index+1, path);
                path.remove(path.size()-1);
                tmp = nums[index];
                nums[index] = nums[i];
                nums[i] = tmp;
            }
        }
    }
}
