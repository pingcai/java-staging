package me.pingcai.dao.entity;

import java.util.Date;

public class User {
    private Long id;

    private Byte status;

    private String name;

    private Boolean sex;

    private Byte age;

    private Date birthday;

    private String comment;

    public User(Long id, Byte status, String name, Boolean sex, Byte age, Date birthday, String comment) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.birthday = birthday;
        this.comment = comment;
    }

    public User() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}