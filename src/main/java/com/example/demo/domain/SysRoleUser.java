package com.example.demo.domain;

import java.io.Serializable;

/**
 * @Auther: Administrator
 * @Date: 2018/10/11 16:54
 * @Description: 用户角色关联表
 */
public class SysRoleUser implements Serializable{
    static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer sysUserId;

    /**
     * 角色id
     */
    private Integer sysRoleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Integer getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
    }
}
