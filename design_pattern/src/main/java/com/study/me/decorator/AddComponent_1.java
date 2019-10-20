package com.study.me.decorator;

/**
 * @author FanQie
 * @date 2019/10/20 19:31
 */
public class AddComponent_1 extends BaseDecorator implements Component {

    public AddComponent_1(final Component component) {
        super(component);
    }

    @Override
    public String printMyself() {
        return super.printMyself() + " + AddComponent_1";
    }
}
