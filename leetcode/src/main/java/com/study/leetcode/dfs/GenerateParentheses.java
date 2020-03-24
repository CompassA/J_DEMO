package com.study.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fanqie
 * @date 2020/3/25
 */
public class GenerateParentheses {

    List<String> result = new ArrayList<>();
    int limit;

    public List<String> generateParenthesis(int n) {
        limit = n;
        dfs(0, 0, new StringBuilder());
        return result;
    }

    void dfs(int left, int right, StringBuilder curStr) {
        if (left == right && left == limit) {
            result.add(curStr.toString());
            return;
        }
        if (left < limit) {
            curStr.append("(");
            dfs(left+1, right, curStr);
            curStr.deleteCharAt(curStr.length()-1);
        }
        if (left > right) {
            curStr.append(")");
            dfs(left, right+1, curStr);
            curStr.deleteCharAt(curStr.length()-1);
        }
    }
}
