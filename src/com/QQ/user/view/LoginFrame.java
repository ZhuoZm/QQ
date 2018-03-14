package com.QQ.user.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
//QQ登陆
public class LoginFrame extends JFrame{
	JLabel lblQqNum;
	JLabel lblPassword;
	JTextField txtQqNum;
	JPasswordField txtPassWord;
	JButton btnLogin;
	JButton btnCancle;
	public LoginFrame(){
		lblQqNum=new JLabel("QQ号：");
		lblPassword=new JLabel("密码：");
		txtQqNum= new JTextField();
		txtPassWord=new JPasswordField();
		btnLogin=new JButton("确定");
		btnCancle=new JButton("取消");
		
		//把布局放到界面上,出来是登录界面
		this.getContentPane().setLayout(null);
		this.getContentPane().add(lblQqNum);
		lblQqNum.setBounds(60,20,160,25);//x,y.文本框宽高
		this.getContentPane().add(lblPassword);
		lblPassword.setBounds(60,55,160,25);
		this.getContentPane().add(txtQqNum);
		txtQqNum.setBounds(120,20,100,25);
		this.getContentPane().add(txtPassWord);
		txtPassWord.setBounds(120,55,100,25);
		this.getContentPane().add(btnLogin);
		btnLogin.setBounds(60,100,60,20);
		this.getContentPane().add(btnCancle);
		btnCancle.setBounds(130,100,60,20);
		//注册监听者按钮
		btnLogin.addActionListener(new BtnListener(this));
				
		
		this.setTitle("QQ登陆");
		this.setSize(300, 200);
		this.setVisible(true);
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
