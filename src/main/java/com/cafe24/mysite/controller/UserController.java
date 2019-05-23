package com.cafe24.mysite.controller;

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

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userSerivce;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
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
	public String login(@RequestParam(value = "email", required = true, defaultValue = "") String email,
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

	/*
	 * @RequestMapping("/logout") public String logout(HttpSession session) {
	 * session.removeAttribute("authUser"); session.invalidate(); return
	 * "redirect:/"; }
	 */

	@Auth(role=Auth.Role.USER)
	@RequestMapping("/update")
	public String update(@AuthUser UserVo authUser, Model model) {
		UserVo userVo = userSerivce.getUser(authUser);
		model.addAttribute("userVo",userVo);
		return "user/update";
	}

	@RequestMapping(value = "/inforupdate", method = RequestMethod.POST)
	public String inforupdate(@ModelAttribute UserVo vo, HttpSession session) {
		userSerivce.update(vo);
		session.removeAttribute("authUser");
		session.setAttribute("authUser", vo);
		return "user/inforupdatesuccess";
	}
}
