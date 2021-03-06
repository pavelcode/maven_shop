package com.cblue.shop.search;

import com.cblue.common.pojo.SearchResult;

public interface SearchService {
	
	/**
	 * @param keyWord 访问前台页面搜索，会提供搜索关键字
	 * @param page 访问的第几页
	 * @param rows 每页显示的条数，移动端和网页端都可能调用这个接口，而显示的条数可能不一样 
	 */
    SearchResult search(String keyWord,int page,int rows)throws Exception ;
}
