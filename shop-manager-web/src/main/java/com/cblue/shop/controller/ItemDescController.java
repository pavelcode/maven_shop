package com.cblue.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cblue.common.pojo.ResponseResult;
import com.cblue.shop.pojo.TbItemDesc;

@Controller
public class ItemDescController {

	
	/**
	 * 模拟数据，不写dao了
	 *加载数据的描述信息
	 */
	///rest/item/query/item/desc/'+data.id
	@RequestMapping("/rest/item/query/item/desc/{itemId}")
	@ResponseBody
	public ResponseResult getItemDescById(@PathVariable Long itemId) {
		System.out.println("getItemDescById----"+itemId);
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemDesc("描述信息");
		return ResponseResult.ok(tbItemDesc);
	}

}
