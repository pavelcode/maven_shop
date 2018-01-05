package com.cblue.shop.controller;

import java.util.List;

import com.cblue.common.pojo.EasyUITreeNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cblue.shop.service.ItemCatService;

@Controller
public class ItemCatController {
	
	@Autowired
    private ItemCatService itemCatService;
	
	@RequestMapping("/item/cat/list")
	@ResponseBody      //easyui默认提供id参数，默认值是顶级菜单0
	public List<EasyUITreeNode> getItemCatList(@RequestParam(name="id", defaultValue="0")Long parentId) {
		//调用服务查询节点列表
		List<EasyUITreeNode> list = itemCatService.getItemCatList(parentId);
		return list;
		
	}

}
