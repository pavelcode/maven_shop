package com.cblue.shop.content.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cblue.common.pojo.EasyUITreeNode;
import com.cblue.common.pojo.ResponseResult;
import com.cblue.shop.mapper.TbContentCategoryMapper;
import com.cblue.shop.pojo.TbContentCategory;
import com.cblue.shop.pojo.TbContentCategoryExample;
import com.cblue.shop.pojo.TbContentCategoryExample.Criteria;


@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;


	public List<EasyUITreeNode> getContentCategoryList(long parentId) {
		// TODO Auto-generated method stub
		//根据parentId查询子节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list =tbContentCategoryMapper.selectByExample(example);
		//创建返回结果List
		List<EasyUITreeNode> resultList = new ArrayList<EasyUITreeNode>();
		//把列表转换成EasyUITreeNode列表
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			//设置属性
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");//如果是顶级目录，就open，否则就默认关闭
			//添加到结果列表
			resultList.add(node);
		}
		//返回结果
		return resultList;
	}

	@Override
	public ResponseResult addContentCategory(long parentId, String name) {
		//创建一个tb_content_category表对应的pojo对象
				TbContentCategory contentCategory = new TbContentCategory();
				//设置pojo的属性
				contentCategory.setParentId(parentId);
				contentCategory.setName(name);
				//1(正常),2(删除)
				contentCategory.setStatus(1);
				//默认排序就是1
				contentCategory.setSortOrder(1);
				//新添加的节点一定是叶子节点
				contentCategory.setIsParent(false);
				contentCategory.setCreated(new Date());
				contentCategory.setUpdated(new Date());
				//插入到数据库
				tbContentCategoryMapper.insert(contentCategory);
				//判断父节点的isparent属性。如果不是true改为true
				//根据parentid查询父节点
				TbContentCategory parent = tbContentCategoryMapper.selectByPrimaryKey(parentId);
				if (!parent.getIsParent()) {
					parent.setIsParent(true);
					//更新到数数据库
					tbContentCategoryMapper.updateByPrimaryKey(parent);
				}
				//返回结果，返回E3Result，包含pojo
				return ResponseResult.ok(contentCategory);
	}

}
