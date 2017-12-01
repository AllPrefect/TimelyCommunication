package com.abc.timelycommunication.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import com.abc.timelycommunication.model.MessageBox;
import com.abc.timelycommunication.model.User;
import com.abc.timelycommunication.view.ChattingFrame;

public class ChattingFrameListener implements ActionListener{
	private ChatRecordHelper  record;//
	private ChattingFrame chattingframe;
	private ObjectOutputStream  out;
	private ObjectInputStream  in;
	private User my,your;
	public ChattingFrameListener(ChattingFrame chattingframe,User my,User your,ObjectOutputStream  out,ObjectInputStream  in) {
		this.chattingframe=chattingframe;
		this.my=my;
		this.your=your;
		this.out=out;
		this.in=in;
	}
	public void actionPerformed(ActionEvent e) {
		record=new ChatRecordHelper();
		//点击发送
		if(e.getSource()==chattingframe.getBtnNewButton()) {
			String  willSendMessage=chattingframe.getTextArea_1().getText();
			MessageBox send=new MessageBox();
			send.setContent(willSendMessage);
			send.setFrom(my);
			send.setTo(your);
			send.setType("textMessage");
			//用登陆界面传过来的输出流写给服务器转发给好友
			try {
				out.writeObject(send);
				out.flush();
				System.out.println(send);
			}catch(IOException e1) {
				e1.printStackTrace();
			}
			String nowTime=new Date().toLocaleString();
			chattingframe.getTextArea().append(my.getUsername()+" : ["+nowTime+"]\t");
			record.writeMessageToRecord(willSendMessage);
			chattingframe.getTextArea().append((chattingframe.getTextArea().getText().length()!=0?"\r\n":"")+willSendMessage+"\r\n");
			chattingframe.getTextArea_1().setText("");
		}
		//点击抖动
		if(e.getSource()==chattingframe.getBtnNewButton_1()) {
			MessageBox  m=new MessageBox();
			m.setFrom(my);
			m.setTo(your);
			m.setType("shakeMessage");
			shakeWindow();
		}
	}
	
	public void  shakeWindow() {
		new Thread() {
			public void run() {
				int waitTime=50;
				int lastX=chattingframe.getX();
				int lasty=chattingframe.getY();
				for(int n=0;n<5;n++)
				{
					chattingframe.setLocation(lastX+3, lasty);
					try {
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					chattingframe.setLocation(lastX, lasty+3);
					try {
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					chattingframe.setLocation(lastX-3, lasty);
					try {
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					chattingframe.setLocation(lastX, lasty-3);
					try {
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				chattingframe.setLocation(lastX, lasty);
			};
			
		}.start();
	}
		
}
