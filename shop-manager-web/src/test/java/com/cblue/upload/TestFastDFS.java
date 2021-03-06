package com.cblue.upload;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.cblue.common.utils.FastDFSClient;

public class TestFastDFS {
	
	/**
	 * 1 创建配置文件，内容是tracker服务器的地址 conf/client.conf  tracker_server=IP:22122
	 * 2 加载配置文件
	 * 3 创建TrackerClient对象，获得一个TrackerService对象
	 * 4 创建StorageServer对象引用
	 * 5 创建StorageClient对象
	 * 6 上传文件
	 */
	
	@Test
	public void upload()throws Exception{
		ClientGlobal.init("E:\\Workspaces_Shop\\shop-manager-web\\src\\main\\resources\\conf\\client.conf");
		
		TrackerClient trackerClient = new TrackerClient();
		
		TrackerServer trackerServer =  trackerClient.getConnection();
		
		StorageServer storageServer = null;
		
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		
		String str[]= storageClient.upload_appender_file("E:\\a.jpg","jpg",null);
		
		for(String s:str){
			System.out.println(s);
		}
			
	}
	
	/**
	 * 使用工具类实现图片上传
	 */
	@Test
	public void testUpload02() throws Exception {
		FastDFSClient fastDFSClient = new FastDFSClient("E:\\Workspaces_Shop\\shop-manager-web\\src\\main\\resources\\conf\\client.conf");
		String string = fastDFSClient.uploadFile("E:\\a.jpg");
		System.out.println(string);
	}

}
