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
//数据持久层
public class UserDaoImpl{
	public User getUser(int qqNum){
		try{
 
			//获取数据库连接
			Connection con=ConnectionUtil.getCon();
			//创建statement
			PreparedStatement pstm=con.prepareStatement(
					"select * from users u where u.qqnum=?");
			//设置参数，针对？占位符
			pstm.setInt(1, qqNum);
			//执行sql语句
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
			//关闭连接
			ConnectionUtil.closeCon(rs, pstm, con);
			return u;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 新增用户信息
	 * @param u
	 * @return
	 */
	//增加
	public boolean saveUser(User u){
		try {
			
			////2.获取数据库链接
			Connection con=ConnectionUtil.getCon();
			//3.构建statment.	增，删，改。查，唯一不同的地方，其他地方一样
				PreparedStatement pstm=con.prepareStatement(
						"insert into users(nickName,passWord,RegitstTime,gender,introduce,ip)"
								//+ ") values('"+u.getNickName()+"')");//别忘记单引号，可以用问号表示,?占位符
				                +"values(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
				pstm.setString(1, u.getNickName());
				pstm.setString(2, u.getPassWord());
				pstm.setString(3, u.getRegitstTime().toLocaleString());
				pstm.setString(4, u.getGender());
				pstm.setString(5, u.getIntroduce());
				pstm.setString(6, u.getIp());
			//4.用来在执行sql语句
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
		 * 按用户qq号和密码查询用户
		 * @param qqNum
		 * @param password
		 * @return
		 */
	//查询
	public User findByQqNumAndPassword(int qqNum,String password){
		try {
			 
			//2.获取数据库链接
			Connection con=ConnectionUtil.getCon();
			//3.构建statment.	增，删，改。查，唯一不同的地方，其他地方一样
				PreparedStatement pstm=con.prepareStatement(
						"select * from users u where u.qqNum=? and u.password=?"
						);
				pstm.setInt(1, qqNum);
				pstm.setString(2,password);
			//4.用来在执行sql语句
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
			//5，关闭数据库链接
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
	
	
	 



