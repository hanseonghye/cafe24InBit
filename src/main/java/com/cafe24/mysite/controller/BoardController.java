package com.cafe24.mysite.controller;

import java.util.List;

import javax.annotation.PostConstruct;

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

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	private long page = 1;
	private long totalCount;
	public static long countPage = 3;
	

	@PostConstruct
	public void init() {
		this.totalCount = boardService.getTotalCount();
	}
	@RequestMapping({ "", "/", "/list" })
	public String list(Model model) {
		System.out.println("all count ----> "+totalCount);
		List<BoardVo> list = boardService.getList(1L, totalCount);
		model.addAttribute("list", list);
		int pager = 0;
		if (totalCount <= countPage) {
			pager = 1;
		}else if ( (int)(totalCount)%(int)(countPage) == 0 ) {
			pager = (int) (totalCount/countPage);
		}else {
			pager = ((int) (totalCount/countPage)) +1;
		}
		model.addAttribute("pager",pager);
		return "board/list";
	}
	
	@RequestMapping(value={"/list/{no}"}, method=RequestMethod.GET)
	public  String listPage(Model model, @PathVariable(value = "no") Long no) {
		List<BoardVo> list = boardService.getList(no, totalCount);
		model.addAttribute("list", list);
		int pager = 0;
		if (totalCount <= countPage) {
			pager = 1;
		}else if ( (int)(totalCount)%(int)(countPage) == 0 ) {
			pager = (int) (totalCount/countPage);
		}else {
			pager = ((int) (totalCount/countPage)) +1;
		}
		model.addAttribute("pager",pager);
		return "board/list";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute BoardVo vo,
			@RequestParam(value = "user_no", required = true, defaultValue = "0") Long user_no) {
		vo.setUser_no(user_no);
		boardService.write(vo);
		totalCount = totalCount +1L;
		return "redirect:/board/list/1";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value = "no", required = true, defaultValue = "0") Long no) {
		return null;
	}

	@RequestMapping(value = "/view/{group_no}/{order_no}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable(value = "group_no") Long group_no, @PathVariable(value = "order_no") Long order_no) {
		BoardVo vo = boardService.getBoard(group_no, order_no);
		model.addAttribute("board", vo);
		return "board/view";
	}

	@RequestMapping(value = "/modify/{group_no}/{order_no}", method = RequestMethod.GET)
	public String modify(Model model,@PathVariable(value = "group_no") Long group_no, @PathVariable(value = "order_no") Long order_no) {
		BoardVo vo = boardService.getBoard(group_no, order_no);
		model.addAttribute("board", vo);
		return "board/modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo vo) {
		boardService.modify(vo);
		return "redirect:/board/list/1";
	}
	
	@RequestMapping(value="/writereply/{no}", method=RequestMethod.GET)
	public String writeReply(Model model,@PathVariable(value = "no") Long parent_no) {
		model.addAttribute("parent_no",parent_no);
		return "board/writeReply";
	}
	
	@RequestMapping(value = "/writereply", method = RequestMethod.POST)
	public String writeReply(@ModelAttribute BoardVo vo,
			@RequestParam(value = "user_no", required = true, defaultValue = "0") Long user_no,
			@RequestParam(value = "parent_no", required = true, defaultValue = "0") Long parent_no) {
		vo.setUser_no(user_no);
		vo.setParent_no(parent_no);
		System.out.println("parent-->"+parent_no);
		vo.setNo(boardService.writeReply(vo));
		System.out.println(vo.toString());
		boardService.updateByReplyAdd(vo);
		totalCount = totalCount +1L;
		return "redirect:/board/list/1";
	}
}
