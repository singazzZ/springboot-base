package com.singa.springboot.service;

import com.singa.springboot.vo.UserVo;

public interface IUserService {
	boolean addUser(UserVo user);
	
	public boolean isExist(String name);
}

