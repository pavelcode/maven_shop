package com.cblue.shop.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cblue.shop.content.service.ContentService;
import com.cblue.shop.pojo.TbContent;

@Controller
public class IndexController {
	
	/**
	 * 在配置文件springmvc.xml中
	 * <context:property-placeholder location="classpath:conf/resource.properties" />
	 * 配置context:property-placeholder多个，只有一个生效，如果多个配置文件可以在路径里通过逗号分隔
	 */
	@Value("${CONTENT_LUNBO_ID}")
	private Long CONTENT_LUNBO_ID;
	
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("index")  //默认访问index.html
	public String showIndex(Model model){
		//查询内容列表
		List<TbContent> ad1List = contentService.getContentByCategoryId(CONTENT_LUNBO_ID);
		// 把结果传递给页面
		model.addAttribute("ad1List", ad1List);
		return "index";
	}

}
