package com.study.leetcode.pat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author fanqie
 * @date 2020/3/21
 */
public class ABFormat_1001 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int a = Integer.parseInt(tokenizer.nextToken());
        int b = Integer.parseInt(tokenizer.nextToken());
        int sum = a + b;
        boolean isNegative = sum < 0;
        sum = Math.abs(sum);

        int count = 0;
        StringBuilder builder = new StringBuilder();
        do {
            builder.append(sum % 10);
            sum /= 10;
            count = (count + 1) % 3;
            if (count == 0 && sum != 0) {
                builder.append(',');
            }
        } while (sum != 0);
        if (isNegative) {
            builder.append('-');
        }
        for (int i = builder.length() - 1; i >= 0; --i) {
            System.out.printf("%c", builder.charAt(i));
        }
        System.out.println("\n");
    }
}
