package com.neverend.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Adminuser implements Serializable {
    private Integer uid;

    private String username;

    private String password;

    @Override
    public String toString() {
        return "Adminuser{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private static final long serialVersionUID = 1L;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}