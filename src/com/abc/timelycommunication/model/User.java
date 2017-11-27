package com.abc.timelycommunication.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;

public class User implements Serializable{//ʵ�����л���Ҫ�ȼ̳�Serializable�ӿ�
	private String account;//�˻�
	private String username;//�ǳ�
	private String password;//����
	private String sex;//�Ա�
	private String imagepath;//ͷ��
	private String instruction;//��ע
	private Map<String,HashSet<User>>  friends;//�����б�
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public Map<String, HashSet<User>> getFriends() {
		return friends;
	}
	public void setFriends(Map<String, HashSet<User>> friends) {
		this.friends = friends;
	}
	
	@Override
	public String toString() {
		return "User [account=" + account + ", username=" + username + ", password=" + password + ", sex=" + sex
				+ ", imagepath=" + imagepath + ", instruction=" + instruction + "]";
	}
	public User(String account, String username, String password, String sex, String imagepath, String instruction) {
		super();
		this.account = account;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.imagepath = imagepath;
		this.instruction = instruction;
		/*this.friends = friends;*/
	}
	public User() {
		super();
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
}
