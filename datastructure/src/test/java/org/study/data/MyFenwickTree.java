package org.study.data;

/**
 * @author fanqie
 * @date 2020/7/15
 */
public class MyFenwickTree {

    private final int[] tree;

    public MyFenwickTree(int n) {
        this.tree = new int[n+1];
    }

    /** i starts from 0 */
    public void update(int i, int delta) {
        ++i;
        while (i < tree.length) {
            tree[i] += delta;
            i += (i & -i);
        }
    }

    /** index starts from 0 */
    public int query(int index) {
        ++index;
        int res = 0;
        while (index > 0) {
            res += tree[index];
            index -= (index & -index);
        }
        return res;
    }
}
