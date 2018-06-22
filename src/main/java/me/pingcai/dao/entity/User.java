package me.pingcai.dao.entity;

public class User {
    private Long id;

    private String account;

    private String nickname;

    private String password;

    private String passwordSalt;

    private String phone;

    public User(Long id, String account, String nickname, String password, String passwordSalt, String phone) {
        this.id = id;
        this.account = account;
        this.nickname = nickname;
        this.password = password;
        this.passwordSalt = passwordSalt;
        this.phone = phone;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}