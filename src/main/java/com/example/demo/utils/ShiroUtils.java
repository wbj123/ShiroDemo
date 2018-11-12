package com.example.demo.utils;

import com.example.demo.domain.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroUtils {
    private static  final Logger logger = LoggerFactory.getLogger(ShiroUtils.class);
    @Autowired
    private static SessionDAO sessionDAO;

    public static Subject getSubjct() {

        return SecurityUtils.getSubject();
    }
    public static SysUser getUser() {
        Object object = getSubjct().getPrincipal();
        return (SysUser)object;
    }
    public static Integer getUserId() {
        return getUser().getId();
    }
    public static void logout() {
        getSubjct().logout();
    }
    /**
     * 是否有某个url的权限
     */
    public static boolean isPermitted(String url) {
        return SecurityUtils.getSubject().isPermitted(url);
    }
}
