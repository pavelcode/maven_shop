package com.cblue.shop.sso.service;

import com.cblue.common.pojo.ResponseResult;

public interface LoginService {

	ResponseResult userLogin(String username, String password);
}
