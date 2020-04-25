package com.study.me;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author fanqie
 * @date 2020/4/22
 */
public class SortFileNum {
    public static void main(String[] args) throws IOException {
        final File file = new File("G:\\J_DEMO\\IO\\num.txt");
        final List<Integer> nums = new ArrayList<>();

        try (final BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     new FileInputStream(file)))) {
            for (int i = 0; i < 10; ++i) {
                final StringTokenizer tokens = new StringTokenizer(reader.readLine());
                while (tokens.hasMoreTokens()) {
                    nums.add(Integer.parseInt(tokens.nextToken()));
                }
            }
        }

        nums.sort(Comparator.comparing(Integer::intValue));

        try (final BufferedWriter writer =
                     new BufferedWriter(
                             new OutputStreamWriter(
                                     new FileOutputStream(file)))) {
            for (int i = 0; i < nums.size(); ++i) {
                writer.write(String.format("%d%c", nums.get(i), (i+1) % 20 == 0 ? '\n': ' '));
            }
        }
    }
}
