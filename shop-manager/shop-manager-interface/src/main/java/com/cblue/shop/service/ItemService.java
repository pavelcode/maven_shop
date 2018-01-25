package com.cblue.shop.service;

import com.cblue.common.pojo.EasyUIDataGridResult;
import com.cblue.common.pojo.ResponseResult;
import com.cblue.shop.pojo.TbItem;
import com.cblue.shop.pojo.TbItemDesc;

public interface ItemService {
	
	 TbItem  getItemById(Long id);
	 TbItemDesc getItemDescById(Long id);
	 /**
	  * 
	  * @param currengPage 当前页数
	  * @param pageSize 每页显示条数
	  * @return
	  */
	 EasyUIDataGridResult getItemListByPage(int currengPage,int pageSize);
	 
	 ResponseResult addItem(TbItem item,String desc);
	 
	
	 
	 

}
