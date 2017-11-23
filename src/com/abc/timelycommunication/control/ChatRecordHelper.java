package com.abc.timelycommunication.control;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ChatRecordHelper {
	private File recordFiles;
	private FileInputStream in;
	private FileOutputStream out;
	{
		recordFiles =new File(Config.chatRecordPath);
		if(!recordFiles.exists()) {
			try {
				recordFiles.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			out=new FileOutputStream(recordFiles,true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void writeMessageToRecord(String message) {
		try {
			out.write(message.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

