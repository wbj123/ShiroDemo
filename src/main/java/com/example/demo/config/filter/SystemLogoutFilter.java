//package com.example.demo.config.filter;
//
//import com.alibaba.fastjson.JSONObject;
//import com.fasterxml.jackson.databind.util.JSONPObject;
//import org.apache.shiro.session.SessionException;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.web.filter.authc.LogoutFilter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import java.io.PrintWriter;
//
///**
// * 自定义退出的拦截器，退出返回json
// * @author 35168
// *
// */
//public class SystemLogoutFilter extends LogoutFilter {
//	 private static final Logger log = LoggerFactory.getLogger(SystemLogoutFilter.class);
//
//
//    @Override
//    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
//        Subject subject = getSubject(request, response);
//        try {
//            subject.logout();
//        } catch (SessionException ise) {
//            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
//        }
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter out = response.getWriter();
//        JSONPObject result = new JSONObject();
//        result.put("code", 0);
//        result.put("msg", "成功");
//        out.println(result);
//        out.flush();
//        out.close();
//        return false;
//    }
//}
