package com.abc.timelycommunication.view;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import com.abc.timelycommunication.control.ChattingFrameListener;
import com.abc.timelycommunication.model.User;

public class ChattingFrame extends JFrame {
	private JButton btnNewButton,btnNewButton_1,btnNewButton_2;
	private JPanel contentPane;
	private JTextArea textArea,textArea_1;
	private ChattingFrameListener  lisenter;//
	
	public JButton getBtnNewButton() {
		return btnNewButton;
	}
	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}
	public JButton getBtnNewButton_2() {
		return btnNewButton_2;
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	public JTextArea getTextArea_1() {
		return textArea_1;
	}
	/**
	 * Create the frame.
	 */
	public ChattingFrame(User my,User your,ObjectInputStream  in,ObjectOutputStream  out) {
		
		lisenter=new ChattingFrameListener(ChattingFrame.this,my,your,in,out);
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("与["+your.getUsername()+"]聊天中...");
		setBounds(100, 100, 529, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 488, 235);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 280, 488, 108);
		
		btnNewButton = new JButton("\u53D1\u9001");
		btnNewButton.addActionListener(lisenter);
		btnNewButton.setBounds(429, 398, 69, 23);
		contentPane.setLayout(null);
		
		btnNewButton_1 = new JButton("抖动");
		btnNewButton_1.addActionListener(lisenter);
		btnNewButton_1.setBounds(10, 250, 69, 23);
		
		btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(lisenter);
		btnNewButton_2.setBounds(90, 250, 69, 23);
		
		textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		scrollPane_1.setViewportView(textArea_1);
		contentPane.add(btnNewButton);
		contentPane.add(btnNewButton_1);
		contentPane.add(btnNewButton_2);
		contentPane.add(scrollPane_1);
		contentPane.add(scrollPane);
		
		
	}
	public void  shakeWindow() {
		new Thread() {
			public void run() {
				int waitTime=50;
				int lastX=ChattingFrame.this.getX();
				int lasty=ChattingFrame.this.getY();
				for(int n=0;n<5;n++)
				{
					ChattingFrame.this.setLocation(lastX+3, lasty);
					try {
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ChattingFrame.this.setLocation(lastX, lasty+3);
					try {
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ChattingFrame.this.setLocation(lastX-3, lasty);
					try {
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ChattingFrame.this.setLocation(lastX, lasty-3);
					try {
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				ChattingFrame.this.setLocation(lastX, lasty);
			};
			
		}.start();
	}
	
	
}
