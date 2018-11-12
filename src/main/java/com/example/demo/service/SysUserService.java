package com.example.demo.service;

import com.example.demo.domain.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2018/10/11 17:41
 * @Description:
 */
public interface SysUserService {
    /**
     * list
     * @param map
     * @return
     */
    List<SysUser> list(Map<String,Object> map);
}
