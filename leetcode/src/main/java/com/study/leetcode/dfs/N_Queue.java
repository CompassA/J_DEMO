package com.study.leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fanqie
 * @date 2020/6/1
 */
public class N_Queue {

    /**
     * 51. N-Queens
     * Hard
     * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
     *
     *
     *
     * Given an integer n, return all distinct solutions to the n-queens puzzle.
     *
     * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
     * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
     */
    private static class Solution {
        private List<List<String>> res = new ArrayList<>();

        public List<List<String>> solveNQueens(int n) {
            dfs(new int[n+1], new boolean[n+1], n, 1);
            return res;
        }

        private void dfs(int[] board, boolean[] used, int n, int curX) {
            if (curX == n+1) {
                char[][] tmp = new char[n][n];
                for (char[] line : tmp) {
                    Arrays.fill(line, '.');
                }
                for (int x = 1; x <= n; ++x) {
                    tmp[x-1][board[x]-1] = 'Q';
                }
                List<String> curRes = new ArrayList<>();
                for (char[] line: tmp) {
                    curRes.add(new String(line));
                }
                res.add(curRes);
                return;
            }
            for (int y = 1; y <= n; ++y) {
                if (used[y]) {
                    continue;
                }
                boolean canGenerate = true;
                for (int preX = 1; preX < curX; ++preX) {
                    int preY = board[preX];
                    if (Math.abs(preX - curX) == Math.abs(preY - y)) {
                        canGenerate = false;
                        break;
                    }
                }
                if (canGenerate) {
                    board[curX] = y;
                    used[y] = true;
                    dfs(board, used, n, curX + 1);
                    used[y] = false;
                }
            }
        }
    }

    class Solution51 {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> res = new ArrayList<>();
            char[][] g = new char[n][n];
            for (char[] c : g) {
                Arrays.fill(c, '.');
            }
            dfs(res, g, n, 0, new boolean[n], new boolean[2*n-1], new boolean[2*n-1]);
            return res;
        }

        private void dfs(List<List<String>> res, char[][] g, int n, int x,
                         boolean[] yUsed, boolean[] lUsed, boolean[] rUsed) {
            if (x == n) {
                res.add(Arrays.stream(g).map(String::new).collect(Collectors.toList()));
                return;
            }
            for (int y = 0; y < n; ++y) {
                //y = x + b, b = y - x
                int l = n - 1 + y - x;
                //y = - x + b, b = x + y
                int r = x + y;
                if (yUsed[y] || lUsed[l] || rUsed[r]) {
                    continue;
                }
                yUsed[y] = lUsed[l] = rUsed[r] = true;
                g[x][y] = 'Q';
                dfs(res, g, n, x + 1, yUsed, lUsed, rUsed);
                yUsed[y] = lUsed[l] = rUsed[r] = false;
                g[x][y] = '.';
            }
        }
    }

    /**
     * 52. N-Queens II
     * Hard
     *
     * 466
     *
     * 144
     *
     * Add to List
     *
     * Share
     * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
     *
     *
     *
     * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
     *
     * Example:
     *
     * Input: 4
     * Output: 2
     * Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
     */
    public int totalNQueens(int n) {
        return dfs(new int[n+1], new boolean[n+1], n, 1);
    }

    private int dfs(int[] board, boolean[] used, int n, int curX) {
        if (curX == n + 1) {
            return 1;
        }
        int res = 0;
        for (int y = 1; y <= n; ++y) {
            if (used[y]) {
                continue;
            }
            boolean canGenerate = true;
            for (int preX = 1; preX < curX; ++preX) {
                int preY = board[preX];
                if (Math.abs(preX - curX) == Math.abs(preY - y)) {
                    canGenerate = false;
                    break;
                }
            }
            if (canGenerate) {
                board[curX] = y;
                used[y] = true;
                res += dfs(board, used, n, curX+1);
                used[y] = false;
            }
        }
        return res;
    }

    class Solution52 {
        int res = 0;
        int n;

        public int totalNQueens(int n) {
            this.n = n;
            dfs(0, new boolean[n], new boolean[2*n-1], new boolean[2*n-1]);
            return res;
        }

        private void dfs(int x, boolean[] yUsed, boolean[] lUsed, boolean[] rUsed) {
            if (x == n) {
                res++;
                return;
            }
            for (int y = 0; y < n; ++y) {
                int r = x + y;
                int l = y - x + n - 1;
                if (yUsed[y] || lUsed[l] || rUsed[r]) {
                    continue;
                }
                yUsed[y] = lUsed[l] = rUsed[r] = true;
                dfs(x + 1, yUsed, lUsed, rUsed);
                yUsed[y] = lUsed[l] = rUsed[r] = false;
            }
        }
    }
}
