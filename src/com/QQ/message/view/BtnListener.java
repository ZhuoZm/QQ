package com.QQ.message.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import com.QQ.entity.Message;
import com.QQ.message.service.ClientThread;
import com.QQ.message.service.MessageServiceImpl;

public class BtnListener implements ActionListener{

	ChatFrame chatFrame;
	public BtnListener(ChatFrame chatFrame) {
		this.chatFrame=chatFrame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String msg=chatFrame.txtMsg.getText();
		//new Thread(new ClientThread(msg)).start();
		Message message=new Message();
		message.setContent(msg);
		message.setSender(chatFrame.myself.getQqnum());
		message.setReceiver(chatFrame.another.getQqnum());
		message.setSendTime(new Date());
		message.setState(1);
		//chatFrame.txtMsg.setText("");
		
		//���ݿ����
		MessageServiceImpl messageServiceImpl=new MessageServiceImpl();
		int key=messageServiceImpl.sendMessage(message);
		message.setId(key);
		
		//�������̣߳�����Ϣ
		new Thread(new ClientThread(message,chatFrame.another.getIp())).start();
		chatFrame.txtMsg.setText("");
		
		
		//TOdof������Ϣ���ŵ��ı�����
		String content=chatFrame.txtList.getText();
		int size=chatFrame.txtList.getSize().width/40;//�õ��ı��򳤶�,���е���
		int spaceSize=size-chatFrame.myself.getNickName().length()-msg.length()-1;
		if(spaceSize>0){
			for(int i=0;i<spaceSize;i++){
				content+=" ";
			}
		}
		content+=chatFrame.myself.getNickName()+":"+msg+"\n";
		chatFrame.txtList.setText(content);
		
	}

}
