package com.study.me;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试HashMap链表到几个之后才会树化
 * @author fanqie
 * @date 2019/12/8
 */
public class HashMapDemo {

    static class Key {

        private int val;

        public Key(int val) {
            this.val = val;
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            } else if (other instanceof HashMapDemo.Key) {
                return (((Key) other).val == this.val);
            }
            return false;
        }
    }

    /**
     * 当第九个节点插入完成之后
     * tab[1].next.next.next.next.next.next.next.next.next = null
     * 会调用树化函数
     */
    public static void main(String[] args) {
        final Map<Key, String> map = new HashMap<>(0);
        //条件断点 i == 8
        for (int i = 0; i < 9; ++i) {
            map.put(new Key(i), "");
        }
    }
}
