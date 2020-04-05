package com.study.leetcode.pat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * @author fanqie
 * @date 2020/4/5
 */
public class B1065 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokens.nextToken());
        Map<Integer, Integer> couples = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            tokens = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            couples.put(a, b);
            couples.put(b, a);
        }
        tokens = new StringTokenizer(reader.readLine());
        int m = Integer.parseInt(tokens.nextToken());
        tokens = new StringTokenizer(reader.readLine());

        Set<Integer> resSet = new HashSet<>(n);
        for (int i = 0; i < m; ++i) {
            int id = Integer.parseInt(tokens.nextToken());
            Integer coupleId = couples.get(id);
            if (coupleId == null || !resSet.contains(coupleId)) {
                resSet.add(id);
            } else {
                resSet.remove(coupleId);
            }
        }
        Set<Integer> sortedRes = new TreeSet<>(resSet);
        Iterator<Integer> res = sortedRes.iterator();
        System.out.println(sortedRes.size());
        for (int i = 0; i < sortedRes.size(); ++i) {
            System.out.printf("%05d%c", res.next(), i == sortedRes.size()-1 ? '\n': ' ');
        }
    }
}
