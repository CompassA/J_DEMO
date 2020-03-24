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
 * 1003 Emergency (25分)
 * As an emergency rescue team leader of a city, you are given a special map of your country. The map shows several scattered cities connected by some roads. Amount of rescue teams in each city and the length of each road between any pair of cities are marked on the map. When there is an emergency call to you from some other city, your job is to lead your men to the place as quickly as possible, and at the mean time, call up as many hands on the way as possible.
 *
 * Input Specification:
 * Each input file contains one test case. For each test case, the first line contains 4 positive integers: N (≤500) - the number of cities (and the cities are numbered from 0 to N−1), M - the number of roads, C
 * ​1
 * ​​  and C
 * ​2
 * ​​  - the cities that you are currently in and that you must save, respectively. The next line contains N integers, where the i-th integer is the number of rescue teams in the i-th city. Then M lines follow, each describes a road with three integers c
 * ​1
 * ​​ , c
 * ​2
 * ​​  and L, which are the pair of cities connected by a road and the length of that road, respectively. It is guaranteed that there exists at least one path from C
 * ​1
 * ​​  to C
 * ​2
 * ​​ .
 *
 * Output Specification:
 * For each test case, print in one line two numbers: the number of different shortest paths between C
 * ​1
 * ​​  and C
 * ​2
 * ​​ , and the maximum amount of rescue teams you can possibly gather. All the numbers in a line must be separated by exactly one space, and there is no extra space allowed at the end of a line.
 *
 * Sample Input:
 * 5 6 0 2
 * 1 2 1 5 3
 * 0 1 1
 * 0 2 2
 * 0 3 1
 * 1 2 1
 * 2 4 1
 * 3 4 1
 *
 *
 *
 * Sample Output:
 * 2 4
 * @author fanqie
 * @date 2020/3/21
 */
public class Emergency {

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
