package com.study.me.decorator;

/**
 * @author FanQie
 * @date 2019/10/20 19:27
 */
public abstract class BaseDecorator implements Component {

    /**
     * 被装饰对象
     */
    private Component component;

    public BaseDecorator(final Component component) {
        this.component = component;
    }

    /**
     * 被增强方法
     */
    @Override
    public String printMyself() {
        return component.printMyself();
    }
}
