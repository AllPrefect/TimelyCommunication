package com.abc.timelycommunication.control;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.abc.timelycommunication.view.MainFrame;
import com.abc.timelycommunication.view.PersonalInformationFrame;

public class MainFrameListener implements MouseListener{
	private MainFrame mainframe;
	public  MainFrameListener(MainFrame mainframe) {
		this.mainframe=mainframe;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("�����");
		//new PersonalInformationFrame();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("�ͷ���");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("������");
		//mainframe.getSearchButton().setBackground(Color.magenta);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("�Ƴ���");
		
	}
	

}
