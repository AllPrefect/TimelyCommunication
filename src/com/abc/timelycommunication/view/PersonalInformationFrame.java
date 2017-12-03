package com.abc.timelycommunication.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.abc.timelycommunication.model.MessageBox;
import com.abc.timelycommunication.model.User;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class PersonalInformationFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel label_2;
	private JLabel label_3;
	private String pathName;
	private User user;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	/**
	 * Create the frame.
	 */
	public PersonalInformationFrame(User user,ObjectOutputStream out,ObjectInputStream in) {
		this.user=user;
		this.out=out;
		this.in=in;
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 517, 479);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField(user.getAccount());
		textField.setBounds(95, 30, 170, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u8D26   \u53F7\uFF1A");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 13));
		lblNewLabel.setBounds(30, 32, 65, 25);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u6635   \u79F0\uFF1A");
		label.setFont(new Font("����", Font.PLAIN, 13));
		label.setBounds(30, 72, 65, 25);
		contentPane.add(label);
		
		textField_1 = new JTextField(user.getUsername());
		textField_1.setColumns(10);
		textField_1.setBounds(95, 70, 170, 28);
		contentPane.add(textField_1);
		
		JLabel label_1 = new JLabel("\u5BC6   \u7801\uFF1A");
		label_1.setFont(new Font("����", Font.PLAIN, 13));
		label_1.setBounds(30, 152, 65, 25);
		contentPane.add(label_1);
		
		passwordField = new JPasswordField(user.getPassword());
		passwordField.setBounds(95, 150, 170, 28);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(30, 192, 65, 25);
		contentPane.add(lblNewLabel_1);
		
		passwordField_1 = new JPasswordField(user.getPassword());
		passwordField_1.setBounds(95, 190, 170, 28);
		contentPane.add(passwordField_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5934\u50CF");
		lblNewLabel_2.setBounds(341, 31, 106, 145);
		lblNewLabel_2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(user.getImagepath()).getScaledInstance(106, 145, Image.SCALE_DEFAULT)));
		contentPane.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		for(int n=1;n<9;n++)
		{
			comboBox.addItem(n+".jpg");
		}
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				PersonalInformationFrame.this.pathName="resource/avatar/"+e.getItem().toString();
				lblNewLabel_2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(pathName).getScaledInstance(106, 145, Image.SCALE_DEFAULT)));
			}
		});
		comboBox.setBounds(345, 185, 90, 20);
		contentPane.add(comboBox);
		
		label_2 = new JLabel("\u5907   \u6CE8\uFF1A");
		label_2.setFont(new Font("����", Font.PLAIN, 13));
		label_2.setBounds(30, 230, 65, 25);
		contentPane.add(label_2);
		
		label_3 = new JLabel("\u6027   \u522B\uFF1A");
		label_3.setFont(new Font("����", Font.PLAIN, 13));
		label_3.setBounds(30, 112, 65, 25);
		contentPane.add(label_3);
		
		JRadioButton radioButton = new JRadioButton("\u7537");
		if(user.getSex().equals("��")) {
			radioButton.setSelected(true);
		}
		radioButton.setBounds(108, 114, 60, 20);
		contentPane.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("\u5973");
		if(user.getSex().equals("Ů")) {
			radioButton.setSelected(true);
		}
		radioButton_1.setBounds(182, 114, 60, 20);
		contentPane.add(radioButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(94, 240, 374, 94);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea(user.getInstruction());
		scrollPane.setViewportView(textArea);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.setBounds(390, 380, 60, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String account=textField.getText().trim();
				String username=textField_1.getText();
				String sex=radioButton.isSelected()?"��":"Ů";
				String password=passwordField.getText();
				String passwordagain=passwordField_1.getText();
				String touxiang=pathName;
				String instruction=textArea.getText().toString();
			
				if(!password.equals(passwordagain)) {
					JOptionPane.showMessageDialog(PersonalInformationFrame.this,"�����������벻һ��","��ܰ��ʾ", JOptionPane.ERROR_MESSAGE);
				}else {
					User updateUser=new User(account,username,password,sex,touxiang,instruction);
					MessageBox updateData=new MessageBox();
					updateData.setFrom(updateUser);
					updateData.setType("update");
					System.out.println(updateData);
					//ʹ�����л�����Ϣ����������
					try {
						out.writeObject(updateData);
						out.flush();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					System.out.println("���͸�������");
					//������������Ϣ
					try {
						MessageBox received=(MessageBox)PersonalInformationFrame.this.in.readObject();
						JOptionPane.showMessageDialog(PersonalInformationFrame.this, (received.getContent().equals("true"))?"�޸ĳɹ�":"�޸�ʧ��");
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		contentPane.add(button);
	}
}
