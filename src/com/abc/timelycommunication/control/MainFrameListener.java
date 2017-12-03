package com.abc.timelycommunication.control;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import com.abc.timelycommunication.model.MessageBox;
import com.abc.timelycommunication.model.User;
import com.abc.timelycommunication.view.ChattingFrame;
import com.abc.timelycommunication.view.MainFrame;
import com.abc.timelycommunication.view.PersonalInformationFrame;

public class MainFrameListener implements MouseListener{
	//在本类定义一个ObjectIn,ObjectOut来接受登录界面已经建立好的两个流
	private ObjectInputStream  in;
	private ObjectOutputStream  out;
	private User user;
	private PersonalInformationFrame p;
	private MainFrame mainframe;
	
	public  MainFrameListener(MainFrame mainframe,User user,ObjectInputStream in,ObjectOutputStream out) {
		this.mainframe=mainframe;
		this.user=user;
		this.out=out;
		this.in=in;
	}
		
	@Override
	public void mouseClicked(MouseEvent e) {
		/**
		 * 点击好友列表,发送消息
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
				String friendname=friendusername.substring(0,friendusername.lastIndexOf("["));
				User your=new User();
				your.setAccount(friendaccount);
				your.setUsername(friendname);
				for(String account:MainFrame.getAllFrames().keySet()) {
					if(account.equals(friendaccount)) {
						//设置对应的聊天窗口可见
						MainFrame.getAllFrames().get(account).setVisible(true);
						return;
					}
				}
				ChattingFrame chatting=new ChattingFrame(user,your,in,out);
				chatting.setVisible(true);
				MainFrame.getAllFrames().put(friendaccount, chatting);		
			}
		}
		}
		
		/**
		 * 点击头像，修改个人信息
		 */
		if(e.getSource()==mainframe.getHeadportrait()) {
			if(e.getButton()==1&&e.getClickCount()==2) {
				p=new PersonalInformationFrame(user,out,in);
				p.setTitle("修改个人信息中...");
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==mainframe.getHeadportrait()||e.getSource()==mainframe.getUsername()) {
			p=new PersonalInformationFrame(user,out,in);
			p.setVisible(true);
			}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource()==mainframe.getHeadportrait()||e.getSource()==mainframe.getUsername()) {
			p.setVisible(false);
			}
		
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
					
					
					
					for(String friendaccount:MainFrame.getAllFrames().keySet()) {
						if(friendaccount.equals(receivedMessage.getFrom().getAccount())) {
							if(receivedMessage.getContent().equals("shakeMessage")) {
								MainFrame.getAllFrames().get(friendaccount).setVisible(true);
								MainFrame.getAllFrames().get(friendaccount).shakeWindow();
							}else {
								MainFrame.getAllFrames().get(friendaccount).getTextArea().append(receivedMessage.getFrom().getUsername()+"  :  ["+receivedMessage.getTime()+"]\t\r\n"+receivedMessage.getContent()+"\r\n");
								MainFrame.getAllFrames().get(friendaccount).setVisible(true);
							}
							continue A;
						}
					}
					ChattingFrame chat=new ChattingFrame(user,receivedMessage.getFrom(), in, out);
					//计时器
					Timer timer=new Timer();
					timer.schedule(new TimerTask() {
						int num=1;
						public void run() {
							mainframe.getIcon().setImage(Toolkit.getDefaultToolkit().createImage("resource/pictures/"+(num%2==0?"logo.jpg":"logo_1.png")));
							num++;
						}
					}, 0,500);
					if(receivedMessage.getContent().equals("shakeMessage")) {
						chat.setVisible(true);
						chat.shakeWindow();
					}else {
						chat.getTextArea().append(receivedMessage.getFrom().getUsername()+" : "+receivedMessage.getTime()+"\t\r\n"+receivedMessage.getContent()+"\r\n");
						chat.setVisible(true);
					}
					MainFrame.getAllFrames().put(receivedMessage.getFrom().getAccount(), chat);
				}
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
