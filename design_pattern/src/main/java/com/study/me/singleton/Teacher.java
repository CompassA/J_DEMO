package com.study.me.singleton;

/**
 * @author fanqie
 * @date 2020/5/8
 */
public class Teacher {
    private final int id;
    private final String name;
    private final int age;
    private final boolean isSpecial;
    private final boolean isFemale;

    public Teacher(final int id, final String name, final int age, final boolean isSpecial, final boolean isFemale) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isSpecial = isSpecial;
        this.isFemale = isFemale;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public boolean isSpecial() { return isSpecial; }
    public boolean isFemale() { return isFemale; }

    public static InnerBuilder builder() { return new InnerBuilder(); }

    private static class InnerBuilder {
        private int innerId;
        private String innerName;
        private int innerAge;
        private boolean innerIsSpecial = false;
        private boolean innerIsFemale = false;

        public InnerBuilder id(final int id) {
            innerId = id;
            return this;
        }

        public InnerBuilder name(final String name) {
            innerName = name;
            return this;
        }

        public InnerBuilder age(final int age) {
            innerAge = age;
            return this;
        }

        public InnerBuilder isSpecial(final boolean isSpecial) {
            innerIsSpecial = isSpecial;
            return this;
        }

        public InnerBuilder isFemale(final boolean isFemale) {
            innerIsFemale = isFemale;
            return this;
        }

        public Teacher build() {
            return new Teacher(innerId, innerName, innerAge, innerIsSpecial, innerIsFemale);
        }
    }

    public static void main(final String[] args) {
        //男普通老师
        final Teacher teacher1 = Teacher.builder().id(1).age(12).name("老师1").build();

        //女普通老师
        final Teacher teacher2 = Teacher.builder().id(2).age(30).name("老师2").isFemale(true).build();

        //男特级老师
        final Teacher teacher3 = Teacher.builder().id(1).age(12).name("老师3").isSpecial(true).build();

        //女特级老师
        final Teacher teacher4 = Teacher.builder().id(1).age(12).name("老师4").isSpecial(true).isFemale(true).build();
    }
}
