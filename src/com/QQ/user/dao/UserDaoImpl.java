package com.QQ.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.QQ.entity.User;
import com.QQ.util.ConnectionUtil;
import com.mysql.jdbc.Statement;
//���ݳ־ò�
public class UserDaoImpl{
	public User getUser(int qqNum){
		try{
 
			//��ȡ���ݿ�����
			Connection con=ConnectionUtil.getCon();
			//����statement
			PreparedStatement pstm=con.prepareStatement(
					"select * from users u where u.qqnum=?");
			//���ò�������ԣ�ռλ��
			pstm.setInt(1, qqNum);
			//ִ��sql���
			ResultSet rs=pstm.executeQuery();
			User u=null;
			while(rs.next()){
				u=new User();
				u.setQqnum(rs.getInt(1));
				u.setNickName(rs.getString(2));
				u.setPassWord(rs.getString(3));
				u.setRegitstTime(rs.getDate(4));
				u.setGender(rs.getString(5));
				u.setIntroduce(rs.getString(6));
				u.setIp(rs.getString(7));
			}
			//�ر�����
			ConnectionUtil.closeCon(rs, pstm, con);
			return u;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * �����û���Ϣ
	 * @param u
	 * @return
	 */
	//����
	public boolean saveUser(User u){
		try {
			
			////2.��ȡ���ݿ�����
			Connection con=ConnectionUtil.getCon();
			//3.����statment.	����ɾ���ġ��飬Ψһ��ͬ�ĵط��������ط�һ��
				PreparedStatement pstm=con.prepareStatement(
						"insert into users(nickName,passWord,RegitstTime,gender,introduce,ip)"
								//+ ") values('"+u.getNickName()+"')");//�����ǵ����ţ��������ʺű�ʾ,?ռλ��
				                +"values(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
				pstm.setString(1, u.getNickName());
				pstm.setString(2, u.getPassWord());
				pstm.setString(3, u.getRegitstTime().toLocaleString());
				pstm.setString(4, u.getGender());
				pstm.setString(5, u.getIntroduce());
				pstm.setString(6, u.getIp());
			//4.������ִ��sql���
				int count=pstm.executeUpdate();
				ResultSet rs=pstm.getGeneratedKeys();
			 if(rs.next())
				System.out.println(rs.getInt(1));
			 ConnectionUtil.closeCon(rs, pstm, con);
			 	if(count>0)
			 		return true;
			 	else
			 		return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
		public boolean updatePassWord(int qqNum, String password){
			Connection con=null;
			PreparedStatement pstm=null;
			try{
				List<User> list=new ArrayList<User>();
				con=ConnectionUtil.getCon();
				pstm=con.prepareStatement(
						"update users set password=? where qqNum=?");
				pstm.setString(1, password);
				pstm.setInt(2, qqNum);
				int count=pstm.executeUpdate();
				if(count>0)
					return true;
				else
					return false;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}finally{
				ConnectionUtil.closeCon(null, pstm, con);
			}
		}
	
		public void updateIp(int qqNum,String ip){
			Connection con=null;
			PreparedStatement pstm=null;
			//ResultSet rs=null;
			try {
				con=ConnectionUtil.getCon();
				pstm=con.prepareStatement(
						"update users set ip=? where qqNum=?");
				pstm.setString(1, ip);
				pstm.setInt(2, qqNum);
				pstm.executeUpdate();
				int count=pstm.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				ConnectionUtil.closeCon(null, pstm, con);
			}
		}
		/**
		 * ���û�qq�ź������ѯ�û�
		 * @param qqNum
		 * @param password
		 * @return
		 */
	//��ѯ
	public User findByQqNumAndPassword(int qqNum,String password){
		try {
			 
			//2.��ȡ���ݿ�����
			Connection con=ConnectionUtil.getCon();
			//3.����statment.	����ɾ���ġ��飬Ψһ��ͬ�ĵط��������ط�һ��
				PreparedStatement pstm=con.prepareStatement(
						"select * from users u where u.qqNum=? and u.password=?"
						);
				pstm.setInt(1, qqNum);
				pstm.setString(2,password);
			//4.������ִ��sql���
				ResultSet rs=pstm.executeQuery();
				User u=null;
				while(rs.next()){
					u=new User();
					u.setQqnum(rs.getInt(1));
					u.setNickName(rs.getString(2));
					u.setPassWord(rs.getString(3));
					u.setGender(rs.getString(4));
					u.setRegitstTime(rs.getDate(5));
					u.setIntroduce(rs.getString(6));
					u.setIp(rs.getString(7));
				}
			//5���ر����ݿ�����
				ConnectionUtil.closeCon(rs, pstm, con);
				return u;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
   }
	public List<User> findFriendByQqNum(int qqNum){
		
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			List<User> list=new ArrayList<User>();
			con=ConnectionUtil.getCon();
			pstm=con.prepareStatement(
					"select * from users u where u.qqnum<>?");
			pstm.setInt(1, qqNum);
			rs=pstm.executeQuery();
			
			while(rs.next()){
				User u=new User();
				u.setQqnum(rs.getInt(1));
				u.setNickName(rs.getString(2));
				u.setPassWord(rs.getString(3));
				u.setGender(rs.getString(4));
				u.setRegitstTime(rs.getDate(5));
				u.setIntroduce(rs.getString(6));
				u.setIp(rs.getString(7));
				
				list.add(u);
			}
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			ConnectionUtil.closeCon(rs, pstm, con);
		}
}
}
//	public boolean findSaveUser(int qqNum,String password){
//	List<User> list=new ArrayList<User>();
//	Connection con=null;
//	PreparedStatement pstm=null;
//	ResultSet rs=null;
//	try {
//		con=ConnectionUtil.getCon();
//		pstm=con.prepareStatement(
//				"update users set password=? where qqNum");
//		pstm.setString(1, password);
//		pstm.setInt(2, qqNum);
//		int count=pstm.executeUpdate();
//		
//		if(count>0)
//			return true;
//		else
//			return false;
//		
//	} catch (Exception e) {
//		e.printStackTrace();
//		return false;
//	}finally{
//		ConnectionUtil.closeCon(null, pstm, con);
//	}
//}
	
	
	 



