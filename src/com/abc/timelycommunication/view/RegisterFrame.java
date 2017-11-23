package com.abc.timelycommunication.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class RegisterFrame implements ActionListener {
	JFrame rmainFrame;
	JLabel myJLabel;
	Container con;
	
	public RegisterFrame() {
		rmainFrame=new JFrame("ÐÂÓÃ»§×¢²á");
		rmainFrame.setVisible(true);
		rmainFrame.setSize(400, 500);
		rmainFrame.setLocationRelativeTo(null);
		rmainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//con=mainFrame.getContentPane();
		//con.setLayout(new FlowLayout());
		myJLabel=new JLabel("hello world!");
		rmainFrame.add(myJLabel);
		
	} 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RegisterFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
