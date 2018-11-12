package com.example.demo.domain;

import java.io.Serializable;

/**
 * @Auther: Administrator
 * @Date: 2018/10/11 16:54
 * @Description: 角色表
 */
public class SysRole implements Serializable{
    static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色标识
     */
    private String sign;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
