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
 * 1004 Counting Leaves (30分)
 * A family hierarchy is usually presented by a pedigree tree. Your job is to count those family members who have no child.
 *
 * Input Specification:
 * Each input file contains one test case. Each case starts with a line containing 0<N<100, the number of nodes in a tree, and M (<N), the number of non-leaf nodes. Then M lines follow, each in the format:
 *
 * ID K ID[1] ID[2] ... ID[K]
 *
 *
 *
 * where ID is a two-digit number representing a given non-leaf node, K is the number of its children, followed by a sequence of two-digit ID's of its children. For the sake of simplicity, let us fix the root ID to be 01.
 *
 * The input ends with N being 0. That case must NOT be processed.
 *
 * Output Specification:
 * For each test case, you are supposed to count those family members who have no child for every seniority level starting from the root. The numbers must be printed in a line, separated by a space, and there must be no extra space at the end of each line.
 *
 * The sample case represents a tree with only 2 nodes, where 01 is the root and 02 is its only child. Hence on the root 01 level, there is 0 leaf node; and on the next level, there is 1 leaf node. Then we should output 0 1 in a line.
 *
 * Sample Input:
 * 2 1
 * 01 1 02
 *
 *
 *
 * Sample Output:
 * 0 1
 * @author fanqie
 * @date 2020/3/21
 */
public class CountingLeaves {

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
