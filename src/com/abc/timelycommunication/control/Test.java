package com.abc.timelycommunication.control;

import com.abc.timelycommunication.model.User;

public class Test {
	public static void main(String[] args) {
		User user=DataOperate.login("111111","111111");
		System.out.println(user);
	}
}