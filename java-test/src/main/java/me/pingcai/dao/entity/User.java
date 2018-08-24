package me.pingcai.dao.entity;

import java.util.Date;
import me.pingcai.dao.enums.UserSex;
import me.pingcai.dao.enums.UserStatus;

public class User {
    private Long id;

    private UserStatus status;

    private String name;

    private UserSex sex;

    private Byte age;

    private Date birthday;

    private String comment;

    public User(Long id, UserStatus status, String name, UserSex sex, Byte age, Date birthday, String comment) {
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

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserSex getSex() {
        return sex;
    }

    public void setSex(UserSex sex) {
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