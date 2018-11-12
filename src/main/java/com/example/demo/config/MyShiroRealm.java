package com.example.demo.config;

import com.example.demo.domain.SysUser;
import com.example.demo.service.PermissionService;
import com.example.demo.service.SysUserService;
import com.example.demo.utils.MD5Utils;
import com.example.demo.utils.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: Administrator
 * @Date: 2018/10/12 13:17
 * @Description:
 */

public class MyShiroRealm extends AuthorizingRealm {
    private static  final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 角色权限和对应权限添加
     *
     * 如果只是简单的身份认证没有权限的控制的话
     * 那么这个方法可以不进行实现，直接返回null即可。
     * @param principalCollection
     * @return
     */
    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("权限配置 ------> ");
        SysUser user =(SysUser) SecurityUtils.getSubject().getPrincipal();
        Integer userId = user.getId();
        logger.info(userId + "");
        //先通过用户id 查用户角色表 再查权限角色 再查权限表 得到此用户拥有什么权限
        Set<String> permissionSet = permissionService.findByUserId(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //设置权限的标记 如：sys:menu:menu
        //再controller上加上@RequiresPermissions("sys:user:user")用来设置权限与上一行对应
        info.addStringPermissions(permissionSet);
        logger.info(permissionSet.toString());
        return info;
    }

    /**
     * 用户认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
    	logger.info(username + "<------>" + password);
        Map<String, Object> map = new HashMap(16);
        map.put("username",username);
        List<SysUser> list = sysUserService.list(map);
        if (CollectionUtils.isEmpty(list)) {
            throw new UnknownAccountException("账号不存在");
        }
        logger.info(list.get(0).getUsername() + list.get(0).getPassword());
        //账户信息
        SysUser user = list.get(0);
        if (null == user) {
            throw new UnknownAccountException("账号不存在");
        }
        // 密码错误
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,password,getName());
//        //前后端分离
//        if (null == redisUtil.get(username)) {
//            return info;
//        }
//        String value = (String) redisUtil.get(username);
//        if(null != value && !"".equals(value)){
//            value = MD5Utils.encrypt(username, value);
//            if(value.equals(password)){
//                SimpleAuthenticationInfo info2 = new SimpleAuthenticationInfo(user, password, getName());
//                return info2;
//           }else{
//                throw new IncorrectCredentialsException("验证码输入错误");
//           }
//        }

        return info;
    }
}
