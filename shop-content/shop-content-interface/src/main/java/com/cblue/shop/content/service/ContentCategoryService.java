package com.cblue.shop.content.service;

import java.util.List;

import com.cblue.common.pojo.EasyUITreeNode;
import com.cblue.common.pojo.ResponseResult;

public interface ContentCategoryService {
	
	List<EasyUITreeNode>  getContentCategoryList(long parentId);
	ResponseResult addContentCategory(long parentId,String name);

}
