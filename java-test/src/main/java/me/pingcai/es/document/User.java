package me.pingcai.es.document;

import me.pingcai.es.EsConstant;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = EsConstant.USER_INDEX,type = EsConstant.USER_TYPE)
public class User {

    @Id
    @Field(type = FieldType.Long)
    private Long id;

    @Field(type = FieldType.Integer)
    private Byte status;

    @Field(type = FieldType.Keyword )
    private String name;

    @Field(type = FieldType.Integer)
    private Byte sex;

    @Field(type = FieldType.Integer)
    private Byte age;

    @Field(type = FieldType.Date)
    private Date birthday;

    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String comment;

    public User(Long id, Byte status, String name, Byte sex, Byte age, Date birthday, String comment) {
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

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
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