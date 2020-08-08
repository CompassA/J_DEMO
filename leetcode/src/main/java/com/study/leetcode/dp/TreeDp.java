package com.study.leetcode.dp;

import com.study.leetcode.tree.TreeNode;

/**
 * @author fanqie
 * Created on 2020.08.08
 */
public class TreeDp {

    /**
     * 337. House Robber III
     */
    public int rob(TreeNode root) {
        Info info = doRob(root);
        return Math.max(info.rob, info.notRob);
    }

    private Info doRob(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }
        Info leftInfo = doRob(root.left);
        Info rightInfo = doRob(root.right);
        int robNum = root.val + leftInfo.notRob + rightInfo.notRob;
        int notRobNum = Math.max(leftInfo.rob, leftInfo.notRob) +
                Math.max(rightInfo.rob, rightInfo.notRob);
        return new Info(robNum, notRobNum);
    }

    private static class Info {
        public int rob;
        public int notRob;

        public Info(int rob, int notRob) {
            this.rob = rob;
            this.notRob = notRob;
        }
    }
}
