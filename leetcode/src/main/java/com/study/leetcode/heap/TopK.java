package com.study.leetcode.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author fanqie
 * @date 2020/5/15
 */
public class TopK {
    /**
     * 347. Top K Frequent Elements
     * Medium
     * <p>
     * 2709
     * <p>
     * 198
     * <p>
     * Add to List
     * <p>
     * Share
     * Given a non-empty array of integers, return the k most frequent elements.
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [1,1,1,2,2,3], k = 2
     * Output: [1,2]
     * Example 2:
     * <p>
     * Input: nums = [1], k = 1
     * Output: [1]
     * Note:
     * <p>
     * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
     * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
     * It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
     * You can return the answer in any order.
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        Queue<Integer> minHeap = new PriorityQueue<>(k, (a, b) -> cnt.get(a) - cnt.get(b));
        cnt.forEach((key, val) -> {
            if (minHeap.size() == k) {
                if (cnt.get(minHeap.peek()) < val) {
                    minHeap.poll();
                    minHeap.offer(key);
                }
            } else {
                minHeap.offer(key);
            }
        });
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; --i) {
            res[i] = minHeap.poll();
        }
        return res;
    }

    public int[] topKFrequentWay2(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] bucket = new List[nums.length + 1];
        cnt.forEach((key, val) -> {
            if (bucket[val] == null) {
                bucket[val] = new ArrayList<>();
            }
            bucket[val].add(key);
        });
        int[] res = new int[k];
        int index = 0;
        for (int i = bucket.length - 1; index < res.length; --i) {
            if (bucket[i] != null) {
                for (int num : bucket[i]) {
                    res[index++] = num;
                }
            }
        }
        return res;
    }

    /**
     * 451. Sort Characters By Frequency
     * Medium
     *
     * 1227
     *
     * 98
     *
     * Add to List
     *
     * Share
     * Given a string, sort it in decreasing order based on the frequency of characters.
     *
     * Example 1:
     *
     * Input:
     * "tree"
     *
     * Output:
     * "eert"
     *
     * Explanation:
     * 'e' appears twice while 'r' and 't' both appear once.
     * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
     * Example 2:
     *
     * Input:
     * "cccaaa"
     *
     * Output:
     * "cccaaa"
     *
     * Explanation:
     * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
     * Note that "cacaca" is incorrect, as the same characters must be together.
     * Example 3:
     *
     * Input:
     * "Aabb"
     *
     * Output:
     * "bbAa"
     *
     * Explanation:
     * "bbaA" is also a valid answer, but "Aabb" is incorrect.
     * Note that 'A' and 'a' are treated as two different characters.
     */
    public String frequencySort(String s) {
        Map<Character, Integer> cnt = new HashMap();
        for (int i = 0; i < s.length(); ++i) {
            cnt.put(s.charAt(i), cnt.getOrDefault(s.charAt(i), 0) + 1);
        }
        List<Character>[] bucket = new List[s.length()+1];
        cnt.forEach((k, v) -> {
            if (bucket[v] == null) {
                bucket[v] = new ArrayList<Character>();
            }
            bucket[v].add(k);
        });
        StringBuilder builder = new StringBuilder();
        for (int i = bucket.length - 1; i >= 0; --i) {
            if (bucket[i] != null) {
                for (char c : bucket[i]) {
                    for (int j = 0; j < i; ++j) {
                        builder.append(c);
                    }
                }
            }
            if (builder.length() == s.length()) {
                return builder.toString();
            }
        }
        return "";
    }
}
