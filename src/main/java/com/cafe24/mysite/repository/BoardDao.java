package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.controller.BoardController;
import com.cafe24.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	private long countPage;

	@Autowired
	private SqlSession sqlSession;
	
	public BoardDao() {
		
	}
	public Boolean contentinsert(BoardVo vo) {
		return 1 == sqlSession.insert("board.contentinsert",vo);
	}
	public List<BoardVo> getList(long page, long _totalCount) {
		long totalCount = _totalCount;
		countPage = BoardController.countPage;
		long countTop ;
		
		if (totalCount <= countPage) {
			countTop = 0;
		}else if (page*countPage > totalCount) {
			countTop = totalCount - (page -1 )*countPage;
		}else {
			countTop = (page -1)*countPage;
		}
		
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("countpage",countPage);
		map.put("counttop",countTop);
		List<BoardVo> result = sqlSession.selectList("board.getlist",map);

		return result;
	}
	public BoardVo getBoard(Long group_no, Long order_no) {
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("groupno",group_no);
		map.put("orderno",order_no);
		return sqlSession.selectOne("board.getByNo",map);
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
		return 1 == sqlSession.update("board.updateByReplyAdd",vo);
	}

}
