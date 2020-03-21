package com.study.leetcode.pat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author fanqie
 * @date 2020/3/21
 */
public class CountingLeaves_1004 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        List<List<Integer>> tree = initTree(n);
        for (int i = 0; i < m; ++i) {
            tokenizer = new StringTokenizer(reader.readLine());
            int id = Integer.parseInt(tokenizer.nextToken());
            int childNum = Integer.parseInt(tokenizer.nextToken());
            for (int j = 0; j < childNum; ++j) {
                tree.get(id).add(Integer.parseInt(tokenizer.nextToken()));
            }
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        List<Integer> res = new ArrayList<>(n);
        while (!q.isEmpty()) {
            int levelNum = q.size();
            int cnt = 0;
            for (int i = 0; i < levelNum; ++i) {
                int node = q.poll();
                List<Integer> children = tree.get(node);
                if (children.isEmpty()) {
                    ++cnt;
                } else {
                    q.addAll(children);
                }
            }
            res.add(cnt);
        }
        for (int i = 0; i < res.size(); ++i) {
            System.out.printf("%d%c", res.get(i), i == res.size() - 1 ? '\n' : ' ');
        }
    }

    public static List<List<Integer>> initTree(int n) {
        int size = n + 1;
        List<List<Integer>> tree = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            tree.add(i, new ArrayList<>(0));
        }
        return tree;
    }
}
