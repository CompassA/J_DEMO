package org.study.data;

/**
 * @author fanqie
 * @date 2020/3/26
 */
public class MyLoopQueue<E> {

    private static final int MIN_SIZE = 10;
    private E[] elements;
    private int front;
    private int tail;
    private int size;

    public MyLoopQueue() {
        this(MIN_SIZE);
    }

    public MyLoopQueue(final int capacity) {
        this.elements = (E[]) new Object[capacity + 1];
        size = front = tail = 0;
    }

    public boolean isEmpty() {
        return front == tail;
    }

    public boolean isFull() {
        return front == (tail + 1) % elements.length;
    }

    public int getCapacity() {
        return elements.length - 1;
    }

    public int getSize() {
        return size;
    }

    public void offer(final E element) {
        //ensure capacity
        if (isFull()) {
            resize(getCapacity() * 2);
        }

        //enqueue
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        ++size;
    }

    public E poll() {
        if (isEmpty()) {
            return null;
        }
        //dequeue
        final E polledOne = elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        --size;

        //resize
        if (size == getCapacity() / 4 && getCapacity() / 2 >= MIN_SIZE) {
            resize(getCapacity() / 2);
        }

        return polledOne;
    }

    private void resize(final int newCapacity) {
        final E[] newArray = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; ++i) {
            newArray[i] = elements[(front + i) % elements.length];
        }
        elements = newArray;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(
                String.format("capacity:%d front [", elements.length - 1));
        for (int i = front; i != tail; i = (i + 1) % elements.length) {
            builder.append(String.format("%s%s",
                    elements[i],
                    (i + 1) % elements.length == tail ? "] " + "tail" : ",")
            );
        }
        return builder.toString();
    }
}
