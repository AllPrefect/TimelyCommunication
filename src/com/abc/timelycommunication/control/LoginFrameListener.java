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
		//�����½��ť
		if(e.getSource()==loginframe.getLogin()) {
			 /**
			  * ����Ϣ��֤
			  */
			 String yourInputaccount=loginframe.getAccount().getSelectedItem().toString().trim();
			 String yourInputPassword=loginframe.getPassword().getText().toString();
			 //trim��String��ķ�����ȥ�ַ�����ǰ��հ׷�
			 if(yourInputaccount.length()<6) {
				JOptionPane.showMessageDialog(loginframe, "��������λ������ȷ�˺�!","��ܰ��ʾ",JOptionPane.ERROR_MESSAGE);
			 	loginframe.getAccount().requestFocus();
				return ;
			 }else {
				 if(yourInputPassword.length()<6) {
					JOptionPane.showMessageDialog(loginframe, "��������λ������ȷ����!","��ܰ��ʾ",JOptionPane.ERROR_MESSAGE);
					loginframe.requestFocus();
					return ;
				 }else {
			 /**
			  * �����������������
			  */
					 try {
						 if(client==null) {
							 client=new Socket(Config.serverIP,Config.port);
							 out=new ObjectOutputStream(client.getOutputStream());
							 in=new ObjectInputStream(client.getInputStream());
						 }
					 }catch(Exception e1) {
						 e1.printStackTrace();
						 JOptionPane.showMessageDialog(loginframe, "�޷����ӷ���������������!","��ܰ��ʾ",JOptionPane.ERROR_MESSAGE);
					 }
			 /**
			  * �����������Ϣ���͸��������������˺�����
			  */
				 try {
					 //��ϢҪ��װ�ɶ������ͣ�����Ҫ������ϢҪ��Object��
					 //��Ϊ���ǽ���Ϣ��װ���ض������ͣ�����ÿ���ٸ�������������Ϣʱ����Ҫ��װ���ض�����Ϣ����ſ���
					MessageBox  loginMessage=new MessageBox();
					User willLoginUser=new User(yourInputaccount,yourInputPassword);
					loginMessage.setFrom(willLoginUser);
					loginMessage.setType("login"); 
					
					out.writeObject(loginMessage);
					out.flush();
			/**
			 * ��ȡ�������ط��ĵ�½�����Ϣ
			 */
					MessageBox  loginresult=(MessageBox)in.readObject();
					if(loginresult.getFrom()==null) {
						JOptionPane.showMessageDialog(loginframe, "�û����������!","��ܰ��ʾ",JOptionPane.ERROR_MESSAGE);
					}else{
						User u=loginresult.getFrom();
						//ͬʱ���������������MainFrame��
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
		
		//���ע�ᰴť
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
				 JOptionPane.showMessageDialog(loginframe, "�޷����ӷ���������������!","��ܰ��ʾ",JOptionPane.ERROR_MESSAGE);
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
