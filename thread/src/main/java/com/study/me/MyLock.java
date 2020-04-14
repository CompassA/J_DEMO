package com.study.me;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author fanqie
 * @date 2020/4/14
 */
public class MyLock implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        @Override
        protected boolean tryAcquire(int arg) {
            if ((compareAndSetState(0, 1))) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            setState(0);
            setExclusiveOwnerThread(null);
            return true;
        }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        final MyLock lock = new MyLock();
        final int threadNum = 100000;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        final TestClass testClass = new TestClass(lock, countDownLatch);
        for (int i = 0; i < threadNum; ++i) {
            new Thread(testClass, "test"+i).start();
        }
        countDownLatch.await();
        System.out.println("num = " + testClass.getNum());
    }

    private static class TestClass implements Runnable {

        private final MyLock lock;
        private final CountDownLatch countDownLatch;
        private int num = 0;

        public TestClass(final MyLock lock, final CountDownLatch countDownLatch) {
            this.lock = lock;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                ++num;
            } finally {
                lock.unlock();
                countDownLatch.countDown();
            }
        }

        public int getNum() {
            return num;
        }
    }
}
