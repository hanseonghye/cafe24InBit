package com.cafe24.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	@Autowired
	private SqlSession sqlSession;
	
	public BlogDao() {
		
	}
	
	public Boolean insert(String id) {
		return 1 == sqlSession.insert("blog.insert", id);
	}

	public BlogVo get(String id) {
		List<BlogVo> blogVo = sqlSession.selectList("blog.getbyid",id);
		
		if(blogVo.isEmpty() == true) {
			return null;
		}
		
		return blogVo.get(0);
	}

	public Boolean update(BlogVo vo) {
		return 1 == sqlSession.update("blog.update",vo);
	}
}
