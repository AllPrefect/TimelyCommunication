package com.abc.timelycommunication.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.abc.timelycommunication.control.ChatRecordHelper;

public class ChattingFrame extends JFrame {
	private JButton btnNewButton;
	private JPanel contentPane;
	private JTextArea textArea,textArea_1;
	private ChatRecordHelper  record;//
	private ChattingFrameActionListener  lisenter;//
	
	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChattingFrame frame = new ChattingFrame();
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
	public ChattingFrame() {
		record=new ChatRecordHelper();
		lisenter=new ChattingFrameActionListener();
		/**
		 * 
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 488, 221);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 237, 488, 33);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 280, 488, 108);
		
		btnNewButton = new JButton("\u53D1\u9001");
		btnNewButton.addActionListener(lisenter);
		btnNewButton.setBounds(429, 398, 69, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.setLayout(null);
		
		textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		scrollPane_1.setViewportView(textArea_1);
		contentPane.add(scrollPane_1);
		contentPane.add(panel);
		contentPane.add(scrollPane);
		contentPane.add(btnNewButton);
	}
	
private class ChattingFrameActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==btnNewButton) {
				String  willSendMessage=textArea_1.getText();
				record.writeMessageToRecord(willSendMessage);
				textArea.append(textArea.getText()+(textArea.getText().length()!=0?"\r\n":"")+new Date().toLocaleString()+": "+willSendMessage);
				textArea_1.setText("");
			}
		}
		
	}
}
