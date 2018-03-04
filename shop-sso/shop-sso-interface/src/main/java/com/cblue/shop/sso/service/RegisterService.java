package com.cblue.shop.sso.service;

import com.cblue.common.pojo.ResponseResult;
import com.cblue.shop.pojo.TbUser;


public interface RegisterService {

	ResponseResult checkData(String param, int type);
	
	ResponseResult register(TbUser tbUser);

}
