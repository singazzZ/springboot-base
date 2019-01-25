package com.singa.springboot.vo;

public class UserVo {
	public String name;
	public String age;
	public String address;
	
	public UserVo(){}
	
	public UserVo(String name, String age, String address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}
	
	public UserVo(String name, String age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
