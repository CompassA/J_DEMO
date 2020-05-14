package org.study.data;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author fanqie
 * @date 2020/5/14
 */
public class MyPriorityQueueTest {

    @Test
    public void priorityQueueTest() {
        final MyPriorityQueue<Integer> queue = new MyPriorityQueue<>();
        for (int i = 0; i < 100; ++i) {
            queue.offer(i);
        }

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    @Test
    public void studentSortTest() {
        final MyPriorityQueue<Person> queue = new MyPriorityQueue<>();
        final Random random = new Random();
        for (int i = 0; i < 100; ++i) {
            queue.offer(new Person(UUID.randomUUID().toString(), random.nextInt(100)));
        }

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    @Test
    public void heapfyTest() {
        final List<Person> list = new ArrayList<>();
        final Random random = new Random();
        for (int i = 0; i < 100; ++i) {
            list.add(new Person(UUID.randomUUID().toString(), random.nextInt(100)));
        }

        final MyPriorityQueue<Person> queue = new MyPriorityQueue<>(list);
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    private static class Person implements Comparable<Person> {
        public String name;
        public int age;

        public Person(final String name, final int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(final Person o) {
            return Integer.compare(this.age, o.age);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
