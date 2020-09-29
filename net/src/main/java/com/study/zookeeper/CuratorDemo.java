package com.study.zookeeper;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author tomato
 * Created on 2020.09.28
 */
public class CuratorDemo {

    private static final String IP_PORT = "192.168.3.17:2181";

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                4, 4, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new ThreadFactoryBuilder().setNameFormat("local zk thread pool %d").build(),
                new ThreadPoolExecutor.DiscardPolicy()
        );

        //只监听一次
        threadPoolExecutor.submit(() -> {
            CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                    .connectString(IP_PORT)
                    .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                    .connectionTimeoutMs(15 * 1000) //连接超时时间，默认15秒
                    .sessionTimeoutMs(60 * 1000)    //会话超时时间，默认60秒
                    .namespace("test")              //设置命名空间
                    .build();
            curatorFramework.start();
            try {
                //throw exception when node not exist
                curatorFramework.getData().usingWatcher(
                        (Watcher) event -> {
                            System.out.println("event type: " + event.getType());
                            System.out.println("event state: " + event.getState());
                        }).forPath("/1");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //Node cache
        threadPoolExecutor.submit(() -> {
            CuratorFramework client = CuratorFrameworkFactory.builder()
                    .connectString(IP_PORT)
                    .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                    .connectionTimeoutMs(15 * 1000) //连接超时时间，默认15秒
                    .sessionTimeoutMs(60 * 1000)    //会话超时时间，默认60秒
                    .namespace("test")              //设置命名空间
                    .build();
            client.start();
            NodeCache nodeCache = new NodeCache(client, "/2");
            try {
                nodeCache.start(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            nodeCache.getListenable().addListener(
                    () -> System.out.println("event data: " + nodeCache.getCurrentData())
            );
        });

        //path cache
        threadPoolExecutor.submit(() -> {
            CuratorFramework client = CuratorFrameworkFactory.builder()
                    .connectString(IP_PORT)
                    .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                    .connectionTimeoutMs(15 * 1000) //连接超时时间，默认15秒
                    .sessionTimeoutMs(60 * 1000)    //会话超时时间，默认60秒
                    .namespace("test")              //设置命名空间
                    .build();
            client.start();
            PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/3", true);
            try {
                pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
            } catch (Exception e) {
                e.printStackTrace();
            }
            pathChildrenCache.getListenable().addListener((curatorClient, event) -> {
                switch (event.getType()) {
                    case CHILD_ADDED:
                        System.out.println("CHILD_ADDED:" + event.getData().getPath());
                        break;
                    case CHILD_REMOVED:
                        System.out.println("CHILD_REMOVED:" + event.getData().getPath());
                        break;
                    case CHILD_UPDATED:
                        System.out.println("CHILD_UPDATED:" + event.getData().getPath());
                        break;
                    case CONNECTION_LOST:
                        System.out.println("CONNECTION_LOST:" + event.getData().getPath());
                        break;
                    case CONNECTION_RECONNECTED:
                        System.out.println("CONNECTION_RECONNECTED:" + event.getData().getPath());
                        break;
                    case CONNECTION_SUSPENDED:
                        System.out.println("CONNECTION_SUSPENDED:" + event.getData().getPath());
                        break;
                    case INITIALIZED:
                        System.out.println("INITIALIZED:" + event.getData().getPath());
                        break;
                    default:
                        break;
                }
            });
        });

        threadPoolExecutor.submit(() -> {
            CuratorFramework client = CuratorFrameworkFactory.builder()
                    .connectString(IP_PORT)
                    .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                    .connectionTimeoutMs(15 * 1000) //连接超时时间，默认15秒
                    .sessionTimeoutMs(60 * 1000)    //会话超时时间，默认60秒
                    .namespace("test")             //设置命名空间
                    .build();
            client.start();

        });

        while (true) {
            Thread.sleep(3000);
        }
    }
}
