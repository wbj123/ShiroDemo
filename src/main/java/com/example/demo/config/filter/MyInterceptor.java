package com.example.demo.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

/**
 * @program: MyInterceptor
 * @description: 拦截器
 * @author: Mr.Wang
 * @create: 2018-05-25 16:27
 */
public class MyInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
    private boolean fa = false;
    private int startTime;
    private int endTime;
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
    //在控制器执行前调用
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        System.out.println("执行preHandle方法-->01");
        String uri = request.getRequestURI();
        logger.info("uri : " + uri);
        Cookie[] cookies = request.getCookies();
        if (uri.contains("/login") && StringUtils.isEmpty(request.getHeader("Cookie"))) {
            return true;
        } else if (uri.contains("/login") && !StringUtils.isEmpty(request.getHeader("Cookie"))){
//            response.setHeader("Cookie","");
            logger.info("Cookie:"+request.getHeader("Cookie"));
            return true;
        }
//        logger.info(request.getRequestURI());
//        String[] split = request.getRequestURI().split("/");
//        logger.info(split[2]);
//        List<Users> users = usersMapper.selectByuOpenid(split[2]);
//        logger.info(users.toString());
//        if (CollectionUtils.isEmpty(users)) {
//            return false;
//        }
        /**/

        if (null == cookies) {
//            logger.info(request.getQueryString());
//            logger.info(request.getContextPath() + " cookie:" + cookies);
        }else {
            StringBuffer stringBuffer = new StringBuffer();
            for (Cookie c:cookies) {
                stringBuffer.append(c.toString());
            }
            logger.info("cookie:" + stringBuffer.toString());
        }

        int i=0;
        if (i < 2) {
            if (uri.equalsIgnoreCase("/findMyFragByOne")) {
                return true;  //通过拦截器，继续执行请求
            } else if (uri.equalsIgnoreCase("/findWorldList")){
            request.getRequestDispatcher("/index").forward(request, response);
                return false;  //没有通过拦截器，返回登录页面
            }
            return true;  //通过拦截器，继续执行请求
        } else {
//            request.setAttribute("msg", "非登录时段");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
