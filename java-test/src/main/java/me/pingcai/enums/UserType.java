package me.pingcai.enums;

import org.apache.commons.lang3.StringUtils;

public enum  UserType {

    PRIVATE("0","对私账户"),
    PUBLIC("1","对公账户");

    private String type;
    private String value;

    private UserType(String type,String value){
        this.type = type;
        this.value = value;
    }
    public static UserType of(String type){
        for(UserType u : values()){
            if(StringUtils.equals(type,u.type)){
                return u;
            }
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

