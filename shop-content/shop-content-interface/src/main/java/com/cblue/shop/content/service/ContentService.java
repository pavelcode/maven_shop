package com.cblue.shop.content.service;

import java.util.List;

import com.cblue.common.pojo.ResponseResult;
import com.cblue.shop.pojo.TbContent;

public interface ContentService {
	
	ResponseResult addContent(TbContent tbContent);
	List<TbContent> getContentByCategoryId(long categoryId);

}
