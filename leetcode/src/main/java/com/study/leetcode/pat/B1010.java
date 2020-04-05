package com.study.leetcode.pat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author fanqie
 * @date 2020/4/4
 */
public class B1010 {

    private static class Component {
        int a;
        int n;

        public Component(int a, int n) {
            this.a = a;
            this.n = n;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(reader.readLine());

        List<Component> res = new ArrayList<>();
        while (tokens.hasMoreTokens()) {
            int a = Integer.parseInt(tokens.nextToken());
            int n = Integer.parseInt(tokens.nextToken());
            int nextA = a * n;
            int nextN = n - 1;
            if (nextA != 0) {
                res.add(new Component(nextA, nextN));
            }
        }

        if (res.size() == 0) {
            System.out.print("0 0\n");
        }
        for (int i = 0; i < res.size(); ++i) {
            Component component = res.get(i);
            System.out.printf("%d %d%c", component.a, component.n, i == res.size()-1 ? '\n' : ' ');
        }
    }
}
