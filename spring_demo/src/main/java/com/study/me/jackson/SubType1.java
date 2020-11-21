package com.study.me.jackson;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author tomato
 * Created on 2020.11.16
 */
@JsonTypeName("sub1")
public class SubType1 extends BaseClass {

    private int var0;
    private int var;
    private int num;

    public int getVar0() {
        return var0;
    }

    public void setVar0(int var0) {
        this.var0 = var0;
    }

    public int getVar() {
        return var;
    }

    public void setVar(int var) {
        this.var = var;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
