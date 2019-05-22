package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardVo;


@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	public Boolean write(BoardVo vo) {
		return boardDao.contentinsert(vo);
	}

	public List<BoardVo> getList(long number,  long totalCount) {
		return boardDao.getList(number, totalCount);
	}

	public BoardVo getBoard(Long group_no, Long order_no) {
		return boardDao.getBoard(group_no, order_no);
	}

	public Boolean modify(BoardVo vo) {
		return boardDao.modify(vo);
	}

	public long getTotalCount() {
		return boardDao.getTotalCount();
	}

	public Long writeReply(BoardVo vo) {
		return boardDao.writeReply(vo);
	}

	public Boolean updateByReplyAdd(BoardVo vo) {
		return boardDao.updateByReplyAdd(vo);
	}

	public BoardVo getBoard(Long no) {
		return boardDao.getBoard(no);
	}

	public Boolean deleteBoard(Long no) {
		return boardDao.deleteBoard(no);
	}

	public Boolean hitUpdate(Long no) {
		return boardDao.hitUpdate(no);
	}

	public List<BoardVo> search(String kwd, long countTop, long count) {
		return boardDao.getSearch(kwd, countTop, count);
	}
}
