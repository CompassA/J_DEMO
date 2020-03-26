package com.study.leetcode.pat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author fanqie
 * @date 2020/3/26
 */
public class RotateArray {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(tokens.nextToken());
        int m = Integer.parseInt(tokens.nextToken());

        m = m % n;

        int[] nums = new int[n];
        tokens = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; ++i) {
            nums[i] = Integer.parseInt(tokens.nextToken());
        }

        //way1(nums, m);
        way2(nums, m);
    }

    private static void way1(int[] nums, int m) {
        int[] newArray = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            newArray[(i + m) % nums.length] = nums[i];
        }

        for (int i = 0; i < newArray.length; ++i) {
            System.out.printf("%d%c", newArray[i], i == newArray.length - 1 ? '\n' : ' ');
        }
    }

    private static void way2(int[] nums, int m) {

        reverse(nums, nums.length - m, nums.length - 1);
        reverse(nums, 0, nums.length - m - 1);
        reverse(nums, 0, nums.length - 1);

        for (int i = 0; i < nums.length; ++i) {
            System.out.printf("%d%c", nums[i], i == nums.length - 1 ? '\n' : ' ');
        }
    }

    private static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            ++left;
            --right;
        }
    }
}
