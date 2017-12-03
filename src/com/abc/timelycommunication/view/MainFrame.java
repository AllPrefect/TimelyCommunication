package com.abc.timelycommunication.view;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
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
import com.abc.timelycommunication.control.MainFrameListener.MessageReceiveThread;
import com.abc.timelycommunication.model.MessageBox;
import com.abc.timelycommunication.model.User;

public class MainFrame extends JFrame {
	//记录正在聊天的好友账号、聊天窗口对象
	private static Map<String,ChattingFrame> allFrames=new HashMap<>();
	private MainFrameListener mianframelistener;
	private JTree tree;
	private JLabel headPicture,headportrait,username;
	private JTextField searchList;
	private JButton searchButton;
	//接收登陆界面传来的用户对象
	private User user;
	private ObjectInputStream  in;
	private ObjectOutputStream  out;
	private SystemTray sysTray;
	private TrayIcon icon;
	
	public SystemTray getSysTray() {
		return sysTray;
	}
	public TrayIcon getIcon() {
		return icon;
	}
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
	
	public static Map<String, ChattingFrame> getAllFrames() {
		return allFrames;
	}
	public MainFrame(User user,ObjectInputStream in,ObjectOutputStream out) {
		this.in=in;
		this.out=out;
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
		
		/**
		 *任务栏图标
		 */
		//可以对操作系统任务栏操作的java对象
		sysTray=SystemTray.getSystemTray();
		icon=new TrayIcon(Toolkit.getDefaultToolkit().createImage("resource/pictures/logo.jpg"));
		icon.setImageAutoSize(true);
		try {
			sysTray.add(icon);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		icon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==1) {
					
				}
			}
		});
	}
	public void homeCompoment() {
		mianframelistener=new MainFrameListener(MainFrame.this,user,in,out);
		
		headPicture=new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage("resource/pictures/mainframe.gif")));
		headPicture.setSize(300,125);
		headPicture.setLocation(0,0);
		this.add(headPicture);
		
		headportrait=new JLabel();//头像
		headportrait.setBounds(200,10,70,90);
		headportrait.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(user.getImagepath()).getScaledInstance(80, 100, Image.SCALE_DEFAULT)));
		headportrait.addMouseListener(mianframelistener);
		headPicture.add(headportrait);
		
		username=new JLabel(user.getUsername());
		username.setBounds(210,103,70,20);
		username.addMouseListener(mianframelistener);
		headPicture.add(username);
		
		searchList=new JTextField(" 搜索...");
		searchList.setBounds(5, 132, 235, 20);
		searchList.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		searchList.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(searchList.getText().length()==0)
					searchList.setText(" 搜索...");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(searchList.getText().equals(" 搜索..."))
					searchList.setText("");
			}
		});
		this.add(searchList);
		
		searchButton=new JButton("の");
		searchButton.addMouseListener(mianframelistener);
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
						DefaultMutableTreeNode  friend=new DefaultMutableTreeNode(user.getUsername()+"["+user.getAccount()+"]");
						group.add(friend);
			}
			root.add(group);
		}
		}
		tree = new JTree(root);
		tree.addMouseListener(mianframelistener);
		JScrollPane scrollPane= new JScrollPane(tree);
		tree.setRootVisible(false);
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("群组", null, panel_2, null);
		//时刻接收服务器传来的消息
		MessageReceiveThread t=new MainFrameListener(MainFrame.this,user,in,out).new MessageReceiveThread();
		t.start();
		
	}
	
}
