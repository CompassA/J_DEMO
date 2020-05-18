package org.study.data;

import org.junit.Test;

/**
 * @author fanqie
 * @date 2020/5/18
 */
public class MySegmentTreeTest {

    @Test
    public void sumTest() {

        final MySegmentTree<Integer> tree = new MySegmentTree<>(
                new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, Math::max);
        System.out.println(tree.query(1, 2));
        System.out.println(tree.query(0, 9));
    }
}
