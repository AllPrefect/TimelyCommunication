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
	//����ÿ����½�˺š���Ӧ�������
	private Map<String,ObjectOutputStream> allClient=new HashMap<>();
	public ServerFrameListener(ServerFrame serverframe) {
		this.serverframe=serverframe;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		//���������������ť
		if(e.getSource()==serverframe.getBtnNewButton()) {
			try {
				s=new ServerSocket(Config.port);
				serverframe.getBtnNewButton().setEnabled(false);//������������
				serverframe.getBtnNewButton_1().setEnabled(true);
				serverframe.getTextArea().append(new Date().toLocaleString()+":\t�����������ɹ�!\r\n");
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
		
		//����رշ�������ť
		if(e.getSource()==serverframe.getBtnNewButton_1()) {
			try {
				server.close();
				s.close();
				serverframe.getBtnNewButton().setEnabled(true);
				serverframe.getBtnNewButton_1().setEnabled(false);
				JOptionPane.showMessageDialog(serverframe, "�������Ѿ��ر�!", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
	}
	
	/**
	 * ����������ͻ��˵�ͨ��
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
					serverframe.getTextArea().append(new Date().toLocaleString()+":\t�ͻ���["+server.getInetAddress()+"]l���ӽ�����!\r\n");
					
					if(m.getType().equals("login")) {
						System.out.println("�������½��Ϣ");
						DetermineLoginInformation(m);
					}else if(m.getType().equals("register")){
						System.out.println("������֤ע����Ϣ");
						DetermineRegisterInformation(m);
					}else if(m.getType().equals("textMessage")) {
						System.out.println("������Ϣת��");
						TransmitTextMessage(m);
					}else if(m.getType().equals("update")){
						System.out.println("�޸�����");
						UpdateInformation(m);
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
		User loginedUser=DataOperate.login(m.getFrom().getAccount(), m.getFrom().getPassword());
		if(loginedUser!=null) {
			allClient.put(loginedUser.getAccount(), out);
			serverframe.getTextArea().append(new Date().toLocaleString()+":\t�ͻ���["+loginedUser.getUsername()+"]l���ӽ�����!\r\n");
		}
		MessageBox loginResult=new MessageBox();
		//���˻����봫��MessageBox���from����
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
	 * ����ע����Ϣ
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
	 * �޸ĸ�����Ϣ
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
	 *ת�������ı���Ϣ
	 * @param m
	 */
	public void TransmitTextMessage(MessageBox m) {
		//�������ϣ��ҵ���Ϣ���շ���Ӧ�������
		for(String account:allClient.keySet()) {
			if(account.equals(m.getTo().getAccount())) {
				m.setTime(new Date().toLocaleString());
				try {
					allClient.get(account).writeObject(m);
					allClient.get(account).flush();
					System.out.println("��ת��");
				}catch(IOException E) {
					E.printStackTrace();
				}
				break;
			}
		}
	}
	/**
	 * ����Ⱥ��Ϣ
	 */
	public void DatermineGroupNews(MessageBox m) {
		
	}
	}
}
