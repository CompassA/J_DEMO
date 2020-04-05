package com.study.leetcode.dfs;

/**
 * @author fanqie
 * @date 2020/4/5
 */
public class Sudoku {

    /**
     * 36. Valid Sudoku
     * Medium
     *
     * 1355
     *
     * 424
     *
     * Add to List
     *
     * Share
     * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
     *
     * Each row must contain the digits 1-9 without repetition.
     * Each column must contain the digits 1-9 without repetition.
     * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
     *
     * A partially filled sudoku which is valid.
     *
     * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
     *
     * Example 1:
     *
     * Input:
     * [
     *   ["5","3",".",".","7",".",".",".","."],
     *   ["6",".",".","1","9","5",".",".","."],
     *   [".","9","8",".",".",".",".","6","."],
     *   ["8",".",".",".","6",".",".",".","3"],
     *   ["4",".",".","8",".","3",".",".","1"],
     *   ["7",".",".",".","2",".",".",".","6"],
     *   [".","6",".",".",".",".","2","8","."],
     *   [".",".",".","4","1","9",".",".","5"],
     *   [".",".",".",".","8",".",".","7","9"]
     * ]
     * Output: true
     * Example 2:
     *
     * Input:
     * [
     *   ["8","3",".",".","7",".",".",".","."],
     *   ["6",".",".","1","9","5",".",".","."],
     *   [".","9","8",".",".",".",".","6","."],
     *   ["8",".",".",".","6",".",".",".","3"],
     *   ["4",".",".","8",".","3",".",".","1"],
     *   ["7",".",".",".","2",".",".",".","6"],
     *   [".","6",".",".",".",".","2","8","."],
     *   [".",".",".","4","1","9",".",".","5"],
     *   [".",".",".",".","8",".",".","7","9"]
     * ]
     * Output: false
     * Explanation: Same as Example 1, except with the 5 in the top left corner being
     *     modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
     * Note:
     *
     * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
     * Only the filled cells need to be validated according to the mentioned rules.
     * The given board contain only digits 1-9 and the character '.'.
     * The given board size is always 9x9.
     */
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rowMap = new boolean[9][10];
        boolean[][] colMap = new boolean[9][10];
        boolean[][] regionMap = new boolean[9][10];
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    int regionNo = i / 3 * 3 + j / 3;
                    if (rowMap[i][num] || colMap[j][num] || regionMap[regionNo][num]) {
                        return false;
                    }
                    rowMap[i][num] = colMap[j][num] = regionMap[regionNo][num] = true;
                }
            }
        }
        return true;
    }

    /**
     * 37. Sudoku Solver
     * Hard
     *
     * 1461
     *
     * 83
     *
     * Add to List
     *
     * Share
     * Write a program to solve a Sudoku puzzle by filling the empty cells.
     *
     * A sudoku solution must satisfy all of the following rules:
     *
     * Each of the digits 1-9 must occur exactly once in each row.
     * Each of the digits 1-9 must occur exactly once in each column.
     * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
     * Empty cells are indicated by the character '.'.
     *
     *
     * A sudoku puzzle...
     *
     *
     * ...and its solution numbers marked in red.
     *
     * Note:
     *
     * The given board contain only digits 1-9 and the character '.'.
     * You may assume that the given Sudoku puzzle will have a single unique solution.
     * The given board size is always 9x9.
     */
    private class Solution {

        private boolean[][] rowMap = new boolean[9][10];
        private boolean[][] colMap = new boolean[9][10];
        private boolean[][] regionMap = new boolean[9][10];
        private char[][] board;

        public void solveSudoku(char[][] board) {
            this.board = board;
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    if (board[i][j] != '.') {
                        int num = board[i][j] - '0';
                        rowMap[i][num] = colMap[j][num] = regionMap[i / 3 * 3 + j / 3][num] = true;
                    }
                }
            }
            dfs(0);
        }

        private boolean dfs(int index) {
            if (index == 81) {
                return true;
            }
            int i = index / 9;
            int j = index % 9;
            if (board[i][j] != '.') {
                return dfs(index+1);
            }
            int regionNo = i / 3 * 3 + j / 3;
            for (int num = 1; num < 10; ++num) {
                if (!rowMap[i][num] && !colMap[j][num] && !regionMap[regionNo][num]) {
                    rowMap[i][num] = colMap[j][num] = regionMap[regionNo][num] = true;
                    board[i][j] = (char)('0' + num);
                    if (dfs(index + 1)) {
                        return true;
                    }
                    board[i][j] = '.';
                    rowMap[i][num] = colMap[j][num] = regionMap[regionNo][num] = false;
                }
            }
            return false;
        }
    }
}
