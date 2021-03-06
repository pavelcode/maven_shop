package com.cblue.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cblue.common.pojo.ResponseResult;
import com.cblue.shop.content.service.ContentService;
import com.cblue.shop.pojo.TbContent;

@Controller
public class ContentController {
	
	@Autowired
	private ContentService contentService;

	@RequestMapping(value="/content/save", method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult addContent(TbContent content) {
		//调用服务把内容数据保存到数据库
		ResponseResult responseResult = contentService.addContent(content);
		return responseResult;
	}

}
