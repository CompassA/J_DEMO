package org.study.data;

import org.junit.Test;

/**
 * @author fanqie
 * @date 2020/2/27
 */
public class MyListTest {

    @Test
    public void addDeleteTest() {
        final MyList<Integer> nums = new MyList<>();
        for (int i = 0; i < 100; ++i) {
            nums.add(i);
            System.out.println(nums);
        }
        for (int i = nums.size() - 1; i >= 0; --i) {
            nums.deleteAt(i);
            System.out.println(nums);
        }
        for (int i = 0; i < 100; ++i) {
            nums.add(i);
            System.out.println(nums);
        }
    }

    @Test
    public void insertTest() {
        final MyList<Integer> nums = new MyList<>();
        nums.add(0);
        for (int i = 1; i <= 200; ++i) {
            nums.insert(0, i);
            System.out.println(nums);
        }
    }
}
