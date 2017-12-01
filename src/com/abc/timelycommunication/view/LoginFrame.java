package com.abc.timelycommunication.view;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.abc.timelycommunication.control.Client;
import com.abc.timelycommunication.control.LoginFrameListener;

public class LoginFrame extends JFrame {
	
	private JLabel headImage,JLabel1,JLabel2;
	private JComboBox account;
	private JPasswordField password;
	private JButton login,register; 
	
	public JButton getLogin() {
		return login;
	}
	public JButton getRegister() {
		return register;
	}
	public JPasswordField getPassword() {
		return password;
	}
	public JComboBox getAccount() {
		return account;
	}
	
	public LoginFrame() {
		setSize(340,300);//���ڴ�С
		setLayout(null);//�������κ�Ĭ�ϵĲ���
		setVisible(true);//��ʾ����
		setTitle(" Timely");//����
		setLocationRelativeTo(null);//����λ�þ���
		setResizable(false);//�̶����ڴ�С
		setIconImage(Toolkit.getDefaultToolkit().createImage("resource/pictures/logo.jpg"));
		setDefaultCloseOperation(EXIT_ON_CLOSE);//�رմ���ʱ��������
		initComponent();
		paintComponents(getGraphics());
		paintAll(getGraphics());
	}
	/**
	 * ��ʼ���������
	 */
	public void initComponent() {
		headImage=new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage("resource/pictures/login.gif")));
		headImage.setBorder(BorderFactory.createLineBorder(Color.GRAY));//���ñ߿�
		headImage.setSize(340,150);
		headImage.setLocation(0,0);
		this.add(headImage);//ִ����ӵ�����
		
		JLabel1=new JLabel("�˻�");
		JLabel1.setLocation(60,165);
		JLabel1.setSize(30,20);
		this.add(JLabel1);
		
		account=new JComboBox(new Object[] {"666666","999999"});
		account.setEditable(true);
		account.setSize(150,20);
		account.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		account.setLocation(95,165);
		this.add(account);
		
		JLabel2=new JLabel("����");
		JLabel2.setLocation(60,195);
		JLabel2.setSize(30,20);
		this.add(JLabel2);
		
		password=new JPasswordField("666666");
		password.setSize(150,20);
		password.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		password.setLocation(95,195);
		this.add(password);
		
		
		login=new JButton("��½");
		login.setSize(60, 25);
		login.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		login.setLocation(95,230);
		login.addMouseListener(new LoginFrameListener(LoginFrame.this));
		this.add(login);
		
		register=new JButton("ע��");
		register.setSize(60, 25);
		register.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		register.setLocation(185,230);
		register.addMouseListener(new LoginFrameListener(LoginFrame.this));
		this.add(register);
	}

	public static void main(String[] args) {
		new LoginFrame();
	}

}
