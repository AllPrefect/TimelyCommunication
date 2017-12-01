package com.abc.timelycommunication.view;

import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.abc.timelycommunication.model.MessageBox;
import com.abc.timelycommunication.model.User;

public class RegisterFrame extends JFrame{
	private JButton button_1;
	private JPanel contentPane;
	private JTextArea textArea;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	public RegisterFrame(ObjectOutputStream out,ObjectInputStream in) {
		this.out=out;
		this.in=in;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 479);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextField textField = new JTextField("999999");
		textField.setBounds(95, 30, 170, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u8D26   \u53F7\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 13));
		lblNewLabel.setBounds(30, 32, 65, 25);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u6635   \u79F0\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 13));
		label.setBounds(30, 72, 65, 25);
		contentPane.add(label);
		
		JTextField textField_1 = new JTextField("大狗子");
		textField_1.setColumns(10);
		textField_1.setBounds(95, 70, 170, 28);
		contentPane.add(textField_1);
		
		JLabel label_1 = new JLabel("\u5BC6   \u7801\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 13));
		label_1.setBounds(30, 152, 65, 25);
		contentPane.add(label_1);
		
		JPasswordField passwordField = new JPasswordField("999999");
		passwordField.setBounds(95, 150, 170, 28);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(30, 192, 65, 25);
		contentPane.add(lblNewLabel_1);
		
		JPasswordField passwordField_1 = new JPasswordField("999999");
		passwordField_1.setBounds(95, 190, 170, 28);
		contentPane.add(passwordField_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5934\u50CF");
		lblNewLabel_2.setBounds(341, 31, 106, 145);
		lblNewLabel_2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("resource/avatar/1.jpg").getScaledInstance(106, 145, Image.SCALE_DEFAULT)));
		contentPane.add(lblNewLabel_2);
		
		JLabel label_2 = new JLabel("\u5907   \u6CE8\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 13));
		label_2.setBounds(30, 230, 65, 25);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u6027   \u522B\uFF1A");
		label_3.setFont(new Font("宋体", Font.PLAIN, 13));
		label_3.setBounds(30, 112, 65, 25);
		contentPane.add(label_3);
		
		JRadioButton radioButton = new JRadioButton("\u7537");
		radioButton.setBounds(108, 114, 60, 20);
		contentPane.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("\u5973");
		radioButton_1.setBounds(182, 114, 60, 20);
		contentPane.add(radioButton_1);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			//提取注册界面上的信息
			String account=textField.getText().trim();
			String username=textField_1.getText();
			String sex=radioButton.isSelected()?"男":"女";
			String password=passwordField.getText();
			String passwordagain=passwordField_1.getText();
			String touxiang="null";
			String instruction=textArea.getText().toString();
			if(!password.equals(passwordagain)) {
				JOptionPane.showMessageDialog(RegisterFrame.this,"两次输入密码不一致","温馨提示", JOptionPane.ERROR_MESSAGE);
			}else {
				User newUser=new User(account,username,password,sex,touxiang,instruction);
				MessageBox registerData=new MessageBox();
				registerData.setFrom(newUser);
				registerData.setType("register");
				System.out.println(registerData);
				//使用序列化将信息传给服务器
				try {
					out.writeObject(registerData);
					out.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println("传送给服务器");
				//服务器回馈信息
				try {
					MessageBox received=(MessageBox)RegisterFrame.this.in.readObject();
					JOptionPane.showMessageDialog(RegisterFrame.this, (received.getContent().equals("true"))?"注册成功":"注册失败");
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
			}
		});
		button.setBounds(108, 380, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("登陆");
		button_1.setBounds(308, 380, 93, 23);
		button_1.setEnabled(true);
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username=textField_1.getText();
				String password=passwordField.getText();
				MessageBox  loginMessage=new MessageBox();
				User willLoginUser=new User(username,password);
				loginMessage.setFrom(willLoginUser);
				loginMessage.setType("login");
				try {
					out.writeObject(loginMessage);
					out.flush();
					MessageBox  loginresult=(MessageBox)in.readObject();
					if(loginresult.getFrom()==null) {
					JOptionPane.showMessageDialog(RegisterFrame.this, "请先进行注册！");	
					}else {
					User u=loginresult.getFrom();
					MainFrame  m=new MainFrame(u,out,in);
					m.setVisible(true);
					RegisterFrame.this.setVisible(false);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		contentPane.add(button_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(94, 240, 374, 94);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea("什么都没有");
		scrollPane.setViewportView(textArea);
		
	} 

}
