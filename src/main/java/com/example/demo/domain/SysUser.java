package com.example.demo.domain;

import java.io.Serializable;

/**
 * @Auther: Administrator
 * @Date: 2018/10/11 16:54
 * @Description:
 */
public class SysUser implements Serializable{
    static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
