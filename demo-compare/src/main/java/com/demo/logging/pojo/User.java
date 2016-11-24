package com.demo.logging.pojo;

import java.util.Date;

import com.spiderworts.util.n1.base.RandomDataUtil;

public class User {
	private String name;
	private Date birthday;

	public User() {
		this.name = RandomDataUtil.getRandomString(8);
		this.birthday = RandomDataUtil.getRandomDate();
	}

	@Override
	public String toString() {
		return "Name: " + name + ", Birthday: " + birthday + ", :)";
	}
}
