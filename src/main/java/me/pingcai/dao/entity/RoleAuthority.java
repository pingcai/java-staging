package me.pingcai.dao.entity;

public class RoleAuthority {
    private Long id;

    private Long roleId;

    private Long authorityId;

    public RoleAuthority(Long id, Long roleId, Long authorityId) {
        this.id = id;
        this.roleId = roleId;
        this.authorityId = authorityId;
    }

    public RoleAuthority() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }
}