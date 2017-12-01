package com.abc.timelycommunication.view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.abc.timelycommunication.control.Server;
import com.abc.timelycommunication.control.ServerFrameListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class ServerFrame extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnNewButton,btnNewButton_1;
	
	public JTextArea getTextArea() {
		return textArea;
	}
	public JButton getBtnNewButton() {
		return btnNewButton;
	}
	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}
	
	private Server s;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerFrame frame = new ServerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerFrame() {
		//Server����û�����޲ι�����
		s=new Server(ServerFrame.this);//new һ��server����
		
		setTitle("��������");
		setResizable(false);//����ı䴰�ڴ�С
		setIconImage(Toolkit.getDefaultToolkit().createImage("resource/pictures/logo.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 55, 381, 183);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		btnNewButton = new JButton("\u5F00\u542F\u670D\u52A1\u7AEF");
		btnNewButton.setBounds(66, 283, 113, 23);
		btnNewButton.addActionListener(new ServerFrameListener(ServerFrame.this));
		//����Server��ļ�����
		/*
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean startResult=s.startServer();
				btnNewButton.setEnabled(false);//���ð�ť������
				//��������Ϣ��ʾ���ı���
				textArea.append(new Date().toLocaleString()+":\t����������"+(startResult?"�ɹ�":"ʧ��")+"!\r\n");
				//��������
				JOptionPane.showMessageDialog(ServerFrame.this, startResult?"�����ɹ���":"����ʧ����", "��ܰ��ʾ", startResult?JOptionPane.INFORMATION_MESSAGE:JOptionPane.ERROR_MESSAGE);
				if(startResult) {
					s.startService();
				}
			}
		});
		*/
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("\u5173\u95ED\u670D\u52A1\u7AEF");
		btnNewButton_1.setBounds(243, 283, 113, 23);
		btnNewButton_1.addActionListener(new ServerFrameListener(ServerFrame.this));
		/*
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean closeResult=s.closeServer();
				//���ر���Ϣ��ʾ���ı���
				textArea.append(new Date().toLocaleString()+":\t�������ر�"+(closeResult?"�ɹ�":"ʧ��")+"!\r\n");
				//��������
				JOptionPane.showMessageDialog(ServerFrame.this, closeResult?"�ѹر�":"�ر�ʧ����", "��ܰ��ʾ", closeResult?JOptionPane.INFORMATION_MESSAGE:JOptionPane.ERROR_MESSAGE);
			}
		});
		*/
		contentPane.add(btnNewButton_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setEnabled(false);
		textPane.setEditable(false);
		textPane.setText("\u5BA2\u6237\u7AEF\u8FDE\u63A5\u5217\u8868\uFF1A");
		textPane.setBounds(28, 24, 120, 21);
		contentPane.add(textPane);
	}

}
