package com.abc.timelycommunication.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.abc.timelycommunication.control.MainFrameListener;
import com.abc.timelycommunication.model.User;

public class MainFrame extends JFrame {
	private JTree tree;
	private JLabel headPicture,headportrait,username;
	private JTextField searchList;
	private JButton searchButton;
	//接收登陆界面传来的用户对象
	private User user;
	
	public JLabel getHeadportrait() {
		return headportrait;
	}
	public JLabel getUsername() {
		return username;
	}
	public JButton getSearchButton() {
		return searchButton;
	}
	public JTree getTree() {
		return tree;
	}
	public MainFrame(User user) {
		this.user=user;
		setSize(300, 600);
		setVisible(true);
		setLayout(null);//不采用任何默认布局
		setTitle("Timely......");
		setLocation(100,50);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().createImage("resource/pictures/logo.jpg"));
		homeCompoment();
		paintComponents(getGraphics());
		paintAll(getGraphics());
	}
	public void homeCompoment() {
		headPicture=new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage("resource/pictures/mainframe.gif")));
		headPicture.setSize(300,125);
		headPicture.setLocation(0,0);
		this.add(headPicture);
		
		headportrait=new JLabel();//头像
		headportrait.setBounds(200,10,70,90);
		headportrait.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(user.getImagepath()).getScaledInstance(80, 100, Image.SCALE_DEFAULT)));
		headportrait.addMouseListener(new MainFrameListener(MainFrame.this));
		headPicture.add(headportrait);
		
		username=new JLabel(user.getUsername());
		username.setBounds(210,103,70,20);
		username.addMouseListener(new MainFrameListener(MainFrame.this));
		headPicture.add(username);
		
		searchList=new JTextField(" 搜索...");
		searchList.setBounds(5, 132, 235, 20);
		searchList.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		searchList.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(searchList.getText().length()==0)
					searchList.setText(" 搜索...");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(searchList.getText().equals(" 搜索..."))
					searchList.setText("");
			}
		});
		this.add(searchList);
		
		searchButton=new JButton("の");
		searchButton.addMouseListener(new MainFrameListener(MainFrame.this));
		searchButton.setBounds(245, 132, 35, 20);
		this.add(searchButton);
		/**
		 * 
		 */
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 160, 260, 400);
		this.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("消息", null, panel, null);
		
		JPanel panel_1=new JPanel();
		tabbedPane.addTab("联系人",null,panel_1,null);
		panel_1.setLayout(new BorderLayout(0,0));
		//定义根节点
		DefaultMutableTreeNode  root=new DefaultMutableTreeNode("root");
		Map<String,HashSet<User>> allfriends=user.getFriends();
		//set集合获取所有分组名
		if(allfriends!=null) {
		Set<String> allgroupsname=allfriends.keySet();
		for(String groupname:allgroupsname) {
			//每个好友分组的节点
			DefaultMutableTreeNode  group=new DefaultMutableTreeNode(groupname);
			//根据键值遍历对应的好友
			HashSet<User>  friendsOfGroup=allfriends.get(groupname);
			for(User user:friendsOfGroup) {
				DefaultMutableTreeNode  friend=new DefaultMutableTreeNode(user.getUsername());
				group.add(friend);
			}
			root.add(group);
		}
		}
		tree = new JTree(root);
		tree.addMouseListener(new MainFrameListener(MainFrame.this));
		JScrollPane scrollPane= new JScrollPane(tree);
		tree.setRootVisible(false);
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("群组", null, panel_2, null);
	}
}
