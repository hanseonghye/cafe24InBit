package com.cafe24.springex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		
		return "hihi";
	}
	@RequestMapping({"/","/main"})
	public String main() {
		System.out.println("dddd");
		return "main/index";
	}
	
}
