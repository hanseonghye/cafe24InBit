package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public BoardDao() {
		
	}
	
	public Boolean contentinsert(BoardVo vo) {
		return 1 == sqlSession.insert("board.contentinsert",vo);
	}
	
	public List<BoardVo> getList(long countTop, long count) {
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("counttop",countTop);
		map.put("count",countTop+count);
		List<BoardVo> result = sqlSession.selectList("board.getlist",map);
		
		return result;
	}
	
	public List<BoardVo> getSearch(String kwd, long countTop, long count) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kwd",kwd);
		map.put("counttop",countTop);
		map.put("count",count);
		
		List<BoardVo> result = sqlSession.selectList("board.getsearch",kwd);
		return result;
	}

	public Boolean modify(BoardVo vo) {
		return 1 == sqlSession.update("board.update",vo);
	}

	public long getTotalCount() {
		return sqlSession.selectOne("board.getBoardCount");
	}
	public Long writeReply(BoardVo vo) {
		sqlSession.insert("board.insertReply",vo);
		return vo.getNo();
	}
	
	public Boolean updateByReplyAdd(BoardVo vo) {
		return 1 == sqlSession.update("board.updateByReplyAdd",vo.getNo());
	}
	public BoardVo getBoard(Long no) {
		return sqlSession.selectOne("board.getByNo",no);
	}
	public Boolean deleteBoard(Long no) {
		return 1 == sqlSession.update("board.updateBydelete",no);
	}
	public Boolean hitUpdate(Long no) {
		return 1 == sqlSession.update("board.updateHit",no);
	}

	public Long getTotalSearchCount(String kwd) {
		return sqlSession.selectOne("board.getTotalSearchCount", kwd);
	}

}
