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
			//�����˷����ַ���
			BufferedWriter out=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
			//��loginFrame���ȡ�˻�������Ϣ
			//����˽��հ��ж�ȡҪ��ӡ�\r\n��
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
