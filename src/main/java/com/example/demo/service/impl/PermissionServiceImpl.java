package com.example.demo.service.impl;

import com.example.demo.dao.PermissionDao;
import com.example.demo.domain.Permission;
import com.example.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : Administrator
 * @Date: 2018/10/11 17:42
 * @Description:
 */
@Service
public class PermissionServiceImpl implements PermissionService{
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public Set<String> findByUserId(Integer userId) {
        List<Permission> list = permissionDao.findByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (Permission permission : list) {
            String descripion = permission.getDescripion();
            permsSet.add(descripion);
        }
        return permsSet;
    }
}
