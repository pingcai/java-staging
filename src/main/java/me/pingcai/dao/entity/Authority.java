package me.pingcai.dao.entity;

public class Authority {
    private Long id;

    private String authorityName;

    private String authorityDesc;

    public Authority(Long id, String authorityName, String authorityDesc) {
        this.id = id;
        this.authorityName = authorityName;
        this.authorityDesc = authorityDesc;
    }

    public Authority() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getAuthorityDesc() {
        return authorityDesc;
    }

    public void setAuthorityDesc(String authorityDesc) {
        this.authorityDesc = authorityDesc;
    }
}