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

public class MainFrame extends JFrame {
	private JLabel headPicture;
	private JTextField searchList;
	private JButton searchButton;
	public MainFrame() {
		setSize(300, 600);
		setVisible(true);
		setLayout(null);//不采用任何默认布局
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
		headPicture.setSize(300,110);
		headPicture.setLocation(0,0);
		this.add(headPicture);
		
		JLabel headportrait=new JLabel();//头像
		headportrait.setBounds(200,10,80,100);
		headportrait.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("resource/avatar/1.jpg").getScaledInstance(80, 100, Image.SCALE_DEFAULT)));
		
		searchList=new JTextField(" 搜索...");
		searchList.setBounds(5, 112, 235, 20);
		searchList.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		searchList.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(searchList.getText().length()==0)
					searchList.setText(" 搜索...");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(searchList.getText().equals(" 搜索..."))
					searchList.setText("");
			}
		});
		this.add(searchList);
		
		searchButton=new JButton("の");
		searchButton.setBounds(245, 112, 35, 20);
		this.add(searchButton);
		
		JTree tree = new JTree();
		tree.setToolTipText("123");
		tree.setEditable(true);
		tree.setBounds(15, 140, 260, 300);
		this.add(tree);
		
		
	}
	
	public static void main(String[] args) {
	new MainFrame();
	} 
}
