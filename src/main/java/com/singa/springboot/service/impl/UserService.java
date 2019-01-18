package com.singa.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.singa.springboot.domain.User;
import com.singa.springboot.repository.UserRepository;
import com.singa.springboot.service.IUserService;
import com.singa.springboot.vo.UserVo;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean addUser(UserVo userVo) {
		User user = new User();
		user.setName(userVo.getName());
		user.setAge(Integer.parseInt(userVo.getAge()));
		user.setAddress(userVo.getAddress());
		user = userRepository.save(user);
		return user.getId() > 0;
	}

	@Override
	public boolean isExist(String name) {
		User user = new User();
		user.setName(name);
		Example<User> example = Example.of(user, ExampleMatcher.matching().withIgnorePaths("age"));
		List<User> list = userRepository.findAll(example);
		return list.size() > 0;
	}

}
