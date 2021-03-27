package com.study.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tomato
 * Created on 2021.03.28
 */
public class LRUCache {
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;
    private int size;
    private int capacity;

    private static class Node {
        int key;
        int val;
        Node prev;
        Node next;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
            prev = next = null;
        }
    }

    public LRUCache(int capacity) {
        head = tail = null;
        size = 0;
        this.capacity = capacity + 1;
        map = new HashMap<>();
    }

    public int get(int key) {
        Node cache = map.get(key);
        if (cache == null) {
            return -1;
        }
        moveToHead(cache);
        return cache.val;
    }

    public void put(int key, int value) {
        Node cache = map.get(key);
        if (cache != null) {
            cache.val = value;
            moveToHead(cache);
            return;
        }
        cache = new Node(key, value);
        insertHead(cache);
        map.put(key, cache);

        if (size == capacity) {
            Node tail = removeTail();
            map.remove(tail.key);
        }
    }

    private void insertHead(Node newHead) {
        ++size;
        if (head == null) {
            head = tail = newHead;
            return;
        }
        newHead.next = head;
        head.prev = newHead;
        head = newHead;
    }

    private Node removeTail() {
        --size;
        Node oldTail = tail;
        if (head == tail) {
            head = tail = null;
            return oldTail;
        }
        tail.prev.next = null;
        tail = tail.prev;

        oldTail.prev = null;
        return oldTail;
    }

    private void moveToHead(Node node) {
        if (head == tail || node.prev == null) {
            return;
        }
        node.prev.next = node.next;
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        node.prev = null;
        head.prev = node;
        node.next = head;
        head = node;
    }
}
