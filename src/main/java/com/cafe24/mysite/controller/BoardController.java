package com.cafe24.mysite.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.security.Auth;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	private long totalCount;
	public static long countPage = 10;
	private int jumpPage = 3;

	@PostConstruct
	public void init() {
		this.totalCount = boardService.getTotalCount();
	}


	@RequestMapping(value = { "/list/{no}" }, method = RequestMethod.GET)
	public String listPage(Model model, @PathVariable(value = "no") Long no) throws JsonProcessingException {
		long countTop = (no - 1) * countPage;
		long count;

		if (totalCount <= countPage) {
			count = totalCount;
		} else if (no * countPage > totalCount) {
			count = totalCount - (no - 1) * countPage;
		} else {
			count = countPage;
		}

		List<BoardVo> list = boardService.getList(countTop, count);
		model.addAttribute("list", list);

		int pager = 0;
		if (totalCount <= countPage) {
			pager = 1;
		} else if ((int) (totalCount) % (int) (countPage) == 0) {
			pager = (int) (totalCount / countPage);
		} else {
			pager = ((int) (totalCount / countPage)) + 1;
		}
		model.addAttribute("nowPage", no);
		model.addAttribute("countPage", countPage);
		model.addAttribute("pager", pager);
		model.addAttribute("jumppage",jumpPage);
		return "board/list";
	}
	
	@Auth(role=Auth.Role.USER)
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}


	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute BoardVo vo,
			@RequestParam(value = "user_no", required = true, defaultValue = "0") Long user_no) {
		vo.setUser_no(user_no);
		boardService.write(vo);
		totalCount = totalCount + 1L;
		return "redirect:/board/list/1";
	}

	@RequestMapping(value = "/delete/{no}", method = RequestMethod.POST)
	public String delete(@PathVariable(value = "no") Long no) {
		boardService.deleteBoard(no);
		return "redirect:/board/list/1";
	}


	@RequestMapping(value = "/view/{no}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable(value = "no") Long no) {
		BoardVo vo = boardService.getBoardAndHitUpdate(no);
		model.addAttribute("board", vo);
		return "board/view";
	}


	@RequestMapping(value = "/modify/{no}", method = RequestMethod.POST)
	public String modify(Model model, @PathVariable(value = "no") Long no, HttpSession session) {
		BoardVo vo = boardService.getBoard(no);
		model.addAttribute("board", vo);
		return "board/modify";
	}

	@RequestMapping(value = "/domodify", method = RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo vo) {
		boardService.modify(vo);
		return "redirect:/board/list/1";
	}

	@Auth(role=Auth.Role.USER)
	@RequestMapping(value = "/writereply/{no}", method = RequestMethod.POST)
	public String writeReply(Model model, @PathVariable(value = "no") Long parent_no, HttpSession session) {
		model.addAttribute("parent_no", parent_no);
		return "board/writeReply";
	}

	@RequestMapping(value = "/writereply", method = RequestMethod.POST)
	public String writeReply(@ModelAttribute BoardVo vo,
			@RequestParam(value = "user_no", required = true, defaultValue = "0") Long user_no,
			@RequestParam(value = "parent_no", required = true, defaultValue = "0") Long parent_no) {
		vo.setUser_no(user_no);
		vo.setParent_no(parent_no);
		vo.setNo(boardService.writeReply(vo));
		boardService.updateByReplyAdd(vo);
		totalCount = totalCount + 1L;
		return "redirect:/board/list/1";
	}

	@RequestMapping(value = "/search/{no}", method = RequestMethod.POST)
	public String search(@RequestParam(value = "kwd", required = true, defaultValue = "") String kwd, @PathVariable(value = "no") Long no, Model model)
			throws JsonProcessingException {
		long countTop = (no - 1) * countPage;
		long count;

		if (totalCount <= countPage) {
			count = totalCount;
		} else if (no * countPage > totalCount) {
			count = totalCount - (no - 1) * countPage;
		} else {
			count = countPage;
		}
		
		List<BoardVo> list = boardService.search(kwd,countTop, count);
		ObjectMapper om = new ObjectMapper();
		String jsonList = om.writeValueAsString(list);
		model.addAttribute("jsonlist", jsonList);
		int pager = 0;
		if (totalCount <= countPage) {
			pager = 1;
		} else if ((int) (totalCount) % (int) (countPage) == 0) {
			pager = (int) (totalCount / countPage);
		} else {
			pager = ((int) (totalCount / countPage)) + 1;
		}
		model.addAttribute("nowPage", 1);
		model.addAttribute("countPage", countPage);
		model.addAttribute("pager", pager);
		return "board/list";
	}
}
