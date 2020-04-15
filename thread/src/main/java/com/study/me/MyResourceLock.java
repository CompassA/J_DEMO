package com.study.me;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author fanqie
 * @date 2020/4/15
 */
public class MyResourceLock {

    private static final int RESOURCE_UNIT = 1;

    private final Sync sync;

    public MyResourceLock(final int resources) {
        sync = new Sync(resources);
    }

    public void lock() {
        sync.acquireShared(RESOURCE_UNIT);
    }

    public void unlock() {
        sync.releaseShared(RESOURCE_UNIT);
    }

    private static final class Sync extends AbstractQueuedSynchronizer {

        public Sync(final int resources) {
            setState(resources);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            while (true) {
                int state = getState();
                int newState = state - arg;
                if (newState < 0 || compareAndSetState(state, newState)) {
                    return newState;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            while (true) {
                int state = getState();
                int newState = state + arg;
                if (compareAndSetState(state, newState)) {
                    return true;
                }
            }
        }
    }

    public static void main(String[] args) {
        final MyResourceLock resourceLock = new MyResourceLock(3);
        final int threadNum = 30;
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(threadNum);
        final int[] cnt = new int[1];
        for (int i = 0; i < threadNum; ++i) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                resourceLock.lock();
                try {
                    Thread.sleep(2000);
                    System.out.println("Hello World!" + (++cnt[0]));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    resourceLock.unlock();
                    end.countDown();
                }
            }).start();
        }

        countDownLatch.countDown();
        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
