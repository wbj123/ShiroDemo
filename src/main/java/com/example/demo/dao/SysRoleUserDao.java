package com.example.demo.dao;

import com.example.demo.domain.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2018/10/11 17:02
 * @Description: 用户角色关联表dao
 */
public interface SysRoleUserDao {
    /**
     *
     * @param map
     * @return
     */
    List<SysUser> list(Map<String, Object> map);
}
