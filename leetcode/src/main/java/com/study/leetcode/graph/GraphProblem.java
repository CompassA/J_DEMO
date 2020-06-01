package com.study.leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    /**
     * 133. Clone Graph
     * Medium
     *
     * 1594
     *
     * 1316
     *
     * Add to List
     *
     * Share
     * Given a reference of a node in a connected undirected graph.
     *
     * Return a deep copy (clone) of the graph.
     *
     * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
     *
     * class Node {
     *     public int val;
     *     public List<Node> neighbors;
     * }
     *
     *
     * Test case format:
     *
     * For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, the first node with val = 1, the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.
     *
     * Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
     *
     * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
     * Output: [[2,4],[1,3],[2,4],[1,3]]
     * Explanation: There are 4 nodes in the graph.
     * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
     * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
     * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
     * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
     * Example 2:
     *
     *
     * Input: adjList = [[]]
     * Output: [[]]
     * Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
     * Example 3:
     *
     * Input: adjList = []
     * Output: []
     * Explanation: This an empty graph, it does not have any nodes.
     * Example 4:
     *
     *
     * Input: adjList = [[2],[1]]
     * Output: [[2],[1]]
     *
     *
     * Constraints:
     *
     * 1 <= Node.val <= 100
     * Node.val is unique for each node.
     * Number of Nodes will not exceed 100.
     * There is no repeated edges and no self-loops in the graph.
     * The Graph is connected and all nodes can be visited starting from the given node.
     */
    private Map<Integer, Node> clonedNodeMap = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Node clonedNode = clonedNodeMap.get(node.val);
        if (clonedNode != null) {
            return clonedNode;
        }
        clonedNode = new Node(node.val);
        clonedNodeMap.put(node.val, clonedNode);
        for (Node neighbor : node.neighbors) {
            clonedNode.neighbors.add(cloneGraph(neighbor));
        }
        return clonedNode;
    }


    private static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() { val = 0; neighbors = new ArrayList<Node>(); }
        public Node(int _val) { val = _val; neighbors = new ArrayList<Node>(); }
        public Node(int _val, ArrayList<Node> _neighbors) { val = _val; neighbors = _neighbors; }
    }
}
