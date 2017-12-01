package com.abc.timelycommunication.control;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import com.abc.timelycommunication.model.MessageBox;
import com.abc.timelycommunication.model.User;
import com.abc.timelycommunication.view.ChattingFrame;
import com.abc.timelycommunication.view.MainFrame;
import com.abc.timelycommunication.view.PersonalInformationFrame;

public class MainFrameListener implements MouseListener{
	//记录正在聊天的好友账号、聊天窗口对象
	private Map<String,ChattingFrame> allFrames=new HashMap<>();
	//在本类定义一个ObjectIn,ObjectOut来接受登录界面已经建立好的两个流
	private ObjectInputStream  in;
	private ObjectOutputStream  out;
	private User user;
	private PersonalInformationFrame p;
	private MainFrame mainframe;
	public  MainFrameListener(MainFrame mainframe,User user,ObjectOutputStream out,ObjectInputStream in) {
		this.mainframe=mainframe;
		this.user=user;
		this.out=out;
		this.in=in;
	}
		
	@Override
	public void mouseClicked(MouseEvent e) {
		/**
		 * 点击好友列表
		 */
		if(e.getSource()==mainframe.getTree()) {
		//鼠标左键点击两次
		if(e.getButton()==1&&e.getClickCount()==2) {
			//获取点击路径
			TreePath path=mainframe.getTree().getSelectionPath();
			DefaultMutableTreeNode lastNode=(DefaultMutableTreeNode)path.getLastPathComponent();
			if(lastNode.isLeaf()) {
				String friendusername=lastNode.toString();
				//获取聊天对象账户
				String friendaccount=friendusername.substring(friendusername.lastIndexOf("[")+1,friendusername.length()-1);
				
				User your=new User();
				your.setAccount(friendaccount);
				for(String account:allFrames.keySet()) {
					if(account.equals(friendaccount)) {
						//设置对应的聊天窗口可见
						allFrames.get(account).setVisible(true);
						return;
					}
				}
				ChattingFrame chatting=new ChattingFrame(user,your,out,in);
				chatting.setVisible(true);
				allFrames.put(friendaccount, chatting);		
			}
		}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource()==mainframe.getHeadportrait()) {
		p=new PersonalInformationFrame();
		p.setVisible(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource()==mainframe.getHeadportrait()) {
		p.setVisible(false);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		//mainframe.getSearchButton().setBackground(Color.magenta);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}
	/**
	 * 接收聊天信息
	 * @author user
	 *
	 */
	public class MessageReceiveThread extends Thread{
		@Override
		public void run() {
			MessageBox receivedMessage=null;
			try {
				A:while((receivedMessage=(MessageBox)in.readObject())!=null) {
					for(String friendaccount:allFrames.keySet()) {
						if(friendaccount.equals(receivedMessage.getFrom().getAccount())) {
							if(receivedMessage.getType().equals("shakeMessage")) {
								
								allFrames.get(friendaccount).setVisible(true);
								allFrames.get(friendaccount).shakeWindow();
							}else {
								allFrames.get(friendaccount).getTextArea().append(receivedMessage.getFrom().getUsername()+"  :  "+receivedMessage.getTime()+"\t\r\n"+receivedMessage.getContent()+"\r\n");
								allFrames.get(friendaccount).setVisible(true);
							}
							continue A;
						}
					}
					ChattingFrame chat=new ChattingFrame(user,receivedMessage.getFrom(), out, in);
				}
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
