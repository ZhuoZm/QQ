package com.QQ.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


public class ConnectionUtil {
	static{
		
	//1.º”‘ÿ«˝∂Ø¿‡
	try{
		Class.forName("com.mysql.jdbc.Driver");
	}catch(ClassNotFoundException e){
		e.printStackTrace();
	}
}
	public static Connection getCon(){
		try {
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=UTF-8","root","");
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void closeCon(ResultSet rs,PreparedStatement pstm,Connection con){
		try{
			if(rs!=null)
				rs.close();
			if(pstm!=null)
				pstm.close();
			if(con!=null)
				con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
