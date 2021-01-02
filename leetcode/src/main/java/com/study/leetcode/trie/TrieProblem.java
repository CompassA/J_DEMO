package com.study.leetcode.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tomato
 * Created on 2021.01.01
 */
public class TrieProblem {
    //==========================Medium=================================================

    /**
     * 421. Maximum XOR of Two Numbers in an Array
     * https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
     */
    public int findMaximumXOR(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Trie root = new Trie();
        for (int num : nums) {
            root.insert(num);
        }
        int res = 0;
        for (int num : nums) {
            res = Math.max(res, root.query(num));
        }
        return res;
    }

    private static class Trie {
        Map<Integer, Trie> children = new HashMap<>();

        public void insert(int num) {
            Trie curNode = this;
            for (int i = 31; i >= 0; --i) {
                int bit = (num >> i) & 1;
                curNode.children.putIfAbsent(bit, new Trie());
                curNode = curNode.children.get(bit);
            }
        }

        public int query(int num) {
            int sum = 0;
            Trie curNode = this;
            for (int i = 31; i >= 0; --i) {
                int bit = (num >> i) & 1;
                Trie oppositePrefix = curNode.children.get(1 - bit);
                if (oppositePrefix != null) {
                    sum |= (1 << i);
                    curNode = oppositePrefix;
                } else {
                    curNode = curNode.children.get(bit);
                }
            }
            return sum;
        }
    }

    //=======================Hard=====================================

    /**
     * 1707. Maximum XOR With an Element From Array
     * https://leetcode.com/problems/maximum-xor-with-an-element-from-array/
     */
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int[][] queryInfo = new int[queries.length][];
        for (int i = 0; i < queries.length; ++i) {
            queryInfo[i] = new int[3];
            queryInfo[i][0] = queries[i][0];
            queryInfo[i][1] = queries[i][1];
            queryInfo[i][2] = i;
        }
        Arrays.sort(nums);
        Arrays.sort(queryInfo, (query1, query2) -> query1[1] - query2[1]);

        int[] res = new int[queries.length];
        Trie root = new Trie();
        int numIndex = 0;
        for (int[] query : queryInfo) {
            while (numIndex < nums.length && nums[numIndex] <= query[1]) {
                root.insert(nums[numIndex]);
                ++numIndex;
            }
            res[query[2]] = root.query(query[0]);
        }
        return res;
    }

    private static class TrieII {
        TrieII[] children = new TrieII[2];

        public void insert(int num) {
            TrieII curNode = this;
            for (int i = 31; i >= 0; --i) {
                int bit = (num >> i) & 1;
                if (curNode.children[bit] == null) {
                    curNode.children[bit] = new TrieII();
                }
                curNode = curNode.children[bit];
            }
        }

        public int query(int num) {
            int res = 0;
            TrieII curNode = this;
            for (int i = 31; i >= 0; --i) {
                if (curNode.children[0] == null && curNode.children[1] == null) {
                    return -1;
                }
                int bit = (num >> i) & 1;
                TrieII next = curNode.children[1 - bit];
                if (next != null) {
                    res |= (1 << i);
                    curNode = next;
                } else {
                    curNode = curNode.children[bit];
                }
            }
            return res;
        }
    }
}
