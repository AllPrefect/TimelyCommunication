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
	////���������֮��Ĺ���
	public Client(LoginFrame frame) {
		this.frame=frame;
	}
	
	public boolean connectServer() {
		try {
			client=new Socket(Config.serverIP,Config.port);
			//�����˷����ַ���
			out=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			in=new BufferedReader(new InputStreamReader(client.getInputStream()));
			//��loginFrame���ȡ�˻�������Ϣ
			//����˽��հ��ж�ȡҪ��ӡ�\r\n��
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
