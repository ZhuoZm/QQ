package com.QQ.message.dao;//Êý¾Ý³Ö¾Ã²ã

import java.sql.Connection;
import java.sql.ResultSet;

import javax.naming.spi.DirStateFactory.Result;

import com.QQ.entity.Message;
import com.QQ.util.ConnectionUtil;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class MessageDaoImpl {
	
	public int saveMessage(Message message){
		Connection con=null;
		PreparedStatement pstm=null;
		try {
			con=ConnectionUtil.getCon();
			pstm=(PreparedStatement) con.prepareStatement(
					"insert into message(content,sender,receiver,sendTime,state"
					+ "values(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1,message.getContent());
			pstm.setInt(2,message.getSender());
			pstm.setInt(3, message.getReceiver());
			pstm.setString(4,message.getSendTime().toLocaleString());
			pstm.setInt(5, message.getState());
			
			pstm.executeUpdate();
			ResultSet rs=pstm.getGeneratedKeys();
			int key=0;
			if(rs.next()){
				key=rs.getInt(1);
			}
			return key;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally{
			ConnectionUtil.closeCon(null, pstm, con);
			
		}
		
	}

}
