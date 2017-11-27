package com.abc.timelycommunication.model;

import java.io.Serializable;
/**
 * 封装消息对象类型
 * @author user
 *
 */
public class MessageBox implements Serializable {
	private User from;
	private User to;
	private String type;//传送信息环境类型
	private String content;//传送信息内容
	private String time;
	public User getFrom() {
		return from;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	public User getTo() {
		return to;
	}
	public void setTo(User to) {
		this.to = to;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public MessageBox(User from, User to, String type, String content, String time) {
		super();
		this.from = from;
		this.to = to;
		this.type = type;
		this.content = content;
		this.time = time;
	}
	public MessageBox() {
		super();
	}
	@Override
	public String toString() {
		return "MessageBox [from=" + from + ", to=" + to + ", type=" + type + ", content=" + content + ", time=" + time
				+ "]";
	}
	
}
