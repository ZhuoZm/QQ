package com.QQ.message.service;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.Iterator;

import java.io.InputStream;

import com.QQ.entity.Message;
import com.QQ.message.view.ChatFrame;
import com.QQ.user.view.MainFrame;
import com.QQ.util.SerializableUtil;

public class ServerThread implements Runnable{
	MainFrame mainFrame;
 	public ServerThread(MainFrame mainFrame){
 		this.mainFrame=mainFrame;
 	}
	
	
	public void run(){
		try {
			ServerSocket serverSocket=new ServerSocket(8888);
			while(true){
				Socket socket=serverSocket.accept();
				InputStream is=socket.getInputStream();
				if(is.available()>0){
				byte cache[]=new byte[is.available()];
				is.read(cache);
				Message message=SerializableUtil.unSerializableUtil(cache);
				//先输出一下看看
				 System.out.println(message.getSender());
				 System.out.println(message.getContent());
				
				//TOdo接受消息并放到文本框中
				Iterator i=mainFrame.chatingFrames.keySet().iterator();
				boolean isExist=false;
				while(i.hasNext()){
					int key=Integer.parseInt(i.next().toString());
					if(message.getSender()==key){
						ChatFrame chatFrame=mainFrame.chatingFrames.get(key);
						String content=chatFrame.txtList.getText();
						chatFrame.txtList.setText(content+chatFrame.another.getNickName()+":"+message.getContent());
						isExist=true;
						break;
					}
				
				if(!isExist){
					ChatFrame chatFrame=new ChatFrame(mainFrame.myself, message.getSender()+" jlk");
					mainFrame.chatingFrames.put(message.getSender(),chatFrame);
				}
				
				}
				}
			}
		}catch (Exception e){
		e.printStackTrace();
	}
}
}


