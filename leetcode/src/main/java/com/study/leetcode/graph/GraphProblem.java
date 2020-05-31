package com.study.leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * @author fanqie
 * @date 2020/5/17
 */
public class GraphProblem {
    /**
     * 743. Network Delay Time
     * Medium
     *
     * 1345
     *
     * 213
     *
     * Add to List
     *
     * Share
     * There are N network nodes, labelled 1 to N.
     *
     * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
     *
     * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
     * Output: 2
     *
     *
     * Note:
     *
     * N will be in the range [1, 100].
     * K will be in the range [1, N].
     * The length of times will be in the range [1, 6000].
     * All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
     */
    public int networkDelayTime(int[][] times, int N, int K) {
        Integer[][] g = buildGraph(times, N);
        Queue<GNode> minHeap = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];
        int[] dis = new int[N+1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[K] = 0;
        minHeap.offer(new GNode(K, 0));
        while (!minHeap.isEmpty()) {
            GNode curMin = minHeap.poll();
            if (visited[curMin.no]) {
                continue;
            }
            visited[curMin.no] = true;
            for (int i = 1; i <= N; ++i) {
                if (g[curMin.no][i] != null && !visited[i]  && curMin.dis + g[curMin.no][i] < dis[i]) {
                    dis[i] = curMin.dis + g[curMin.no][i];
                    minHeap.offer(new GNode(i, dis[i]));
                }
            }
        }
        int max = 0;
        for (int i = 1; i <= N; ++i) {
            if (dis[i] == Integer.MAX_VALUE) {
                return -1;
            }
            if (dis[i] > max) {
                max = dis[i];
            }
        }
        return max;
    }

    private Integer[][] buildGraph(int[][] times, int N) {
        Integer[][] g = new Integer[N + 1][N + 1];
        for (int[] time : times) {
            g[time[0]][time[1]] = time[2];
        }
        return g;
    }

    private static class GNode implements Comparable<GNode> {
        int no;
        int dis;
        public GNode(int _no, int _dis) {
            no = _no;
            dis = _dis;
        }
        public int compareTo(GNode other) {
            return this.dis - other.dis;
        }
    }

    public static void main(String[] args) {
        new GraphProblem().networkDelayTime(new int[][]{{2,1,1},{2,3,1},{3,4,1}},4, 2);
    }

