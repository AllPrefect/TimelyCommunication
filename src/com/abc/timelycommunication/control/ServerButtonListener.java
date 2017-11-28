package com.abc.timelycommunication.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import com.abc.timelycommunication.model.MessageBox;
import com.abc.timelycommunication.model.User;
import com.abc.timelycommunication.view.LoginFrame;
import com.abc.timelycommunication.view.ServerFrame;

public class ServerButtonListener implements ActionListener {
	private ServerSocket  s;
	private Socket server;
	private ServerFrame serverframe;
	
	public ServerButtonListener(ServerFrame serverframe) {
		this.serverframe=serverframe;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		//�������������
		if(e.getSource()==serverframe.getBtnNewButton()) {
			try {
				s=new ServerSocket(Config.port);
				serverframe.getBtnNewButton().setEnabled(false);//������������
				serverframe.getTextArea().append(new Date().toLocaleString()+":\t�����������ɹ�!\r\n");
				ClientMessageReciveThread clientmessagerecivethread=new ClientMessageReciveThread();
				clientmessagerecivethread.start();
			}catch(IOException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource()==serverframe.getBtnNewButton_1()) {
			System.out.println("�ر�");
		
		
		}
	}
	class ClientMessageReciveThread extends Thread {
		private ObjectOutputStream out;
		public void run() {
			try {
				while(true) {
					server=s.accept();
					System.out.println(server.getInetAddress());
					
					out=new ObjectOutputStream(server.getOutputStream());
					ObjectInputStream in=new ObjectInputStream(server.getInputStream());
					MessageBox m=(MessageBox)in.readObject();
					
					System.out.println("serverButtonlistener"+m);
					serverframe.getTextArea().append(new Date().toLocaleString()+":\t�ͻ���["+m.getFrom().getUsername()+"]l���ӽ�����!\r\n");
					
					if(m.getType().equals("login")) {
						System.out.println("�������½��Ϣ");
						DetermineLoginInformation(m);
					}else if(m.getType().equals("register")){
						System.out.println("������֤ע����Ϣ");
						DetermineRegisterInformation(m);
					}
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	/**
	 * ��֤��½��Ϣ
	 * @param m
	 */
	public void DetermineLoginInformation(MessageBox m) {
		
		System.out.println(m.getFrom().getUsername());
		System.out.println(m.getFrom().getPassword());
		User loginedUser=DataOperate.login(m.getFrom().getUsername(), m.getFrom().getPassword());
		
		System.out.println(loginedUser);
		MessageBox loginResult=new MessageBox();
		loginResult.setFrom(loginedUser);//���˻����봫��MessageBox���from����
		loginResult.setType("loginResult");
		try {
			out.writeObject(loginResult);
			out.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ����ע����Ϣ
	 * @param m
	 */
	public void DetermineRegisterInformation(MessageBox m) {
		System.out.println(m);
		
		User registereduser=m.getFrom();
		Boolean registeredresult=DataOperate.register(registereduser);
		System.out.println(registeredresult);
		
		MessageBox result=new MessageBox();
		result.setContent(registeredresult.toString());
		result.setType("registeredResult");
		try {
			out.writeObject(result);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
}
