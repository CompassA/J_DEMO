package org.study.data;

/**
 * @author fanqie
 * @date 2020/2/27
 */
public class MyList<E> {

    private static final int DEFAULT_SIZE = 24;

    private static final double RATIO = 1.5;

    private E[] elements;

    private int size;

    public MyList() {
        this(DEFAULT_SIZE);
    }

    public MyList(final int capacity) {
        elements = (E[]) new Object[capacity];
        size = 0;
    }

    public E get(final int index) {
        rangeCheck(index);
        return elements[index];
    }

    public void add(final E element) {
        ensureCapacity(size + 1);
        elements[size++] = element;
    }

    public E deleteAt(final int index) {
        rangeCheck(index);
        final E deletedElem = elements[index];
        for (int i = index + 1; i < size; ++i) {
            elements[i - 1] = elements[i];
        }
        --size;
        elements[size] = null;
        shrink();
        return deletedElem;
    }

    public void insert(final int index, final E element) {
        rangeCheck(index);
        ensureCapacity(size + 1);
        for (int i = size - 1; i >= index; --i) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        ++size;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(
                String.format("size: %d, capacity: %d\n", size, elements.length));
        builder.append("[");
        for (int i = 0; i < size; ++i) {
            builder.append(elements[i].toString());
            if (size - 1 == i) {
                builder.append("]\n");
            } else {
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    private void resize(final int newCapacity) {
        final E[] newArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; ++i) {
            newArray[i] = elements[i];
        }
        elements = newArray;
    }

    private void shrink() {
        final int newSize = (int) Math.round(elements.length / 1.5);
        final int threshold = elements.length / 3;
        if (size <= threshold && newSize >= DEFAULT_SIZE) {
            resize(newSize);
        }
    }

    private void ensureCapacity(final int targetCapacity) {
        //grow factor = 1.5
        if (elements.length < targetCapacity) {
            resize((int) Math.round(elements.length * RATIO));
        }
    }

    private void rangeCheck(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
