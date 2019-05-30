package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public CategoryDao() {
		
	}
	
	public List<CategoryVo> get(String id) {
		List<CategoryVo> vo = sqlSession.selectList("category.getbyid",id);
		if(vo.isEmpty() == true) {
			return null;
		}
		
		return vo;
	}

	public List<CategoryVo> getAllInforbyid(String id) {
		List<CategoryVo> vo = sqlSession.selectList("category.getAllbyid",id);
		if(vo.isEmpty() == true) {
			return null;
		}
		
		return vo;
	}

	public Boolean add(CategoryVo vo) {
		return 1 == sqlSession.insert("category.insert",vo);
	}

	public Boolean delete(long category_no) {
		// TODO Auto-generated method stub
		return 1 == sqlSession.delete("category.delete",category_no);
	}

	public CategoryVo checkExistCategory(String blog_id, String category_name) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("blog_id",blog_id);
		map.put("name",category_name);
		return sqlSession.selectOne("category.checkExist",map);
	}
}
