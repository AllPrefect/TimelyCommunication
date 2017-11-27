package com.abc.timelycommunication.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.abc.timelycommunication.model.User;

public class DataOperate {
	//注册
	public static boolean register(User user) {
		File data=new File("database/"+user.getUsername()+".data");
		if(data.exists())
		return false;
		return updateInformation(user);
	}
	//修改个人信息
	public static boolean updateInformation(User user) {
		try {
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("database/"+user.getUsername()+".data"));
			out.writeObject(user);//将个人信息以序列化的方式保存
			out.flush();
			out.close();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//登陆方法  User类型减少设置参数，更方便
	public static User login(String username,String password) {
		File data=new File("database/"+username+".data");
		if(!data.exists()) {
			System.out.println("文件不存在！");
			return null;
		}else {
			try {
				ObjectInputStream  in=new ObjectInputStream(new FileInputStream("database/"+username+".data"));
				User user=(User)in.readObject();
				
				System.out.println(user);
				if(password.equals(user.getPassword())&&username.equals(user.getUsername())) {
					return user;
				}else {
					System.out.println("账户密码错误！");
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	public static void main(String[] args) {
		User user1=new User("666666","狗蛋","666666","男","resource/avatar/6.jpg","尼玛喊你回家吃饭");
		User user2=new User("111111","小狗子","111111","男","resource/avatar/1.jpg","咦儿伊尔吆");
		//封装好友列表信息
		Map<String,HashSet<User>>  friends=new HashMap<>();
		HashSet<User>  f1s=new HashSet<>();
				f1s.add(user2);
				
				friends.put("大学室友", f1s);
				user1.setFriends(friends);
				
				try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("database/"+user1.getUsername()+".data"));
					out.writeObject(user1);
					out.flush();
					out.close();
					out=new ObjectOutputStream(new FileOutputStream("database/"+user2.getUsername()+".data"));
					out.writeObject(user2);
					out.flush();
					out.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
		System.out.println(DataOperate.login("狗蛋","666666"));
			
			
	}
}
