package com.study.leetcode.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fanqie
 * @date 2020/2/23
 */
public class Sort {

    /**
     * 347. Top K Frequent Elements
     * https://leetcode.com/problems/top-k-frequent-elements/
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cntMap = new HashMap<>();
        for (int num : nums) {
            cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] bucket = new List[nums.length + 1];
        cntMap.forEach((key, v) -> {
            if (bucket[v] == null) {
                bucket[v] = new ArrayList<>();
            }
            bucket[v].add(key);
        });
        int[] res = new int[k];
        int index = 0;
        for (int j = bucket.length-1; j >= 0; --j) {
            if (bucket[j] != null) {
                for (int num : bucket[j]) {
                    res[index++] = num;
                    if (index == res.length) {
                        return res;
                    }
                }
            }
        }
        return res;
    }
    /**
     * 75. Sort Colors
     *
     * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
     *
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     *
     * Note: You are not suppose to use the library's sort function for this problem.
     */
    public void sortColors(int[] nums) {
        //[0, left] [right, nums.length-1]
        int left = -1, right = nums.length, i = 0;
        while (i < right) {
            switch (nums[i]) {
                case 0:
                    ++left;
                    swap(nums, left, i);
                    ++i;
                    break;
                case 1:
                    ++i;
                    break;
                case 2:
                    --right;
                    swap(nums, right, i);
                    break;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}
