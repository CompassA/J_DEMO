package com.study.leetcode.pat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author fanqie
 * @date 2020/4/4
 */
public class JudgeB1061 {

    private static class Info {
        public int score;
        public int answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(tokens.nextToken());
        int m = Integer.parseInt(tokens.nextToken());

        Info[] answers = new Info[m];
        tokens = new StringTokenizer(reader.readLine());
        for (int i = 0; i < m; ++i) {
            answers[i] = new Info();
            answers[i].score = Integer.parseInt(tokens.nextToken());
        }
        tokens = new StringTokenizer(reader.readLine());
        for (int i = 0; i < m; ++i) {
            answers[i].answer = Integer.parseInt(tokens.nextToken());
        }

        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            tokens = new StringTokenizer(reader.readLine());
            int allSum = 0;
            for (int j = 0; j < m; ++j) {
                int stuAnswer = Integer.parseInt(tokens.nextToken());
                allSum += (stuAnswer == answers[j].answer ? answers[j].score : 0);
            }
            res[i] = allSum;
        }

        for (int score : res) {
            System.out.println(score);
        }
    }
}
