package com.cblue.shop.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cblue.common.pojo.ResponseResult;
import com.cblue.common.utils.CookieUtils;
import com.cblue.shop.sso.service.LoginService;


/**
 * 用户登录处理
 */
@Controller
public class LoginController {


	
	@RequestMapping("/page/login")
	public String showLogin(String redirect, Model model) {
		model.addAttribute("redirect", redirect);
		return "login";
	}
	
	@Autowired
	private LoginService loginService;
	@Value("${TOKEN_KEY}")
	private String TOKEN_KEY;
	
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult login(String username, String password,
			HttpServletRequest request, HttpServletResponse response) {
		ResponseResult responseResult = loginService.userLogin(username, password);
		//判断是否登录成功
		if(responseResult.getStatus() == 200) {
			String token = responseResult.getData().toString();
			//如果登录成功需要把token写入cookie
			CookieUtils.setCookie(request, response, TOKEN_KEY, token);
		}
		//返回结果
		return responseResult;
	}
	
}
