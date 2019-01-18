package com.singa.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.singa.springboot.service.IUserService;
import com.singa.springboot.vo.UserVo;

@Controller
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping(value = "/addUser")
	public String addUser(Model model) {
		model.addAttribute("user", new UserVo());
		return "addUser";
	}
	
	@ResponseBody
	@PostMapping(value = "/addUser")
	public String addUser(@ModelAttribute UserVo user) {
		boolean result = userService.addUser(user);
		return result ? "success" : "failed";
	}
	
	@ResponseBody
	@PostMapping(value = "/isexist")
	public String isExist(String userName) {
		boolean result = userService.isExist(userName);
		return result ? "yes" : "no";
	}
}
