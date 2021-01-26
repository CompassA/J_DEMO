package com.study.leetcode.bfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class LetterCombinationsOfAPhoneNumber {
    /**
     * 17. Letter Combinations of a Phone Number
     * Medium
     *
     * 2854
     *
     * 351
     *
     * Favorite
     *
     * Share
     * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
     *
     * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
     *
     *
     *
     * Example:
     *
     * Input: "23"
     * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     */
    public List<String> letterCombinations(String digits) {
        final String[] mapping = new String[]
                {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        final LinkedList<String> queue = new LinkedList<>();
        queue.offer("");
        int curLength = 0;
        for (int i = 0; i < digits.length(); ++i) {
            final String chars = mapping[Character.getNumericValue(digits.charAt(i))];
            while (queue.peek().length() == curLength) {
                final String peek = queue.peek();
                queue.poll();
                for (int j = 0; j < chars.length(); ++j) {
                    queue.offer(peek + chars.charAt(j));
                }
            }
            ++curLength;
        }
        return curLength == 0 ? new LinkedList<>() : queue;
    }


    /**
     * 934. Shortest Bridge
     * https://leetcode.com/problems/shortest-bridge/
     */
    private static class Solution934 {
        private static final int[] D = {-1, 0, 1, 0, -1};
        private static final int VISITED = 2;

        public int shortestBridge(int[][] A) {
            Queue<Point> queue = new LinkedList<>();
            for (int x = 0; x < A.length; ++x) {
                boolean found = false;
                for (int y = 0; y < A[x].length; ++y) {
                    if (A[x][y] == 1) {
                        dfs(queue, A, new Point(x, y));
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            int level = 0;
            while (!queue.isEmpty()) {
                ++level;
                int size = queue.size();
                for (int i = 0; i < size; ++i) {
                    Point front = queue.poll();
                    if (A[front.x][front.y] == 1) {
                        return level - 1;
                    }
                    if (A[front.x][front.y] == VISITED) {
                        continue;
                    }
                    A[front.x][front.y] = VISITED;
                    for (int j = 0; j < 4; ++j) {
                        int newX = front.x + D[j];
                        int newY = front.y + D[j+1];
                        if (newX >= 0 && newX < A.length && newY >= 0 && newY < A[0].length
                                && A[newX][newY] != VISITED) {
                            if (A[newX][newY] == 1) {
                                return level;
                            }
                            queue.offer(new Point(newX, newY));
                        }
                    }
                }
            }
            return 0;
        }

        private void dfs(Queue<Point> q, int[][] g, Point curPos) {
            if (g[curPos.x][curPos.y] == 0) {
                q.offer(curPos);
                return;
            }
            g[curPos.x][curPos.y] = VISITED;
            for (int i = 0; i < 4; ++i) {
                int newX = curPos.x + D[i];
                int newY = curPos.y + D[i+1];
                if (newX >= 0 && newX < g.length && newY >= 0 && newY < g[0].length
                        && g[newX][newY] != VISITED) {
                    dfs(q, g, new Point(newX, newY));
                }
            }
        }

        private static class Point {
            public int x;
            public int y;
            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }
}
