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
}
