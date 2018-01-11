package com.cblue.shop.controller;

import com.cblue.common.pojo.EasyUIDataGridResult;
import com.cblue.common.pojo.ResponseResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cblue.shop.pojo.TbItem;
import com.cblue.shop.service.ItemService;

@Controller
public class ItemController {
	

	@Autowired
	private ItemService itemService;
	/**
	 * @ResponseBody 返回的结果是json格式
	 * @PathVariable 从请求路径中获得itemid值
	 */
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	
	@RequestMapping("/item/list")  //在easyui访问数据时，确定的访问地址
	@ResponseBody  //返回的类型是json格式                             //page,rows是easyui默认提供的分页参数
	public EasyUIDataGridResult getItemList(@RequestParam(name="page")Integer currentPage,@RequestParam(name="rows")Integer pageSize) {  
		//调用服务查询商品列表
		EasyUIDataGridResult result = itemService.getItemListByPage(currentPage,pageSize);
		return result;
	}
	
	/**
	 * 商品添加功能
	 */
	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult addItem(TbItem item, String desc) {
		ResponseResult result = itemService.addItem(item, desc);
		return result;
	}
	
	
	/**
	 * 展示修改页面
	 */
	@RequestMapping("/rest/page/item-edit")
	public String showEditPage(){
		return "item-edit";
	}
	
	
	
	
	

}
