package com.cafe24.jblog.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.PostDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;
	@Autowired
	ServletContext servletContext;
	

	private static final String SAVE_PATH = "/jblog-uploads";
	private static final String URL = "/images";

	@PostConstruct
	public void init() throws IOException {
		File Folder = new File(SAVE_PATH);
		if (!Folder.exists()) {
			Folder.mkdir();
			System.out.println(SAVE_PATH+" 폴더가 생성됐습니다-----");
		}
	}
	
	public Model viewPost(Model model, String blog_id, long category_no, long post_no) {
		BlogVo blogVo = getBlog(blog_id);
		PostVo wantPost = getWantPost(blog_id,category_no ,post_no);
		List<CategoryVo> cateList = getCategory(blog_id);
		List<PostVo> postList = getPost(blog_id, category_no);
		model.addAttribute("category", cateList);
		model.addAttribute("post", postList);
		model.addAttribute("blog", blogVo);
		model.addAttribute("recentpost", wantPost);
		
		return model;
	}

	public Boolean createBlog(String id) {
		Boolean result = blogDao.insert(id);
		firstCategory(id);
		return result;
	}

	public BlogVo getBlog(String id) {
		return blogDao.get(id);
	}

	public List<CategoryVo> getCategory(String id) {
		return categoryDao.get(id);
	}

	public List<PostVo> getPost(String blog_id, long category_no) {
		if ( category_no == 0L ) {
			return postDao.getFromAllCategory(blog_id);
		}
		return postDao.get(blog_id,category_no);
	}

	public PostVo getRecentPost(String blog_id, long category) {
		if ( category == 0L ) {
			return postDao.getRecentOne(blog_id);
		}
		return postDao.getRecentOne(blog_id,category);
	}

	public List<CategoryVo> getAllcategoryInfor(String id) {
		return categoryDao.getAllInforbyid(id);
	}

	public Boolean addCategory(CategoryVo vo) {
		return categoryDao.add(vo);
	}

	public Boolean write(String title,String content,String blog_id,String category_name) {
		return postDao.write(title, content, blog_id, category_name);
	}

	public String saveLogoFile(MultipartFile multipartFile) {
		String url = "";
		if (multipartFile.isEmpty()) {
			return url;
		}

		String originalFileName = multipartFile.getOriginalFilename();
		String saveFileName = generateSaveFileName(originalFileName);

		try {
			byte[] fileData = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			os.write(fileData);
			os.close();
			
			url = URL +"/"+saveFileName;
		} catch (IOException e) {
			throw new RuntimeException("Fileupload error : " + e);
		}

		return url;

	}

	private String generateSaveFileName(String originalFileName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += originalFileName;

		return fileName;
	}

	public Boolean updateBlog_(BlogVo vo) {
		return blogDao.update(vo);
	}

	public Boolean firstCategory(String id) {
		CategoryVo vo = new CategoryVo();
		vo.setId(id);
		vo.setDescription("기본 카테고리 입니다");
		vo.setName("새 카테고리");
		return categoryDao.add(vo);
	}

	public PostVo getWantPost(String blog_id,long category_no, long post) {
		if (post == 0L ) {
			if (category_no == 0L) {
				return postDao.getRecentOne(blog_id);
			}else {
				return postDao.getRecentOne(blog_id, category_no);
			}
		}
		
		if ( category_no == 0L) {
			return postDao.getRecentOneByPostNo(post);
		}
		
		return postDao.getWantPost(post);
	}

	public Boolean deleteCategory(long category_no) {
		postDao.delete(category_no);
		return categoryDao.delete(category_no);
	}

	public Boolean updateBlog(MultipartFile multipartFile, BlogVo vo) {
		String url = saveLogoFile(multipartFile);
		vo.setLogo(url);
		return updateBlog_(vo);
	}

	public Boolean checkExistCategory(String blog_id, String category_name) {
		CategoryVo vo = categoryDao.checkExistCategory(blog_id, category_name);
		return vo == null;
	}

}
