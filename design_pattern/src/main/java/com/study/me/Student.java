package com.study.me;

/**
 * bean
 * @author FanQie
 * @date 2019/10/1 18:30
 */
public class Student {

    private int id;

    private String name;

    private boolean gender;

    /**
     * 内部构造类
     */
    public static class Builder {
        private int id = 0;

        private String name = "default";

        private boolean gender = false;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder gender(boolean gender) {
            this.gender = gender;
            return this;
        }

        /**
         * 内部构造实例对象
         * @return Student
         */
        public Student build() {
            return new Student()
                    .setGender(this.gender)
                    .setId(this.id)
                    .setName(this.name);
        }
    }

    /**
     * 外部持有生成构造者的静态方法
     * @return 构造者
     */
    public static Builder builder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public Student setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isGender() {
        return gender;
    }

    public Student setGender(boolean gender) {
        this.gender = gender;
        return this;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}
