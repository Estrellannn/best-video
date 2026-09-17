package com.aoba.aiqiyi.Entiy;

import java.io.Serializable;

public class Role implements Serializable {
    private Integer id;
    private String role_name;

    public Role() {
    }

    public Role(Integer id, String role_name) {
        this.id = id;
        this.role_name = role_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return role_name;
    }

    public void setRoleName(String role_name) {
        this.role_name = role_name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role_name='" + role_name + '\'' +
                '}';
    }
}
