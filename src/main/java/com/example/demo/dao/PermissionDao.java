package com.example.demo.dao;

import com.example.demo.domain.Permission;

import java.util.List;

/**
 * @author: Administrator
 * @Date: 2018/10/11 18:13
 * @Description: 权限表dao
 */
public interface PermissionDao {
    List<Permission> findAll();
    List<Permission> findByUserId(Integer userId);
}
