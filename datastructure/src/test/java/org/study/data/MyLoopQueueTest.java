package org.study.data;

import org.junit.Test;

/**
 * @author fanqie
 * @date 2020/3/26
 */
public class MyLoopQueueTest {

    @Test
    public void queue() {
        MyLoopQueue<Integer> queue = new MyLoopQueue<>();

        for (int i = 0; i < 100; ++i) {
            queue.offer(i);
            System.out.println(queue);
        }

        for (int i = 0; i < 100; ++i) {
            queue.poll();
            System.out.println(queue);
        }
    }

}
