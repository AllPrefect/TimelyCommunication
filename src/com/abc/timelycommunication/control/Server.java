package com.abc.timelycommunication.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import com.abc.timelycommunication.view.ServerFrame;

public class Server {
	private ServerSocket  s;
	private ServerFrame frame;
	private Socket server;//���տͻ��˶���
	/**
	 * ����������֮����õ���
	 * @param frame
	 */
	public Server(ServerFrame frame) {
		this.frame=frame;
	}
	public boolean startServer() {
		try {
			s=new ServerSocket(Config.port);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public  void startService() {//����ͻ�������
		new Thread() {
			public void run() {
				while(true) {
					try {
						server=s.accept();//�ȴ��ͻ�������
						//���ܴ������ַ���
						BufferedReader r=new BufferedReader(new InputStreamReader(server.getInputStream()));
						String username=r.readLine();
						
						System.out.println(username);
						BufferedWriter  out=new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));
						frame.getTextArea().append(new Date().toLocaleString()+":\t�ͻ���["+username+"]l���ӽ�����!\r\n");
						frame.paintComponents(frame.getGraphics());//�滭��� updateUI
						System.out.println(server.getInetAddress());
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	public boolean closeServer() {
		try {
			server.close();
			s.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
}
