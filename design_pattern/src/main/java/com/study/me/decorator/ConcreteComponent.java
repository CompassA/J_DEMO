package com.study.me.decorator;

/**
 * @author FanQie
 * @date 2019/10/20 19:26
 */
public class ConcreteComponent implements Component {

    @Override
    public String printMyself() {
        return "ConcreteComponent";
    }
}
