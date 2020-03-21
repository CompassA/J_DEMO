package com.study.leetcode.pat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author fanqie
 * @date 2020/3/21
 */
public class Emergency_1003 {

    static int paths = 0;
    static int maxRescues = 0;
    static int[] weight;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int C1 = Integer.parseInt(tokenizer.nextToken());
        int C2 = Integer.parseInt(tokenizer.nextToken());
        weight = new int[N];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; ++i) {
            weight[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int[][] map = new int[N][N];
        for (int i = 0; i < M; ++i) {
            tokenizer = new StringTokenizer(reader.readLine());
            int c1 = Integer.parseInt(tokenizer.nextToken());
            int c2 = Integer.parseInt(tokenizer.nextToken());
            map[c2][c1] = map[c1][c2] = Integer.parseInt(tokenizer.nextToken());
        }

        Map<Integer, List<Integer>> preMap = dijkstra(map, N, C1);
        dfs(preMap, new ArrayList<>(), C2, C1);
        System.out.printf("%d %d", paths, maxRescues);
    }

    private static Map<Integer, List<Integer>> dijkstra(int[][] map, int nodeNum, int start) {
        int[] minDis = new int[nodeNum];
        Arrays.fill(minDis, Integer.MAX_VALUE);
        minDis[start] = 0;

        boolean[] visited = new boolean[nodeNum];
        Map<Integer, List<Integer>> preMap = initMap(nodeNum);
        for (int i = 0; i < nodeNum; ++i) {
            //find unvisited min dis
            int min = Integer.MAX_VALUE;
            int node = -1;
            for (int j = 0; j < nodeNum; ++j) {
                if (!visited[j] && minDis[j] < min) {
                    min = minDis[j];
                    node = j;
                }
            }
            if (node == -1) {
                return null;
            }
            visited[node] = true;

            //update min dis
            for (int j = 0; j < nodeNum; ++j) {
                if (!visited[j] && map[node][j] != 0) {
                    int curDis = min + map[node][j];
                    if (curDis < minDis[j]) {
                        minDis[j] = curDis;
                        List<Integer> p = new ArrayList<>();
                        p.add(node);
                        preMap.put(j, p);
                    } else if (curDis == minDis[j]) {
                        preMap.get(j).add(node);
                    }
                }
            }
        }
        return preMap;
    }

    public static void dfs(
            Map<Integer, List<Integer>> preMap, List<Integer> curPath, int curNode, int end) {
        curPath.add(curNode);

        if (curNode == end) {
            ++paths;
            int sum = 0;
            for (Integer i : curPath) {
                sum += weight[i];
            }
            if (sum > maxRescues) {
                maxRescues = sum;
            }
            curPath.remove(curPath.size()-1);
            return;
        }

        List<Integer> pre = preMap.get(curNode);
        if (pre != null) {
            for (Integer i : pre) {
                dfs(preMap, curPath, i, end);
            }
        }
        curPath.remove(curPath.size() - 1);
    }

    private static Map<Integer, List<Integer>> initMap(int n) {
        Map<Integer, List<Integer>> res = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            res.put(i, new ArrayList<>());
        }
        return res;
    }
}
