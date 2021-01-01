package com.study.leetcode.trie;

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
}
