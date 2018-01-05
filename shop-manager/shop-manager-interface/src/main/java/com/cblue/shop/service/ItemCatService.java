package com.cblue.shop.service;

import java.util.List;

import com.cblue.common.pojo.EasyUITreeNode;

public interface ItemCatService {
	
	List<EasyUITreeNode> getItemCatList(long parentId);

}
