package com.cafe24.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userSerivce;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute UserVo vo) {
		userSerivce.join(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping(value = "/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(	@RequestParam(value = "email", required = true, defaultValue = "") String email,
							@RequestParam(value = "password", required = true, defaultValue = "") String password, HttpSession session,
							Model model) {

		UserVo authUser = userSerivce.getUser(new UserVo(email, password));

		if (authUser == null) {
			model.addAttribute("result", "fail");
			return "user/login";
		}

		session.setAttribute("authUser", authUser);
		return "redirect:/";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping("/update")
	public String update(HttpSession session) {
		if (session.getAttribute("authUser") == null) {
			return "user/login";
		}
		return "user/update";
	}

	@RequestMapping(value = "/inforupdate", method = RequestMethod.POST)
	public String inforupdate(@ModelAttribute UserVo vo, @RequestParam(value = "pre_email", required = true, defaultValue = "") String pre_email) {
		userSerivce.update(vo, pre_email);
		return "user/inforupdatesuccess";
	}
}
