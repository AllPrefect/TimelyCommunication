package com.abc.timelycommunication.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.abc.timelycommunication.view.LoginFrame;

public class Client {
	private Socket client;
	private LoginFrame frame;
	private BufferedReader in;
	private BufferedWriter out;
	////解决类与类之间的关联
	public Client(LoginFrame frame) {
		this.frame=frame;
	}
	
	public boolean connectServer() {
		try {
			client=new Socket(Config.serverIP,Config.port);
			//向服务端发送字符流
			out=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			in=new BufferedReader(new InputStreamReader(client.getInputStream()));
			//从loginFrame类获取账户名称信息
			//服务端接收按行读取要添加“\r\n”
			out.write(frame.getUsername().getSelectedItem()+"\r\n");
			out.flush();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
}
