package com.cblue.shop.content.service;

import java.util.List;

import com.cblue.common.pojo.EasyUITreeNode;

public interface ContentCategoryService {
	
	List<EasyUITreeNode>  getContentCategoryList(long parentId);

}
