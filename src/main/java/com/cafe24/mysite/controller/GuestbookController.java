package com.cafe24.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.GuestbookService;
import com.cafe24.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping({"","/"})
	public String list(Model model){
		List<GuestbookVo> list = guestbookService.getList();
		model.addAttribute("list", list);
		return "guestbook/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(@ModelAttribute GuestbookVo vo) {
		guestbookService.add(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value="/deleteform/{no}", method = RequestMethod.GET)
	public String deleteform(Model model, @PathVariable(value="no") Long no) {
		model.addAttribute("no",no);
		return "guestbook/delete";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(@RequestParam(value="no", required = true, defaultValue="0") Long no,
							@RequestParam(value="password", required = true, defaultValue = "") String password) {
		guestbookService.delete(no,password);
		return "redirect:/guestbook";
	}
	
}
