package com.cblue.shop.sso.service;

import com.cblue.common.pojo.ResponseResult;



/**
 * 根据token查询用户信息
 */
public interface TokenService {

	ResponseResult getUserByToken(String token);
}
