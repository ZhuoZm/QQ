package com.QQ.user.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.QQ.entity.User;
import com.QQ.user.service.UserServiceImpl;

public class BtnListener implements ActionListener{
	LoginFrame loginFrame;
	public BtnListener (LoginFrame loginFrame){
		this.loginFrame=loginFrame;
	}

	public void actionPerformed(ActionEvent e) {
		String qqNum=loginFrame.txtQqNum.getText();
		String password=new String(loginFrame.txtPassWord.getPassword());
		UserServiceImpl userviceImpl=new UserServiceImpl();
//		int qqnum=0;
//		try {
//			qqnum=Integer.parseInt(qqNum);
//		} catch (Exception e2) {
//			loginFrame.txtQqNum.setText("请填写正确格式QQ号");
//			loginFrame.txtQqNum.select(0, loginFrame.txtQqNum.getText().length());
//		}
		User u=userviceImpl.login(Integer.parseInt(qqNum), password);
				if(u!=null){
					//System.out.println(u.getNickName());
					MainFrame mf=new MainFrame(u);
					loginFrame.dispose();
					loginFrame=null;
				}
	}

}
