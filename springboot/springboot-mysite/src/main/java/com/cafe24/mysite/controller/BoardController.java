package com.cafe24.mysite.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	private int totalCount;
	public static int countPage = 5;
	private int jumpPage = 3;
	private int listNumber;

	@PostConstruct
	public void init() {
		this.totalCount = (int) boardService.getTotalCount();
		this.listNumber = getListNumber(totalCount);
	}


	@RequestMapping(value = { "/list/{no}" }, method = RequestMethod.GET)
	public String listPage(Model model, @PathVariable(value = "no") int no) throws JsonProcessingException {
		System.out.println("---> " + totalCount);
		long countTop = (no - 1) * countPage;
		long count = getHowManyBoardGet(no,totalCount);

		List<BoardVo> list = boardService.getList(countTop, count);
		model.addAttribute("list", list);

		model.addAttribute("nowPage", no);
		model.addAttribute("countPage", countPage);
		model.addAttribute("pager", listNumber);
		model.addAttribute("jumppage",jumpPage);
		return "board/list";
	}
	
	@Auth(role=Auth.Role.USER)
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}


	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute @Valid BoardVo vo,
			@RequestParam(value = "user_no", required = true, defaultValue = "0") Long user_no) {
		vo.setUser_no(user_no);
		boardService.write(vo);
		totalCount = totalCount + 1;
		listNumber = getListNumber(totalCount);
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
	public String modify(@ModelAttribute @Valid BoardVo vo) {
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
	public String writeReply(@ModelAttribute @Valid BoardVo vo,
			@RequestParam(value = "user_no", required = true, defaultValue = "0") Long user_no,
			@RequestParam(value = "parent_no", required = true, defaultValue = "0") Long parent_no) {
		vo.setUser_no(user_no);
		vo.setParent_no(parent_no);
		vo.setNo(boardService.writeReply(vo));
		boardService.updateByReplyAdd(vo);
		totalCount = totalCount + 1;
		listNumber = getListNumber(totalCount);
		return "redirect:/board/list/1";
	}

	@RequestMapping(value = "/search/{no}", method = RequestMethod.POST)
	public String search(@RequestParam(value = "kwd", required = true, defaultValue = "") String kwd, @PathVariable(value = "no") int no, Model model)
			throws JsonProcessingException {
		int countTop =  ((no - 1) * countPage);
		
		long searchTotalCount = boardService.getTotalSearchCount(kwd);
		
		int count = getHowManyBoardGet(no, (int)searchTotalCount);
		List<BoardVo> list = boardService.search(kwd,countTop, count);
		model.addAttribute("list",list);
		model.addAttribute("nowPage", 1);
		model.addAttribute("jumppage", jumpPage);
		model.addAttribute("countPage", countPage);
		model.addAttribute("pager", getListNumber((int)searchTotalCount));
		return "board/list";
	}
	
	public int getHowManyBoardGet(int no, int totalcount) {
		if (no* countPage > totalCount) {
			return totalCount - (no-1)*countPage;
		}
		return countPage;
	}
	
	public int getListNumber(int totalcount) {
		if ( totalCount%countPage == 0 ) {
			return (int)(totalCount / countPage);
		}
		
		return (int)(totalCount/countPage) + 1;
		
	}
}
