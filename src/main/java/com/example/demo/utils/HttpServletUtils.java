package com.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class HttpServletUtils {
	 private static Logger logger = LoggerFactory.getLogger(HttpServletUtils.class);
    public static boolean jsAjax(HttpServletRequest req){
    	
        //判断是否为ajax请求，默认不是
        boolean isAjaxRequest = false;
        String requestedWith =req.getHeader("x-requested-with");
        logger.debug("x-requested-with:"+requestedWith);
        if(!StringUtils.isEmpty(requestedWith) && req.getHeader("x-requested-with").equals("XMLHttpRequest")){
            isAjaxRequest = true;
        }
        return isAjaxRequest;
    }
}
