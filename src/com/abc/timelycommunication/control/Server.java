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
	private Socket server;//接收客户端对象
	/**
	 * 两个关联类之间调用调用
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
	
	public  void startService() {//允许客户端连接
		new Thread() {
			public void run() {
				while(true) {
					try {
						server=s.accept();//等待客户端连接
						//接受传来的字符流
						BufferedReader r=new BufferedReader(new InputStreamReader(server.getInputStream()));
						String username=r.readLine();
						
						System.out.println(username);
						BufferedWriter  out=new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));
						frame.getTextArea().append(new Date().toLocaleString()+":\t客户端["+username+"]l连接进来了!\r\n");
						frame.paintComponents(frame.getGraphics());//绘画组件 updateUI
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
