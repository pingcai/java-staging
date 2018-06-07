package me.pingcai.dao.entity;

public class Test {
    private Long id;

    private String name;

    public Test(Long id, String name) {
        this.id = id;
        this.name = name;
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
}