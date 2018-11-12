package com.example.demo.controller;

import com.example.demo.domain.SysUser;
import com.example.demo.utils.MD5Utils;
import com.example.demo.utils.RedisUtil;
import com.example.demo.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserController
 *
 * @author: Administrator
 * @Date: 2018/10/11 14:27
 * @Description:
 */
@Controller
public class UserController {
	private static  final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private RedisUtil redisUtil;

	@RequestMapping("/test")
    @ResponseBody
	@RequiresPermissions("sys:user:list")
	public List test(){
		List<Object> list = new ArrayList<Object>();
		list.add("test");
		return list;
    }

	@RequestMapping("/403")
	@ResponseBody
	@RequiresPermissions("sys:user:list")
	public List unauthorized(){
		List<Object> list = new ArrayList<Object>();
		list.add("未授权");
		return list;
	}

	@RequestMapping("/test2")
	@ResponseBody
	public List test2(){
		List<Object> list = new ArrayList<Object>();
		list.add("test Two");
		return list;
	}

	@RequestMapping("/login")
	@ResponseBody
	public Map userLogin(@RequestParam(value = "username",required = false) String username,
						 @RequestParam(value = "password",required = false) String password,
						 HttpServletRequest request){
		String cookie = request.getHeader("Cookie");
		logger.info(cookie);
		Map<String, Object> map = new HashMap(16);
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			map.put("code","500");
			map.put("msg","请登录或登录参数为空");
			return map;
		}
		String pwd = MD5Utils.encrypt(username, password);
		logger.info("------ username:"+username+" , password:"+ password);

		UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
		Subject subject = SecurityUtils.getSubject();
//		token.setRememberMe(true);
//		SysUser user = (SysUser) subject.getPrincipal();
		try {
//			redisUtil.set("admin", "123");
			subject.login(token);
			map.put("token", subject.getSession().getId());
			map.put("msg", "登录成功");
			map.put("username", ShiroUtils.getUser().getUsername());
		} catch (IncorrectCredentialsException e) {
			map.put("msg", "密码错误");
		} catch (LockedAccountException e) {
			map.put("msg", "登录失败，该用户已被冻结");
		} catch (AuthenticationException e) {
			map.put("msg", "该用户不存在");
		}catch (Exception e) {
			e.printStackTrace();
		}
		//登录 接口
		return map;
	}

	@RequestMapping(value = "/unauth")
	@ResponseBody
	public Map unauth() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "1000000");
		map.put("msg", "未登录");
		return map;
	}
}
