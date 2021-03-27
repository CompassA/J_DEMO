package com.study.leetcode.bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class BfsProblem {
    /**
     * 17. Letter Combinations of a Phone Number
     * Medium
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

    /**
     * https://leetcode.com/problems/word-ladder-ii/submissions/
     */
    class Solution126 {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            Set<String> dict = new HashSet<>(wordList);
            if (!dict.contains(endWord)) {
                return Collections.emptyList();
            }
            Map<String, List<String>> next = new HashMap<>();
            next.put(beginWord, new ArrayList<>());
            for (String word : dict) { next.put(word, new ArrayList<>()); }
            Set<String> longSet = new HashSet<>();
            Set<String> shortSet = new HashSet<>();
            boolean reverse = false;
            longSet.add(beginWord);
            shortSet.add(endWord);
            dict.remove(beginWord);
            dict.remove(endWord);
            while (!longSet.isEmpty()) {
                Set<String> nextLevel = new HashSet<>();
                boolean linked = false;
                for (String word : longSet) {
                    char[] chars = word.toCharArray();
                    for (int i = 0; i < chars.length; ++i) {
                        char origin = chars[i];
                        for (char c = 'a'; c <= 'z'; ++c) {
                            if (origin == c) {continue;}
                            chars[i] = c;
                            String newWord = new String(chars);
                            if (shortSet.contains(newWord)) {
                                if (reverse) next.get(newWord).add(word);
                                else next.get(word).add(newWord);
                                linked = true;
                            } else if (dict.contains(newWord)) {
                                if (reverse) next.get(newWord).add(word);
                                else next.get(word).add(newWord);
                                nextLevel.add(newWord);
                            }
                        }
                        chars[i] = origin;
                    }
                }
                if (linked) {break;}
                for (String s : nextLevel) {dict.remove(s);}
                if (nextLevel.size() >= shortSet.size()) {
                    longSet = nextLevel;
                } else {
                    longSet = shortSet;
                    shortSet = nextLevel;
                    reverse = !reverse;
                }
            }
            List<List<String>> res = new ArrayList<>();
            dfs(beginWord, endWord, next, new ArrayList<>(), res);
            return res;
        }

        private void dfs(String cur, String end, Map<String, List<String>> g, List<String> path, List<List<String>> res) {
            path.add(cur);
            if (cur.equals(end)) {
                res.add(new ArrayList<>(path));
                path.remove(path.size()-1);
                return;
            }
            List<String> linkedWords = g.get(cur);
            if (linkedWords == null) {
                path.remove(path.size()-1);
                return;
            }
            for (String word : linkedWords) {
                dfs(word, end, g, path, res);
            }
            path.remove(path.size()-1);
        }
    }

    /** https://leetcode.com/problems/surrounded-regions/  */
    private static class Solution130 {
        public void solve(char[][] board) {
            if (board.length == 0 || board[0].length == 0) {
                return;
            }
            for (int x = 0; x < board.length; ++x) {
                if (board[x][0] == 'O') {
                    dfs(x, 0, board, 'Z');
                }
                if (board[x][board[0].length-1] == 'O') {
                    dfs(x, board[0].length-1, board, 'Z');
                }
            }
            for (int y = 0; y < board[0].length; ++y) {
                if (board[0][y] == 'O') {
                    dfs(0, y, board, 'Z');
                }
                if (board[board.length-1][y] == 'O') {
                    dfs(board.length-1, y, board, 'Z');
                }
            }
            for (int i = 1; i < board.length - 1; ++i) {
                for (int j = 1; j < board[0].length - 1; ++j) {
                    if (board[i][j] == 'O') {
                        dfs(i, j, board, 'X');
                    }
                }
            }
            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[0].length; ++j) {
                    if (board[i][j] == 'Z') {
                        board[i][j] = 'O';
                    }
                }
            }
        }

        private void dfs(int x, int y, char[][] board, char fillChar) {
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == 'X' || board[x][y] == fillChar) {
                return;
            }
            board[x][y] = fillChar;
            dfs(x, y + 1, board, fillChar);
            dfs(x, y - 1, board, fillChar);
            dfs(x - 1, y, board, fillChar);
            dfs(x + 1, y, board, fillChar);
        }
    }
}
