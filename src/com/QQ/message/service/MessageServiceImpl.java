package com.QQ.message.service;

import com.QQ.entity.Message;
import com.QQ.message.dao.MessageDaoImpl;

public class MessageServiceImpl {
	public int sendMessage(Message message){
		MessageDaoImpl messageDaoImpl=new MessageDaoImpl(); 
		return messageDaoImpl.saveMessage(message);
	}
}
