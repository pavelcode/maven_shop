package com.cblue.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cblue.common.pojo.ResponseResult;
import com.cblue.shop.search.SearchItemService;

@Controller
public class SearchItemController {
	
		@Autowired
		private SearchItemService searchItemService;

		@RequestMapping("/index/item/import")
		@ResponseBody
		public ResponseResult importItemList() {
			ResponseResult responseResult = searchItemService.importAllItems();
			return responseResult;
			
		}
	

	

}
