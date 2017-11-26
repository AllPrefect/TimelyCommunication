package com.abc.timelycommunication.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.abc.timelycommunication.view.LoginFrame;
import com.abc.timelycommunication.view.ServerFrame;

public class ServerButtonListener implements ActionListener {
	private ServerFrame serverframe;
	private LoginFrame loginframe;
	public ServerButtonListener(ServerFrame serverframe) {
		this.serverframe=serverframe;
	}
	public ServerButtonListener(LoginFrame loginframe) {
		this.loginframe=loginframe;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==loginframe.getUsername()) {
			
		}
	}

}
