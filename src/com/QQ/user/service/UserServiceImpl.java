package com.QQ.user.service;

import java.util.List;

import com.QQ.entity.User;
import com.QQ.user.dao.UserDaoImpl;
import com.QQ.util.IpUtil;

//ÒµÎñÂß¼­²ã
public class UserServiceImpl {
	public boolean regist(User u){
		UserDaoImpl userDaoImpl=new UserDaoImpl();
		return userDaoImpl.saveUser(u);
	}
	public User login(int qqNum,String Password){
		UserDaoImpl userDaoImpl=new UserDaoImpl();
		User u=userDaoImpl.findByQqNumAndPassword(qqNum,Password);
		if(u!=null){
			String ip = IpUtil.getLocalHostAddress();
			userDaoImpl.updateIp(qqNum, ip);
			u.setIp(ip);
			return u;
		}else{
			return null;
		}
}

	public List<User> listFriends(int qqNum){
		UserDaoImpl userDaoImpl=new UserDaoImpl();
		return userDaoImpl.findFriendByQqNum(qqNum);
	}
//	public boolean findSaveUser(int qqNum,String password){
//		UserDaoImpl userDaoImpl=new UserDaoImpl();
//		return userDaoImpl.findSaveUser(qqNum,password);
//	}
	public User listByQqNum(int qqNum){
		UserDaoImpl userDaoImpl=new UserDaoImpl();
		return userDaoImpl.getUser(qqNum);
	}
}
