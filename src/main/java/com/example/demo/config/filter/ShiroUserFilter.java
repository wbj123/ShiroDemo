package com.example.demo.config.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.HttpServletUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

/**
 * 当用户没有登录或session失效后，是使用FormAuthenticationFilter进行拦截处理的，我们可以重写里面的onAccessDenied方法，从而修改重定向。
 * @author 35168
 *
 */
public class ShiroUserFilter extends UserFilter {
	private final String TOKEN = "token";

	@Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		//如果是ajax,那就返回json
		   HttpServletRequest req = (HttpServletRequest) request;
		   String uri = req.getRequestURI();
		   if(HttpServletUtils.jsAjax(req) && !StringUtils.isEmpty(uri)){
			   response.setCharacterEncoding("UTF-8");
			   PrintWriter out = response.getWriter();
			   JSONObject result = new JSONObject();
			   result.put("code", -500);
			   result.put("msg", "请登录");
			   out.println(result);
			   out.flush();
			   out.close();
		   }else {
			   String token = WebUtils.toHttp(request).getHeader(TOKEN);
			   if (!StringUtils.isEmpty(token)) {
				   response.setCharacterEncoding("UTF-8");
				   PrintWriter out = response.getWriter();
				   JSONObject result = new JSONObject();
				   result.put("code", -500);
				   result.put("msg", "请登录");
				   out.println(result);
				   out.flush();
				   out.close();
				   return false;
			   }else {
				   token = request.getParameter(TOKEN);
				   if(!StringUtils.isEmpty(token)){
					   response.setCharacterEncoding("UTF-8");
					   PrintWriter out = response.getWriter();
					   JSONObject result = new JSONObject();
					   result.put("code", -500);
					   result.put("msg", "请登录");
					   out.println(result);
					   out.flush();
					   out.close();
					   return false;
				   }
			   }
			   saveRequestAndRedirectToLogin(request, response);
		   }
        return false;
	}
}
