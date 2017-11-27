package com.abc.timelycommunication.control;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.abc.timelycommunication.view.ChattingFrame;
import com.abc.timelycommunication.view.MainFrame;
import com.abc.timelycommunication.view.PersonalInformationFrame;

public class MainFrameListener implements MouseListener{
	private PersonalInformationFrame p;
	private MainFrame mainframe;
	public  MainFrameListener(MainFrame mainframe) {
		this.mainframe=mainframe;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==mainframe.getTree()) {
			System.out.println("press");
		//鼠标左键点击两次
		if(e.getButton()==1&&e.getClickCount()==2) {
			//
			TreePath path=mainframe.getTree().getSelectionPath();
			System.out.println(path);
			
			DefaultMutableTreeNode lastNode=(DefaultMutableTreeNode)path.getLastPathComponent();
			if(lastNode.isLeaf()) {
				String username=lastNode.toString();
				ChattingFrame chatting=new ChattingFrame();
				chatting.setVisible(true);
			}
		}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource()==mainframe.getHeadportrait()) {
		p=new PersonalInformationFrame();
		p.setVisible(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource()==mainframe.getHeadportrait()) {
		p.setVisible(false);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		//mainframe.getSearchButton().setBackground(Color.magenta);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}
	

}
