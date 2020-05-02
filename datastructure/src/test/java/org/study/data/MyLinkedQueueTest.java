package org.study.data;

import org.junit.Test;

/**
 * @author fanqie
 * @date 2020/5/2
 */
public class MyLinkedQueueTest {

    @Test
    public void test() throws InterruptedException {
        final MyLinkedQueue<Integer> q = new MyLinkedQueue<>();
        for (int i = 0; i < 16; ++i) {
            q.enqueue(i);
            System.out.println("enqueue:" + q);
            Thread.sleep(1000);
            if (i % 3 == 0) {
                q.dequeue();
                System.out.println("dequeue:" + q);
                Thread.sleep(1000);
            }
        }

        while (!q.isEmpty()) {
            q.dequeue();
            System.out.println("dequeue:" + q);
            Thread.sleep(1000);
        }
    }
}
