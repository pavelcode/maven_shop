package com.cblue.shop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cblue.common.pojo.EasyUITreeNode;

import com.cblue.shop.mapper.TbItemCatMapper;
import com.cblue.shop.pojo.TbItemCat;
import com.cblue.shop.pojo.TbItemCatExample;
import com.cblue.shop.pojo.TbItemCatExample.Criteria;
import com.cblue.shop.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public List<EasyUITreeNode> getItemCatList(long parentId) {
		//根据parentId查询子节点列表
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//创建返回结果List
		List<EasyUITreeNode> resultList = new ArrayList<EasyUITreeNode>();
		//把列表转换成EasyUITreeNode列表
		for (TbItemCat tbItemCat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			//设置属性
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");//如果是顶级目录，就open，否则就默认关闭
			//添加到结果列表
			resultList.add(node);
		}
		//返回结果
		return resultList;
	}

}