    /**
     * 1466. Reorder Routes to Make All Paths Lead to the City Zero
     * Medium
     *
     * 62
     *
     * 3
     *
     * Add to List
     *
     * Share
     * There are n cities numbered from 0 to n-1 and n-1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
     *
     * Roads are represented by connections where connections[i] = [a, b] represents a road from city a to b.
     *
     * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
     *
     * Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
     *
     * It's guaranteed that each city can reach the city 0 after reorder.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
     * Output: 3
     * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
     * Example 2:
     *
     *
     *
     * Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
     * Output: 2
     * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
     * Example 3:
     *
     * Input: n = 3, connections = [[1,0],[2,0]]
     * Output: 0
     *
     *
     * Constraints:
     *
     * 2 <= n <= 5 * 10^4
     * connections.length == n-1
     * connections[i].length == 2
     * 0 <= connections[i][0], connections[i][1] <= n-1
     * connections[i][0] != connections[i][1]
     */
    public int minReorderBFS(int n, int[][] connections) {
        List<Integer>[] adjList = new List[n];
        Set<Integer> g = new HashSet<>();
        for (int[] connection : connections) {
            int u = connection[0];
            int v = connection[1];
            if (adjList[u] == null) {
                adjList[u] = new ArrayList<>();
            }
            if (adjList[v] == null) {
                adjList[v] = new ArrayList<>();
            }
            adjList[v].add(u);
            adjList[u].add(v);
            g.add(u * 1_00000 + v);
        }
        boolean[] inQ = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        inQ[0] = true;
        queue.offer(0);
        int res = 0;
        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            if (adjList[curNode] != null) {
                for (int linkedNode : adjList[curNode]) {
                    if (!inQ[linkedNode]) {
                        inQ[linkedNode] = true;
                        queue.offer(linkedNode);
                        if (!g.contains(linkedNode*1_00000+curNode)) {
                            ++res;
                        }
                    }
                }
            }
        }
        return res;
    }

    public int minReorderDFS(int n, int[][] connections) {
        List<Integer>[] adjList = new List[n];
        Set<Integer> g = new HashSet<>();
        for (int[] connection : connections) {
            int u = connection[0];
            int v = connection[1];
            if (adjList[u] == null) {
                adjList[u] = new ArrayList<>();
            }
            if (adjList[v] == null) {
                adjList[v] = new ArrayList<>();
            }
            adjList[v].add(u);
            adjList[u].add(v);
            g.add(u * 1_00000 + v);
        }
        boolean[] visited = new boolean[n];
        return dfs(adjList, g, visited, 0);
    }

    private int dfs(List<Integer>[] adjList, Set<Integer> g, boolean[] visited, int root) {
        if (visited[root] || adjList[root] == null) {
            return 0;
        }
        visited[root] = true;
        int changed = 0;
        for (int linkedNode : adjList[root]) {
            if (!visited[linkedNode]) {
                changed += dfs(adjList, g, visited, linkedNode);
                if (!g.contains(linkedNode * 1_00000 + root)) {
                    changed += 1;
                }
            }
        }
        return changed;
    }

    /**
     * 1462. Course Schedule IV
     * Medium
     *
     * 82
     *
     * 7
     *
     * Add to List
     *
     * Share
     * There are a total of n courses you have to take, labeled from 0 to n-1.
     *
     * Some courses may have direct prerequisites, for example, to take course 0 you have first to take course 1, which is expressed as a pair: [1,0]
     *
     * Given the total number of courses n, a list of direct prerequisite pairs and a list of queries pairs.
     *
     * You should answer for each queries[i] whether the course queries[i][0] is a prerequisite of the course queries[i][1] or not.
     *
     * Return a list of boolean, the answers to the given queries.
     *
     * Please note that if course a is a prerequisite of course b and course b is a prerequisite of course c, then, course a is a prerequisite of course c.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
     * Output: [false,true]
     * Explanation: course 0 is not a prerequisite of course 1 but the opposite is true.
     * Example 2:
     *
     * Input: n = 2, prerequisites = [], queries = [[1,0],[0,1]]
     * Output: [false,false]
     * Explanation: There are no prerequisites and each course is independent.
     * Example 3:
     *
     *
     * Input: n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
     * Output: [true,true]
     * Example 4:
     *
     * Input: n = 3, prerequisites = [[1,0],[2,0]], queries = [[0,1],[2,0]]
     * Output: [false,true]
     * Example 5:
     *
     * Input: n = 5, prerequisites = [[0,1],[1,2],[2,3],[3,4]], queries = [[0,4],[4,0],[1,3],[3,0]]
     * Output: [true,false,true,false]
     *
     *
     * Constraints:
     *
     * 2 <= n <= 100
     * 0 <= prerequisite.length <= (n * (n - 1) / 2)
     * 0 <= prerequisite[i][0], prerequisite[i][1] < n
     * prerequisite[i][0] != prerequisite[i][1]
     * The prerequisites graph has no cycles.
     * The prerequisites graph has no repeated edges.
     * 1 <= queries.length <= 10^4
     * queries[i][0] != queries[i][1]
     */
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        boolean[][] g = new boolean[n][n];
        for (int[] releation : prerequisites) {
            g[releation[0]][releation[1]] = true;
        }
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (g[i][k] && g[k][j]) {
                        g[i][j] = true;
                    }
                }
            }
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            res.add(g[query[0]][query[1]]);
        }
        return res;
    }
}
