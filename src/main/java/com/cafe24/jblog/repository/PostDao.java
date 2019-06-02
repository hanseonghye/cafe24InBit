package com.cafe24.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.PostVo;

@Repository
public class PostDao {
	@Autowired
	private SqlSession sqlSession;

	public PostDao() {

	}

	public List<PostVo> get(String blog_id, long category_no) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("blog_id",blog_id);
		map.put("no",String.valueOf(category_no));
		List<PostVo> postVo = sqlSession.selectList("post.get", map);

		if (postVo.isEmpty() == true) {
			return null;
		}

		return postVo;
	}

	public List<PostVo> getFromAllCategory(String blog_id) {
		List<PostVo> postVo = sqlSession.selectList("post.getbyidFromAllCategory", blog_id);

		if (postVo.isEmpty() == true) {
			return null;
		}

		return postVo;
	}

	public PostVo getRecentOne(String blog_id) {
		List<PostVo> postVo = sqlSession.selectList("post.getRecentOneFromAll", blog_id);
		if (postVo.isEmpty() == true) {
			return null;
		}

		return postVo.get(0);
	}

	public PostVo getRecentOne(String blog_id, long category) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("blog_id",blog_id);
		map.put("no",String.valueOf(category));
		List<PostVo> postVo = sqlSession.selectList("post.getRecentOne", map);

		if (postVo.isEmpty() == true) {
			return null;
		}

		return postVo.get(0);
	}

	public Boolean write(String title, String content, String blog_id, String category_name) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", title);
		map.put("content", content);
		map.put("id", blog_id);
		map.put("name", category_name);
		return 1 == sqlSession.insert("post.insert", map);
	}

	public PostVo getWantPost(long post) {
		List<PostVo> postVo = sqlSession.selectList("post.getWantOne", post);

		if (postVo.isEmpty() == true) {
			return null;
		}

		return postVo.get(0);
	}

	public Boolean delete(long category_no) {
		return 1 == sqlSession.delete("post.delete",category_no);
	}

}
