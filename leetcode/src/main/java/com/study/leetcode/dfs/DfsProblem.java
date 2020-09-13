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
     * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
     * find all unique combinations in candidates where the candidate numbers sums to target.
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
     * Given a collection of distinct integers, return all possible permutations.
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

    /**
     * 87. Scramble String
     * Hard
     *
     * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
     *
     * Below is one possible representation of s1 = "great":
     *
     *     great
     *    /    \
     *   gr    eat
     *  / \    /  \
     * g   r  e   at
     *            / \
     *           a   t
     * To scramble the string, we may choose any non-leaf node and swap its two children.
     *
     * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
     *
     *     rgeat
     *    /    \
     *   rg    eat
     *  / \    /  \
     * r   g  e   at
     *            / \
     *           a   t
     * We say that "rgeat" is a scrambled string of "great".
     *
     * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
     *
     *     rgtae
     *    /    \
     *   rg    tae
     *  / \    /  \
     * r   g  ta  e
     *        / \
     *       t   a
     * We say that "rgtae" is a scrambled string of "great".
     *
     * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
     *
     * Example 1:
     *
     * Input: s1 = "great", s2 = "rgeat"
     * Output: true
     * Example 2:
     *
     * Input: s1 = "abcde", s2 = "caebd"
     * Output: false
     */
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int len = s1.length();
        if (s1.equals(s2)) {
            return true;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < len; ++i) {
            cnt[s1.charAt(i)-'a']++;
            cnt[s2.charAt(i)-'a']--;
        }
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] != 0) {
                return false;
            }
        }
        for (int i = 1; i < len; ++i) {
            String s1Left = s1.substring(0, i);
            String s1Right = s1.substring(i);
            if (isScramble(s1Left, s2.substring(0, i)) && isScramble(s1Right, s2.substring(i))) {
                return true;
            }
            if (isScramble(s1Left, s2.substring(len-i)) && isScramble(s1Right, s2.substring(0, len-i))) {
                return true;
            }
        }
        return false;
    }
}
