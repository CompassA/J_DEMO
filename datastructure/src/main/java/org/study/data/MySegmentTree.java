package org.study.data;

import javax.management.RuntimeErrorException;

/**
 * @author fanqie
 * @date 2020/5/18
 */
public class MySegmentTree<E> {

    private final E[] tree;
    private final E[] elements;
    private final Merger<E> merger;

    public MySegmentTree(final E[] elements, final Merger<E> merger) {
        this.elements = elements;
        this.merger = merger;
        this.tree = (E[]) new Object[4 * elements.length];
        buildTree(0, 0, elements.length - 1);
    }

    public E query(final int left, final int right) {
        if (left > right) {
            throw new RuntimeException("[left, right] illegal");
        }
        return query(0, 0, elements.length - 1, left, right);
    }

    private E query(final int index, final int l, final int r, final int queryL, final int queryR) {
        if (queryL == l && queryR == r) {
            return tree[index];
        }
        final int mid = l + (r - l) / 2;
        final int leftChildIndex = getLeftChildIndex(index);
        final int rightChildIndex = getRightChildIndex(index);
        if (mid < queryL) {
            return query(rightChildIndex, mid + 1, r, queryL, queryR);
        } else if (mid + 1 > queryR) {
            return query(leftChildIndex, l, mid, queryL, queryR);
        }
        final E leftRes = query(leftChildIndex, l, mid, queryL, mid);
        final E rightRes = query(rightChildIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftRes, rightRes);
    }

    private void buildTree(final int index, final int left, final int right) {
        if (left == right) {
            tree[index] = elements[left];
            return;
        }
        final int leftChildIndex = getLeftChildIndex(index);
        final int rightChildIndex = getRightChildIndex(index);
        final int mid = left + (right - left) / 2;
        buildTree(leftChildIndex, left, mid);
        buildTree(rightChildIndex, mid + 1, right);
        tree[index] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }

    private int getLeftChildIndex(final int index) {
        return index * 2 + 1;
    }

    private int getRightChildIndex(final int index) {
        return index * 2 + 2;
    }
}
