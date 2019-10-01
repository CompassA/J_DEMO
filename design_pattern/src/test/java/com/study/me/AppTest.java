package com.study.me;

import com.study.me.reflect.ReflectUtil;
import org.junit.Assert;
import org.junit.Test;

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
}
