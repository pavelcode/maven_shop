package com.cblue.shop.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("index")  //默认访问index.html
	public String showIndex(){
		return "index";
	}

}
