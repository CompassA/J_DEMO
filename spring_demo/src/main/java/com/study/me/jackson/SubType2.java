package com.study.me.jackson;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author tomato
 * Created on 2020.11.16
 */
@JsonTypeName("sub2")
public class SubType2 extends BaseClass {

    private int val1;
    private String str1;

    public int getVal1() {
        return val1;
    }

    public void setVal1(int val1) {
        this.val1 = val1;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }
}
