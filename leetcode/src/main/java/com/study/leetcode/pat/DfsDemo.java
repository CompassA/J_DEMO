package com.study.leetcode.pat;

/**
 * @author fanqie
 * Created on 2020.08.13
 */
public class DfsDemo {

    private static final int[] deltaRow = {-1, 1, 0, 0};
    private static final int[] deltaCol = {0, 0, -1, 1};

    public static void main(String[] args) {
        int[][] mockArray1 = {
                {0, 1, 1, 1, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 1, 0},
                {1, 1, 1, 0, 1, 0, 0},
                {1, 1, 1, 1, 0, 0, 0},
        };
        int[][] mockArray2 = {
                {1, 1, 1, 1},
                {0, 0, 0, 1},
                {1, 1, 1, 1},
                {1, 0, 0, 0},
        };
        System.out.println(calcBlockNum(mockArray1));
        System.out.println(calcBlockNum(mockArray2));
    }

    public static int calcBlockNum(int[][] array) {
        int row = array.length;
        int col = array[0].length;
        boolean[] visited = new boolean[row * col];

        int blockNum = 0;
        for (int x = 0; x < row; ++x) {
            for (int y = 0; y < col; ++y) {
                if (!visited[pos(x, y, col)] && array[x][y] == 1) {
                    ++blockNum;
                    dfsChangeVisited(array, x, y, visited);
                }
            }
        }
        return blockNum;
    }

    private static int pos(int x, int y, int col) {
        return x * col + y;
    }

    private static void dfsChangeVisited(
            int[][]array, int x, int y, boolean[] visited) {
        visited[pos(x, y, array[0].length)] = true;
        for (int i = 0; i < 4; ++i) {
            int newX = x + deltaRow[i];
            int newY = y + deltaCol[i];
            if (needDfsContinue(array, newX, newY, visited)) {
                dfsChangeVisited(array, newX, newY, visited);
            }
        }
    }

    private static boolean needDfsContinue(
            int[][] array, int x, int y, boolean[] visited) {
        return x >= 0 && x < array.length &&
                y >= 0 && y < array[0].length &&
                array[x][y] == 1 && !visited[pos(x, y, array[0].length)];
    }
}
