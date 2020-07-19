package com.study.leetcode.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author fanqie
 * @date 2020/7/8
 */
public class TreeHard {

    /**
     * 297. Serialize and Deserialize Binary Tree
     */
    public class Codec {
        private static final String NULL_NODE = "N";
        private static final String DELIMITER = ";";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder builder = new StringBuilder();
            serializeHelper(root, builder);
            return builder.toString();
        }

        private void serializeHelper(TreeNode root, StringBuilder builder) {
            if (root == null) {
                builder.append(String.format("%s%s", NULL_NODE, DELIMITER));
                return;
            }
            builder.append(String.format("%d%s", root.val, DELIMITER));
            serializeHelper(root.left, builder);
            serializeHelper(root.right, builder);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            return deserialize(new LinkedList<>(Arrays.asList(data.split(DELIMITER))));
        }

        private TreeNode deserialize(Queue<String> tokens) {
            String curToken = tokens.poll();
            if (NULL_NODE.equals(curToken)) {
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(curToken));
            node.left = deserialize(tokens);
            node.right = deserialize(tokens);
            return node;
        }
    }


    /**
     * 1505. Minimum Possible Integer After at Most K Adjacent Swaps On Digits
     * Hard
     *
     * Given a string num representing the digits of a very large integer and an integer k.
     *
     * You are allowed to swap any two adjacent digits of the integer at most k times.
     *
     * Return the minimum integer you can obtain also as a string.
     */
    public String minInteger(String num, int k) {
        Queue<Integer>[] numIndex = new Queue[10];
        for (int i = 0; i < numIndex.length; ++i) {
            numIndex[i] = new LinkedList<>();
        }
        for (int i = 0; i < num.length(); ++i) {
            numIndex[num.charAt(i)-'0'].offer(i);
        }
        FenwickTree tree = new FenwickTree(num.length());
        boolean[] moved = new boolean[num.length()];
        StringBuilder res = new StringBuilder();
        while (k > 0 && res.length() < num.length()) {
            for (int i = 0; i < 10; ++i) {
                if (numIndex[i].isEmpty()) {
                    continue;
                }
                int frontIndex = numIndex[i].peek();
                int cost = frontIndex - tree.query(frontIndex - 1);
                if (cost > k) {
                    continue;
                }
                k -= cost;
                res.append(i);
                tree.update(frontIndex, 1);
                moved[frontIndex] = true;
                numIndex[i].poll();
                break;
            }
        }
        for (int i = 0; i < moved.length; ++i) {
            if (!moved[i]) {
                res.append(num.charAt(i));
            }
        }
        return res.toString();
    }
    private static class FenwickTree {
        private final int[] nums;
        public FenwickTree(int n) {
            this.nums = new int[n + 1];
        }

        public int query(int index) {
            index++;
            int res = 0;
            while (index > 0) {
                res += nums[index];
                index -= (index & -index);
            }
            return res;
        }

        public void update(int index, int val) {
            index++;
            while (index < nums.length) {
                nums[index] += val;
                index += (index & -index);
            }
        }
    }
}
