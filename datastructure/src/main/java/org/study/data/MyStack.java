package org.study.data;

/**
 * @author fanqie
 * @date 2020/3/7
 */
public class MyStack<E> {

    private final MyList<E> innerArray;

    public MyStack(final int capacity) {
        innerArray = new MyList<>(capacity);
    }

    public MyStack() {
        innerArray = new MyList<>();
    }

    public void push(final E element) {
        innerArray.add(element);
    }

    public E pop() {
        return innerArray.deleteAt(innerArray.size() - 1);
    }

    public E peek() {
        return innerArray.get(innerArray.size() - 1);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("MyStack[");
        for (int i = 0; i < innerArray.size(); ++i) {
            builder.append(String.format("%s%s",
                    innerArray.get(i),
                    i == innerArray.size() - 1 ? "]\n" : "," + " "));
        }
        builder.append(String.format("peek: %s\n", this.peek()));
        return builder.toString();
    }

    public boolean isEmpty() {
        return innerArray.isEmpty();
    }
}
