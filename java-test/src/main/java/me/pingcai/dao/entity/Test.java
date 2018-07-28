package me.pingcai.dao.entity;

import java.util.Objects;

public class Test {
    private Long id;

    private String name;

    private Byte age;

    public Test(Long id, String name, Byte age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Test() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(id, test.id) &&
                Objects.equals(name, test.name) &&
                Objects.equals(age, test.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }
}