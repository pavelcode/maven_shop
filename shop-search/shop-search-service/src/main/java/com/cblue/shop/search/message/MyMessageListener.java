package com.cblue.shop.search.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener {

	@Override
	public void onMessage(Message msg) {
		//取消息内容
		 TextMessage textMessage = (TextMessage) msg;
		 try {
			 String text = textMessage.getText();
			 System.out.println(text);
			} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}
	}

}
