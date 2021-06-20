/*
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.study.me.executor;

/**
 * @author Tomato
 * Created on 2021.06.20
 */
public class ThreadStopDemo {

    public static void main(String[] args) throws InterruptedException {
        trueSample();
        //badSample();
    }

    private static void trueSample() throws InterruptedException {
        Thread loopThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("hello world");
            }
            System.out.println("thread stop");
        });

        loopThread.start();

        Thread.sleep(3000);
        loopThread.interrupt();
    }

    private static void badSample() throws InterruptedException {
        Thread loopThread = new Thread(() -> {
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(++i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //lose Thread.currentThread().interrupt();
                }
            }
        });
        loopThread.start();
        Thread.sleep(3000);
        loopThread.interrupt();
    }
}
