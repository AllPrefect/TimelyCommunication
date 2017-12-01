package com.abc.timelycommunication.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.abc.timelycommunication.model.MessageBox;
import com.abc.timelycommunication.view.ChattingFrame;

public class Client {
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public Client(ObjectOutputStream out, ObjectInputStream in) {
		this.out=out;
		this.in=in;
	}

	
	/*public boolean connectServer() {
		try {
			Socket client=new Socket(Config.serverIP,Config.port);
			//向服务端发送字符流
			BufferedWriter out=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
			//从loginFrame类获取账户名称信息
			//服务端接收按行读取要添加“\r\n”
			out.write(LoginFrame.getAccount().getSelectedItem()+"\r\n");
			out.flush();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}*/
}
