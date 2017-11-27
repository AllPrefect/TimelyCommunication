package com.abc.timelycommunication.control;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
		if(e.getSource()==loginframe.getLogin()) {
	
			 //����֤
			 String yourInputUsername=loginframe.getUsername().getSelectedItem().toString().trim();
			 String yourInputPassword=loginframe.getPassword().getText().toString().trim();
			 //trim��String��ķ�����ȥ�ַ�����ǰ��հ׷�
			 if(yourInputUsername.length()<1) {
				JOptionPane.showMessageDialog(loginframe, "�û������Ȳ���!","��ܰ��ʾ",JOptionPane.ERROR_MESSAGE);
			 	loginframe.getUsername().requestFocus();
				return ;
			 }else {
				 if(yourInputPassword.length()<2) {
						JOptionPane.showMessageDialog(loginframe, "���볤�Ȳ���!","��ܰ��ʾ",JOptionPane.ERROR_MESSAGE);
						loginframe.requestFocus();
						return ;
			 }else {
			//�����������������
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
			 //�����������Ϣ���͸����������÷�����У�����ǵ��˺ź������Ƿ�ɹ�
				 try {
				//��ϢҪ��װ�ɶ������ԣ�Ҫ������ϢҪ��Object��
				//��Ϊ���ǽ���Ϣ��װ���ض������ͣ����ԣ�ÿ���ٸ�������������Ϣʱ����Ҫ��װ���ض�����Ϣ����ſ���
					MessageBox  loginMessage=new MessageBox();
					User willLoginUser=new User(yourInputUsername,yourInputPassword);
					loginMessage.setFrom(willLoginUser);
					loginMessage.setType("login"); 
					
					System.out.println(loginMessage);
					out.writeObject(loginMessage);
					out.flush();
				//��ȡ�������ط��ĵ�½�����Ϣ
					MessageBox  result=(MessageBox)in.readObject();
					if(result.getFrom()==null) {
						JOptionPane.showMessageDialog(loginframe, "��½ʧ��,�����û���������!","��ܰ��ʾ",JOptionPane.ERROR_MESSAGE);
					}else
					{
						User u=result.getFrom();
						MainFrame  m=new MainFrame(u);
						m.setVisible(true);
						loginframe.setVisible(false);
					}
				 }catch(Exception e1) {
					 e1.printStackTrace();
				 }
			 }
			 }
		}
		if(e.getSource()==loginframe.getRegister()) {
			new RegisterFrame();
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
