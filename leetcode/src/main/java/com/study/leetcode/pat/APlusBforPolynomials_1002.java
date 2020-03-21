package com.study.leetcode.pat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * @author fanqie
 * @date 2020/3/21
 */
public class APlusBforPolynomials_1002 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Double> res = new TreeMap<>(Comparator.comparing(Integer::intValue).reversed());
        for (int i = 0; i < 2; ++i) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            for (int j = 0; j < n; ++j) {
                int exponents = Integer.parseInt(tokenizer.nextToken());
                double coefficient = Double.parseDouble(tokenizer.nextToken());
                double newRes = res.getOrDefault(exponents, 0.0) + coefficient;
                if (newRes != 0) {
                    res.put(exponents, newRes);
                } else {
                    res.remove(exponents);
                }
            }
        }
        System.out.printf("%d", res.size());
        if (res.size() != 0) {
            res.forEach((k, v) -> System.out.printf(" %d %.1f", k, v));
        }
    }
}
