package com.example.demo.dao;

import com.example.demo.domain.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2018/10/11 17:02
 * @Description: 角色表dao
 */
public interface SysRoleDao {
    /**
     *
     * @param map
     * @return
     */
    List<SysUser> list(Map<String, Object> map);
}
