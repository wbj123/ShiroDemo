package com.example.demo.service;

import com.example.demo.domain.Permission;

import java.util.List;
import java.util.Set;

/**
 * @author: Administrator
 * @Date: 2018/10/11 17:41
 * @Description: 权限表service
 */
public interface PermissionService {
    List<Permission> findAll();
    Set<String> findByUserId(Integer userId);
}
