package org.study.data;

/**
 * @author fanqie
 * @date 2020/5/2
 */
public class MyLinkedQueue<E> {

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(final E e) {
        final Node<E> node = new Node<>(e);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
        ++size;
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        final E retVal = this.head.val;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        --size;
        return retVal;
    }

    public E front() {
        return this.head == null ? null : this.head.val;
    }

    private static class Node<E> {
        public E val;
        public Node<E> next;

        public Node(final E val) {
            this.val = val;
        }
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (Node<E> node = head; node != null; node = node.next) {
            stringBuilder.append(node.val.toString());
            if (node != tail) {
                stringBuilder.append("->");
            }
        }
        return stringBuilder.toString();
    }
}
