package com.QQ.message.service;

import java.io.OutputStream;
import java.net.Socket;

import com.QQ.entity.Message;
import com.QQ.util.SerializableUtil;

public class ClientThread implements Runnable{
	private Message message;
	public String ip;
	public ClientThread(Message message,String ip){
		this.message=message;
		this.ip=ip;
	}


public void run(){
	try {
		Socket socket=new Socket(ip, 8888);
		OutputStream os=socket.getOutputStream();
		os.write(SerializableUtil.serializableMessage(message));
		os.flush();
		os.close();
	} catch (Exception e) {
		e.printStackTrace();
	}

}
}