package com.cblue.shop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cblue.common.utils.FastDFSClient;
import com.cblue.common.utils.JsonUtils;

@Controller
public class UploadImageController {

	@Value("${IMAGE_SERVER_URL}") //加载资源文件中的属性值
	private String IMAGE_SERVER_URL;
    // produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8"指定返回结果是text/plain类型，字符编码utf-8
	@RequestMapping(value="/pic/upload", produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	//@ResponseBody //返回json格式数据
	public String uploadFile(MultipartFile uploadFile) {
		try {
			//把图片上传的图片服务器
			
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
			//取文件扩展名
			String originalFilename = uploadFile.getOriginalFilename();//获得原文件名字
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);//截取文件名获得后缀
			//得到一个图片的地址和文件名
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
			//补充为完整的url
			/**
			 * 新建配置文件conf/resource.properties  IMAGE_SERVER_URL=http\://192.168.25.133
			 * 在springmvc.xml中添加<context:property-placeholder location="classpath:conf/resource.properties" />
			 * 来加载配置文件
			 * 在上面添加@Value("${IMAGE_SERVER_URL}") private String IMAGE_SERVER_URL;
			 * 
			 */
			url = IMAGE_SERVER_URL + url;
			//封装到map中返回
			Map result = new HashMap<>();
			result.put("error", 0);
			result.put("url", url);
			//在common项目中添加一个json工具类
			System.out.println(JsonUtils.objectToJson(result));
			return JsonUtils.objectToJson(result);
		} catch (Exception e) {
			e.printStackTrace();
			Map result = new HashMap<>();
			result.put("error", 1);
			result.put("message", "图片上传失败");
			System.out.println(JsonUtils.objectToJson(result));
			return JsonUtils.objectToJson(result);
		}
	}
}
