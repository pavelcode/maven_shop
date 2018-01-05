package com.cblue.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	//跳转到WEB－INF下到jsp/index.jsp
	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	
	//根据访问到url，获得访问到jsp名字
	@RequestMapping("/{jspName}")
	public String showPage(@PathVariable String jspName){
		return jspName;
	}

}
