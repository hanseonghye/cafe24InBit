package com.cafe24.jblog.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private BlogService blogService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute("userVo")  @Valid UserVo vo, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		userService.join(vo);
		blogService.createBlog(vo.getId());
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public void auth(@RequestParam(value = "id", required = true, defaultValue = "") String id,
			@RequestParam(value = "password", required = true, defaultValue = "") String password, HttpSession session,
			Model model) {
		
	}



}
