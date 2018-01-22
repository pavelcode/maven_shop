package com.cblue.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cblue.common.pojo.EasyUITreeNode;
import com.cblue.common.pojo.ResponseResult;
import com.cblue.shop.content.service.ContentCategoryService;

/**
 * 内容分类管理Controller
 */
@Controller
public class ContentCatController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(
			@RequestParam(name="id", defaultValue="0")Long parentId) {
		List<EasyUITreeNode> list = contentCategoryService.getContentCategoryList(parentId);
		return list;
	}
	
	
	/**
	 * 添加分类节点
	 */
	@RequestMapping(value="/content/category/create", method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult createContentCategory(Long parentId, String name) {
		//调用服务添加节点
		ResponseResult responseResult = contentCategoryService.addContentCategory(parentId, name);
		return responseResult;
	}
	
	
}
