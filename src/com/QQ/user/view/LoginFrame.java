package com.QQ.user.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
//QQ��½
public class LoginFrame extends JFrame{
	JLabel lblQqNum;
	JLabel lblPassword;
	JTextField txtQqNum;
	JPasswordField txtPassWord;
	JButton btnLogin;
	JButton btnCancle;
	public LoginFrame(){
		lblQqNum=new JLabel("QQ�ţ�");
		lblPassword=new JLabel("���룺");
		txtQqNum= new JTextField();
		txtPassWord=new JPasswordField();
		btnLogin=new JButton("ȷ��");
		btnCancle=new JButton("ȡ��");
		
		//�Ѳ��ַŵ�������,�����ǵ�¼����
		this.getContentPane().setLayout(null);
		this.getContentPane().add(lblQqNum);
		lblQqNum.setBounds(60,20,160,25);//x,y.�ı�����
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
		//ע������߰�ť
		btnLogin.addActionListener(new BtnListener(this));
				
		
		this.setTitle("QQ��½");
		this.setSize(300, 200);
		this.setVisible(true);
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
