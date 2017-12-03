package com.abc.timelycommunication.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.abc.timelycommunication.model.MessageBox;
import com.abc.timelycommunication.model.User;
import com.abc.timelycommunication.view.ServerFrame;

public class ServerFrameListener implements ActionListener {
	private ServerSocket  s;
	private Socket server;
	private ServerFrame serverframe;
	//保存每个登陆账号、对应的输出流
	private Map<String,ObjectOutputStream> allClient=new HashMap<>();
	public ServerFrameListener(ServerFrame serverframe) {
		this.serverframe=serverframe;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		//点击启动服务器按钮
		if(e.getSource()==serverframe.getBtnNewButton()) {
			try {
				s=new ServerSocket(Config.port);
				serverframe.getBtnNewButton().setEnabled(false);//按键不能再用
				serverframe.getBtnNewButton_1().setEnabled(true);
				serverframe.getTextArea().append(new Date().toLocaleString()+":\t服务器启动成功!\r\n");
				new Thread() {
					public void run() {
						while(true) {
							try {
								server=s.accept();
								ObjectOutputStream out=new ObjectOutputStream(server.getOutputStream());
								ObjectInputStream in=new ObjectInputStream(server.getInputStream());
								ClientMessageReciveThread clientmessagerecivethread=new ClientMessageReciveThread(out,in);
								clientmessagerecivethread.start();
							}catch(IOException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
			
			}catch(IOException e1) {
				e1.printStackTrace();
			}
		}
		
		//点击关闭服务器按钮
		if(e.getSource()==serverframe.getBtnNewButton_1()) {
			try {
				server.close();
				s.close();
				serverframe.getBtnNewButton().setEnabled(true);
				serverframe.getBtnNewButton_1().setEnabled(false);
				JOptionPane.showMessageDialog(serverframe, "服务器已经关闭!", "温馨提示", JOptionPane.WARNING_MESSAGE);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
	}
	
	/**
	 * 处理服务端与客户端的通信
	 * @author user
	 *
	 */
	class ClientMessageReciveThread extends Thread {
		private ObjectOutputStream out;
		private ObjectInputStream in;
		public ClientMessageReciveThread(ObjectOutputStream out, ObjectInputStream in) {
			super();
			this.out = out;
			this.in = in;
		}
		public void run() {
			try {
				while(true) {
					MessageBox m=(MessageBox)in.readObject();
					System.out.println(m);
					serverframe.getTextArea().append(new Date().toLocaleString()+":\t客户端["+server.getInetAddress()+"]l连接进来了!\r\n");
					
					if(m.getType().equals("login")) {
						System.out.println("进入检查登陆信息");
						DetermineLoginInformation(m);
					}else if(m.getType().equals("register")){
						System.out.println("进入验证注册信息");
						DetermineRegisterInformation(m);
					}else if(m.getType().equals("textMessage")) {
						System.out.println("进入消息转发");
						TransmitTextMessage(m);
					}else if(m.getType().equals("update")){
						System.out.println("修改数据");
						UpdateInformation(m);
					}
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	/**
	 * 验证登陆信息
	 * @param m
	 */
	public void DetermineLoginInformation(MessageBox m) {
		User loginedUser=DataOperate.login(m.getFrom().getAccount(), m.getFrom().getPassword());
		if(loginedUser!=null) {
			allClient.put(loginedUser.getAccount(), out);
			serverframe.getTextArea().append(new Date().toLocaleString()+":\t客户端["+loginedUser.getUsername()+"]l连接进来了!\r\n");
		}
		MessageBox loginResult=new MessageBox();
		//将账户密码传给MessageBox类的from属性
		loginResult.setFrom(loginedUser);
		loginResult.setType("loginResult");
		try {
			out.writeObject(loginResult);
			out.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 处理注册信息
	 * @param m
	 */
	public void DetermineRegisterInformation(MessageBox m) {
		User registereduser=m.getFrom();
		Boolean registeredresult=DataOperate.register(registereduser);
		MessageBox result=new MessageBox();
		result.setContent(registeredresult.toString());
		result.setType("registeredResult");
		try {
			out.writeObject(result);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改个人信息
	 */
	public void UpdateInformation(MessageBox m) {
		User updateuser=m.getFrom();
		Boolean updateresult=DataOperate.updateInformation(updateuser);
		MessageBox result=new MessageBox();
		result.setContent(updateresult.toString());
		result.setType("updateResult");
		try {
			out.writeObject(result);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 *转发聊天文本消息
	 * @param m
	 */
	public void TransmitTextMessage(MessageBox m) {
		//遍历集合，找到消息接收方对应的输出流
		for(String account:allClient.keySet()) {
			if(account.equals(m.getTo().getAccount())) {
				m.setTime(new Date().toLocaleString());
				try {
					allClient.get(account).writeObject(m);
					allClient.get(account).flush();
					System.out.println("已转发");
				}catch(IOException E) {
					E.printStackTrace();
				}
				break;
			}
		}
	}
	/**
	 * 处理群消息
	 */
	public void DatermineGroupNews(MessageBox m) {
		
	}
	}
}
