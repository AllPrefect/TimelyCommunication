package com.abc.timelycommunication.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTree;

import com.abc.timelycommunication.control.MainFrameListener;
import com.abc.timelycommunication.model.User;

public class MainFrame extends JFrame {
	private JLabel headPicture,headportrait,username;
	private JTextField searchList;
	private JButton searchButton;
	//���յ�½���洫�����û�����
	private User user;
	
	public JLabel getHeadportrait() {
		return headportrait;
	}
	public JLabel getUsername() {
		return username;
	}
	public JButton getSearchButton() {
		return searchButton;
	}
	public MainFrame(User user) {
		this.user=user;
		setSize(300, 600);
		setVisible(true);
		setLayout(null);//�������κ�Ĭ�ϲ���
		setTitle("Timely......");
		setLocation(100,50);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().createImage("resource/pictures/logo.jpg"));
		homeCompoment();
		paintComponents(getGraphics());
		paintAll(getGraphics());
	}
	public void homeCompoment() {
		headPicture=new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage("resource/pictures/mainframe.gif")));
		headPicture.setSize(300,125);
		headPicture.setLocation(0,0);
		this.add(headPicture);
		
		headportrait=new JLabel();//ͷ��
		headportrait.setBounds(200,10,70,90);
		headportrait.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(user.getImagepath()).getScaledInstance(80, 100, Image.SCALE_DEFAULT)));
		headportrait.addMouseListener(new MainFrameListener(MainFrame.this));
		headPicture.add(headportrait);
		
		username=new JLabel(user.getUsername());
		username.setBounds(210,103,70,20);
		username.addMouseListener(new MainFrameListener(MainFrame.this));
		headPicture.add(username);
		
		searchList=new JTextField(" ����...");
		searchList.setBounds(5, 132, 235, 20);
		searchList.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		searchList.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(searchList.getText().length()==0)
					searchList.setText(" ����...");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(searchList.getText().equals(" ����..."))
					searchList.setText("");
			}
		});
		this.add(searchList);
		
		searchButton=new JButton("��");
		searchButton.addMouseListener(new MainFrameListener(MainFrame.this));
		searchButton.setBounds(245, 132, 35, 20);
		this.add(searchButton);
		
		JTree tree = new JTree();
		tree.setToolTipText("");
		tree.setEditable(true);
		tree.setBounds(15, 160, 260, 400);
		this.add(tree);
		
	}
}
