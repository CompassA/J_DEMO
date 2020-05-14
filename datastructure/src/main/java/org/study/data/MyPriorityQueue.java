package org.study.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fanqie
 * @date 2020/5/14
 */
public class MyPriorityQueue<E extends Comparable<E>> {

    private final MaxHeap<E> innerMaxHeap;

    public MyPriorityQueue() {
        innerMaxHeap = new MaxHeap<>();
    }

    public MyPriorityQueue(final List<E> list) {
        innerMaxHeap = new MaxHeap<>(list);
    }

    public void offer(final E elem) {
        innerMaxHeap.add(elem);
    }

    public E poll() {
        return innerMaxHeap.popMax();
    }

    public boolean isEmpty() {
        return innerMaxHeap.size() == 0;
    }

    private static class MaxHeap<E extends Comparable<E>> {

        private final List<E> list;

        public MaxHeap() {
            list = new ArrayList<>();
        }

        public MaxHeap(final List<E> list) {
            this.list = new ArrayList<>(list);
            this.heapfy(this.list);
        }

        public void add(final E elem) {
            list.add(elem);
            siftUp(list.size() - 1);
        }

        public E popMax() {
            if (list.isEmpty()) {
                return null;
            }
            final E maxElem = list.get(0);
            final int endIndex = list.size() - 1;
            swap(0, endIndex);
            list.remove(endIndex);
            siftDown(0);
            return maxElem;
        }

        private void siftDown(int index) {
            while (index < list.size()) {
                final int maxIndex = getMaxIndex(index);
                if (maxIndex == index) {
                    return;
                }
                swap(index, maxIndex);
                index = maxIndex;
            }
        }

        private void siftUp(int index) {
            while (index > 0) {
                final int parentIndex = getParentIndex(index);
                final E parent = list.get(parentIndex);
                final E curElem = list.get(index);
                if (parent.compareTo(curElem) >= 0) {
                    return;
                }
                swap(parentIndex, index);
                index = parentIndex;
            }
        }

        private void heapfy(final List<E> list) {
            for (int i = getParentIndex(list.size() - 1); i >= 0; --i) {
                siftDown(i);
            }
        }

        private int getMaxIndex(final int index) {
            int maxIndex = index;
            E maxElem = list.get(index);
            final int leftIndex = getLeftChildIndex(index);
            if (leftIndex >= list.size()) {
                return maxIndex;
            }
            if (list.get(leftIndex).compareTo(maxElem) > 0) {
                maxIndex = leftIndex;
                maxElem = list.get(leftIndex);
            }

            final int rightIndex = getRightChildIndex(index);
            if (rightIndex >= list.size()) {
                return maxIndex;
            }
            if (list.get(rightIndex).compareTo(maxElem) > 0) {
                maxIndex = rightIndex;
            }
            return maxIndex;
        }

        private int getParentIndex(final int index) {
            if (index <= 0) {
                throw new RuntimeException("illegal index");
            }
            return (index - 1) / 2;
        }

        private int getLeftChildIndex(final int index) {
            return index * 2 + 1;
        }

        private int getRightChildIndex(final int index) {
            return index * 2 + 2;
        }

        private void swap(final int a, final int b) {
            final E tmp = list.get(a);
            list.set(a, list.get(b));
            list.set(b, tmp);
        }

        public int size() { return list.size(); }
    }
}
