package com.abc.timelycommunication.view;

import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RegisterFrame extends JFrame{
	private JPanel contentPane;
	
	public RegisterFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 479);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextField textField = new JTextField();
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
		
		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(95, 70, 170, 28);
		contentPane.add(textField_1);
		
		JLabel label_1 = new JLabel("\u5BC6   \u7801\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 13));
		label_1.setBounds(30, 152, 65, 25);
		contentPane.add(label_1);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(95, 150, 170, 28);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(30, 192, 65, 25);
		contentPane.add(lblNewLabel_1);
		
		JPasswordField passwordField_1 = new JPasswordField();
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
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(139, 380, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.setBounds(308, 380, 93, 23);
		contentPane.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(94, 240, 374, 94);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
	} 

	public static void main(String[] args) {
		new RegisterFrame();
	}


}
