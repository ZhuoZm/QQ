package com.QQ.user.view;

import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import com.QQ.entity.User;
import com.QQ.message.service.ServerThread;
import com.QQ.message.view.ChatFrame;
import com.QQ.user.service.UserServiceImpl;
//主窗体，
public class MainFrame extends JFrame {
	public User myself;
	public List<User> friends;
	public HashMap<Integer, ChatFrame>
	chatingFrames=new HashMap<Integer, ChatFrame>();
	
	
	JScrollPane userListPanel=null;
	JTree usersTree=null;
	DefaultTreeModel treeModel=null;
	DefaultMutableTreeNode rootNode=null;
	public MainFrame() {}
	public MainFrame(User u) {
			myself=u;
		
		rootNode = new DefaultMutableTreeNode("我的好友");  
		treeModel = new DefaultTreeModel(rootNode); 
		usersTree=new JTree(treeModel);
		usersTree.getSelectionModel().setSelectionMode 
	     (TreeSelectionModel.SINGLE_TREE_SELECTION);  
		usersTree.setShowsRootHandles(true);
		userListPanel=new JScrollPane(usersTree);
		//构造好友信息
		UserServiceImpl userserviceImpl=new UserServiceImpl();
		friends=userserviceImpl.listFriends(myself.getQqnum());
		for(User friend : friends){		
			DefaultMutableTreeNode node=new DefaultMutableTreeNode(friend.getQqnum()+" "+friend.getNickName());
			rootNode.add(node);
		}
		usersTree.addMouseListener(new TreeMouseListener(this,usersTree,myself));
		
		this.getContentPane().add(userListPanel);
		this.setTitle(myself.getNickName());
		this.setSize(200, 500);
		this.setVisible(true);
		new Thread(new ServerThread(this)).start();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}


