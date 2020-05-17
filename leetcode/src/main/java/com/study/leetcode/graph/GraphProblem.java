package com.study.leetcode.graph;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

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
}
