package com.study.me;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author fanqie
 * @date 2020/4/16
 */
public class MyReadWriteLock {

    private static final class Sync extends AbstractQueuedSynchronizer {

        public Sync() {
            setState(0);
        }

        @Override
        protected boolean tryAcquire(int arg) {
            final Thread currentThread = Thread.currentThread();
            final int state = getState();
            //是否有其他读线程、是否是重入
            if (state != 0) {
                final int readerNums = (state >>> 16);
                if (readerNums > 0 || getExclusiveOwnerThread() != currentThread) {
                    return false;
                } else {
                    setState(state + arg);
                    return true;
                }
            }
            //竞争
            if (compareAndSetState(state, state + arg)) {
                System.out.println("writer acquired, writer = " + (state + arg));
                setExclusiveOwnerThread(currentThread);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            final int state = getState();
            final int nextState = state - arg;
            setState(state - arg);
            if (nextState == 0) {
                System.out.println("writer released, writer=" + (state - arg));
                setExclusiveOwnerThread(null);
                return true;
            }
            return false;
        }

        @Override
        protected int tryAcquireShared(int arg) {
            while (true) {
                final int state = getState();
                final int writerNums = (state & 0x0000FFFF);
                if (writerNums > 0) {
                    return -1;
                }
                int nextState = state + (arg << 16);
                if (compareAndSetState(state, nextState)) {
                    System.out.println("reader acquired, reader = " + (nextState >>> 16));
                    return 1;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            while (true) {
                final int state = getState();
                final int nextState = state - (arg << 16);
                if (compareAndSetState(state, nextState)) {
                    System.out.println("reader released, reader = " + (nextState >>> 16));
                    return true;
                }
            }
        }
    }

    public static void main(String[] args) {
        final int[] num = new int[1];
        final Sync sync = new Sync();
        final Runnable writer = () -> {
            for (int i = 0; i < 10; ++i) {
                sync.acquire(1);
                try {
                    num[0]++;
                } finally {
                    sync.release(1);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        final Runnable reader = () -> {
            while (true) {
                sync.acquireShared(1);
                try {
                    System.out.println("num = " + num[0]);
                } finally {
                    sync.releaseShared(1);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(writer).start();
        for (int i = 0; i < 10; ++i) {
            new Thread(reader).start();
        }

    }
}
