package com.study.me;

import com.study.me.decorator.AddComponent_1;
import com.study.me.decorator.AddComponent_2;
import com.study.me.decorator.Component;
import com.study.me.decorator.ConcreteComponent;
import com.study.me.dynamicproxy.TimeHandler;
import com.study.me.proxy.SomeFunction;
import com.study.me.proxy.WorkerGay;
import com.study.me.proxy.WorkerGayProxy;
import com.study.me.reflect.ReflectUtil;
import com.study.me.singleton.Connection;
import com.study.me.singleton.SingletonConnection;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

/**
 * Unit Student for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    /**
     * 实例化Student
     */
    @Test
    public void newStudentInstance() throws IllegalAccessException, InstantiationException {
        //builder构造
        ReflectUtil.printClassMessage(Student.class);

        final Student stu1 = Student.builder()
                .id(1)
                .name("abs")
                .gender(true)
                .build();
        Assert.assertTrue(Objects.nonNull(stu1));
        System.out.println(stu1);

        //反射构造
        final Class<Student> studentClass = Student.class;
        final Student stu2 = studentClass.newInstance();
        Assert.assertTrue(Objects.nonNull(stu2));
        System.out.println(stu2);
    }

    @Test
    public void singletonTest() {
        //静态内部类单例
        final Connection conn1 = Connection.getInstance();
        conn1.open();
        conn1.close();

        //枚举单例
        final SingletonConnection conn2 = SingletonConnection.INSTANCE;
        conn2.open();
        conn2.close();

        final Connection ins1 = Connection.getInstance();
        final Connection ins2 = Connection.getInstance();
        Assert.assertSame(ins1,ins2);

        final SingletonConnection ins3 = SingletonConnection.INSTANCE;
        final SingletonConnection ins4 = SingletonConnection.INSTANCE;
        Assert.assertSame(ins3, ins4);
    }

    /**
     * 通过反射破解私有构造器单例
     */
    @Test
    public void reflectSingletonAtkTest()
            throws IllegalAccessException, InvocationTargetException, InstantiationException {
        //非枚举类反射攻击
        final Constructor<Connection> constructor;
        try {
            constructor = Connection.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            final Connection connection1 = constructor.newInstance();
            final Connection connection2 = Connection.getInstance();

            System.out.println(connection1);
            System.out.println(connection2);
            Assert.assertNotSame(connection1, connection2);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //枚举类反射攻击失效
        final Constructor<SingletonConnection> singletonConnectionConstructor;
        try {
            singletonConnectionConstructor = SingletonConnection.class.getDeclaredConstructor();
            singletonConnectionConstructor.newInstance();
        } catch (final Exception e) {
            Assert.assertTrue(e instanceof NoSuchMethodException);
        }
    }

    /**
     * 通过反射 让一个List<String>放不同类型的实例
     */
    @Test
    public void listReflectTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final List<String> strList = new ArrayList<String>() {{
            add("str");
        }};

        final Method addMethod = ArrayList.class.getMethod("add", Object.class);
        addMethod.invoke(strList, 1);
        addMethod.invoke(strList, new LinkedList<>());

        for (int i = 0; i < strList.size(); ++i) {
            try {
                System.out.printf("strList[%d] instance of %s", i, strList.get(i).getClass().getSimpleName());
            } catch (final Exception e) {
                e.printStackTrace();
                Assert.assertTrue(e instanceof ClassCastException);
            }
        }
    }

    /**
     * 静态代理测试
     */
    @Test
    public void staticProxy() {
        final SomeFunction proxy = new WorkerGayProxy(new WorkerGay());
        proxy.doSomething();
    }

    /**
     * 动态代理测试
     */
    @Test
    public void dynamicProxy() {
        final TimeHandler timeHandler = new TimeHandler(new WorkerGay());
        //way 1
        final SomeFunction someFunction =
                (SomeFunction) timeHandler.newProxyInstance();

        someFunction.doSomething();

        //way 2
        final SomeFunction someFunction2 = (SomeFunction) Proxy.newProxyInstance(
                SomeFunction.class.getClassLoader(),
                new Class[] {SomeFunction.class},
                timeHandler);
        //代理了SomeFunction接口的全部方法
        someFunction2.doSomething();
        someFunction2.sleep();
    }

    /**
     * 装饰者测试
     */
    @Test
    public void decoratorTest() {
        //base
        final Component concreteComponent = new ConcreteComponent();
        Assert.assertEquals(concreteComponent.printMyself(),
                "ConcreteComponent");

        //base + component1
        final Component component1 = new AddComponent_1(concreteComponent);
        Assert.assertEquals(component1.printMyself(),
                "ConcreteComponent + AddComponent_1");

        //base + component2
        final Component component2 = new AddComponent_2(concreteComponent);
        Assert.assertEquals(component2.printMyself(),
                "ConcreteComponent + AddComponent_2");

        //base + component1 + component2
        final Component component3 = new AddComponent_2(component1);
        Assert.assertEquals(component3.printMyself(),
                "ConcreteComponent + AddComponent_1 + AddComponent_2");

        //base + component1 + component2 + component3
        final Component component4 = new AddComponent_1(component3);
        Assert.assertEquals(component4.printMyself(),
                "ConcreteComponent + AddComponent_1 + AddComponent_2 + AddComponent_1");
    }
}
