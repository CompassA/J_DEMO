package com.study.me.decorator;

/**
 * @author FanQie
 * @date 2019/10/20 19:36
 */
public class AddComponent_2 extends BaseDecorator implements Component {

    public AddComponent_2(final Component component) {
        super(component);
    }

    @Override
    public String printMyself() {
        return super.printMyself() + " + AddComponent_2";
    }
}
