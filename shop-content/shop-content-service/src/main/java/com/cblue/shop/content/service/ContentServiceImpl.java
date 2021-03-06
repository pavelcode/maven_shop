package com.cblue.shop.content.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cblue.common.jedis.JedisClient;
import com.cblue.common.pojo.ResponseResult;
import com.cblue.common.utils.JsonUtils;
import com.cblue.shop.mapper.TbContentMapper;
import com.cblue.shop.pojo.TbContent;
import com.cblue.shop.pojo.TbContentExample;
import com.cblue.shop.pojo.TbContentExample.Criteria;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Override
	public ResponseResult addContent(TbContent tbContent) {
		// 将内容数据插入到内容表
		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		// 插入到数据库
		tbContentMapper.insert(tbContent);
		//缓存同步 把之前的缓存删除
		jedisClient.hdel("CONTENT_LIST", tbContent.getCategoryId().toString());
		return ResponseResult.ok();
	}

	@Override
	public List<TbContent> getContentByCategoryId(long categoryId) {
		//获得缓存
		try {
			/**
			 * 这里可以使用置文件conf/resource.properties  CONTENT_LIST = CONTENT_LIST
			 * 在applicationContext-dao.xml中修改
			 * <context:property-placeholder location="classpath:db/*.properties,classpath:conf/*.properties" />
			 * @value("${CONTENT_LIST}")
			 * private String CONTENT_LIST;
			 */
			String jsonStr = jedisClient.hget("CONTENT_LIST", categoryId+"");
			System.out.println("得到缓存："+jsonStr);
			if(StringUtils.isNotBlank(jsonStr)){			
				List<TbContent> list = JsonUtils.jsonToList(jsonStr, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		/*
		 * 在TbContentMapper中，selectByExampleWithBlobs和selectByExample的区别就是
		 * 是否包含大文本
		 */
		//查询数据库
		 TbContentExample example = new TbContentExample();
		 Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andCategoryIdEqualTo(categoryId);
		//执行查询
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
		
		//添加缓存
		try {
			jedisClient.hset("CONTENT_LIST",categoryId+"", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

}
