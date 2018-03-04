package com.cblue.shop.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cblue.common.pojo.ResponseResult;
import com.cblue.common.utils.JsonUtils;
import com.cblue.shop.sso.service.TokenService;


/**
 * 根据token查询用户信息Controller
 */
@Controller
public class TokenController {

	@Autowired
	private TokenService tokenService;
	
/*	@RequestMapping(value="/user/token/{token}")
	@ResponseBody
	public ResponseResult getUserByToken(@PathVariable String token) {
		ResponseResult result = tokenService.getUserByToken(token);
		return result;
	}*/
	                                             //这里设置ResponseHeader的contentType为application/json
	                                             //等同于"application/json;charset=utf-8"
	@RequestMapping(value="/user/token/{token}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE) 
	@ResponseBody
	public String getUserByToken(@PathVariable String token, String callback) {
		ResponseResult result = tokenService.getUserByToken(token);
		//响应结果之前，判断是否为jsonp请求
		if (StringUtils.isNotBlank(callback)) {
			//把结果封装成一个js语句响应
			return callback + "(" + JsonUtils.objectToJson(result)  + ");";
		}
		return JsonUtils.objectToJson(result);
	}
	
	//spring4.1及之后的写法
	/*@RequestMapping(value="/user/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback) {
		ResponseResult result = tokenService.getUserByToken(token);
		//响应结果之前，判断是否为jsonp请求
		if (StringUtils.isNotBlank(callback)) {
			//把结果封装成一个js语句响应
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
		return result;
	}*/
}
