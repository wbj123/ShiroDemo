package com.example.demo.service.impl;

import com.example.demo.dao.SysUserDao;
import com.example.demo.domain.SysUser;
import com.example.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : Administrator
 * @Date: 2018/10/11 17:42
 * @Description:
 */
@Service
public class SysUserServiceImpl implements SysUserService{
    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public List<SysUser> list(Map<String, Object> map) {
        return sysUserDao.list(map);
    }
}
