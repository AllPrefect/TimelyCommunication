package com.abc.timelycommunication.control;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

import com.abc.timelycommunication.model.MessageBox;
import com.abc.timelycommunication.model.User;
import com.abc.timelycommunication.view.LoginFrame;
import com.abc.timelycommunication.view.MainFrame;
import com.abc.timelycommunication.view.RegisterFrame;

public class LoginFrameListener implements MouseListener {
	private Socket  client;
	private ObjectOutputStream  out;
	private  ObjectInputStream  in;
	private LoginFrame loginframe;
	public LoginFrameListener(LoginFrame loginframe) {
		this.loginframe=loginframe;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		//点击登陆按钮
		if(e.getSource()==loginframe.getLogin()) {
			 /**
			  * 表单信息验证
			  */
			 String yourInputaccount=loginframe.getAccount().getSelectedItem().toString().trim();
			 String yourInputPassword=loginframe.getPassword().getText().toString();
			 //trim是String类的方法，去字符串的前后空白符
			 if(yourInputaccount.length()<6) {
				JOptionPane.showMessageDialog(loginframe, "请输入六位以上正确账号!","温馨提示",JOptionPane.ERROR_MESSAGE);
			 	loginframe.getAccount().requestFocus();
				return ;
			 }else {
				 if(yourInputPassword.length()<6) {
					JOptionPane.showMessageDialog(loginframe, "请输入六位以上正确密码!","温馨提示",JOptionPane.ERROR_MESSAGE);
					loginframe.requestFocus();
					return ;
				 }else {
			 /**
			  * 建立与服务器的链接
			  */
					 try {
						 if(client==null) {
							 client=new Socket(Config.serverIP,Config.port);
							 out=new ObjectOutputStream(client.getOutputStream());
							 in=new ObjectInputStream(client.getInputStream());
						 }
					 }catch(Exception e1) {
						 e1.printStackTrace();
						 JOptionPane.showMessageDialog(loginframe, "无法连接服务器，请检查网络!","温馨提示",JOptionPane.ERROR_MESSAGE);
					 }
			 /**
			  * 用输出流将消息发送给服务器，检验账号密码
			  */
				 try {
					 //消息要封装成对象类型，所以要传递消息要用Object流
					 //因为我们将消息封装成特定的类型，所以每次再给服务器发送消息时，都要封装成特定的消息对象才可以
					MessageBox  loginMessage=new MessageBox();
					User willLoginUser=new User(yourInputaccount,yourInputPassword);
					loginMessage.setFrom(willLoginUser);
					loginMessage.setType("login"); 
					
					out.writeObject(loginMessage);
					out.flush();
			/**
			 * 读取服务器回发的登陆结果消息
			 */
					MessageBox  loginresult=(MessageBox)in.readObject();
					if(loginresult.getFrom()==null) {
						JOptionPane.showMessageDialog(loginframe, "用户名密码错误!","温馨提示",JOptionPane.ERROR_MESSAGE);
					}else{
						User u=loginresult.getFrom();
						//同时将输入输出流传给MainFrame类
						MainFrame  m=new MainFrame(u,in,out);
						m.setVisible(true);
						loginframe.setVisible(false);
					}
				 }catch(Exception e1) {
					 e1.printStackTrace();
				 }
				 }
			 }
		}
		
		//点击注册按钮
		if(e.getSource()==loginframe.getRegister()) {
			try {
				 if(client==null) {
					 client=new Socket(Config.serverIP,Config.port);
					 out=new ObjectOutputStream(client.getOutputStream());
					 in=new ObjectInputStream(client.getInputStream());
					 new RegisterFrame(out,in);
					 loginframe.setVisible(false);
				 }
			 }catch(Exception e1) {
				 e1.printStackTrace();
				 JOptionPane.showMessageDialog(loginframe, "无法连接服务器，请检查网络!","温馨提示",JOptionPane.ERROR_MESSAGE);
			 }
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		loginframe.getLogin().setBackground(Color.magenta);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		loginframe.getLogin().setBackground(Color.LIGHT_GRAY);
	}
	

}
