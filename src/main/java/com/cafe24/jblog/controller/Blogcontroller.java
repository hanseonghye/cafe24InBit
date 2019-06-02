package com.cafe24.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.dto.JSONResult;
import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets|images).*}")
public class Blogcontroller {

	@Autowired
	private BlogService blogService;

	@Autowired
	UserService userService;
		
	@RequestMapping(value = { "", "/", "/{category}", "/{category}/{post}" }, method = RequestMethod.GET)
	public String viewPost(@PathVariable(value = "id") String blog_id,
			@PathVariable(value = "category") Optional<Long> _category,
			@PathVariable(value = "post") Optional<Long> _post, Model model) {
		Long category = _category.isPresent() ? _category.get() : 0L;
		Long post = _post.isPresent() ? _post.get() : 0L;
		model = blogService.viewPost(model, blog_id, category, post);
		model.addAttribute("blog_id", blog_id);
		return "blog/blog-main";
	}

	@RequestMapping(value = { "/admin/basic" }, method = RequestMethod.GET)
	public String basic(@PathVariable(value = "id") String blog_id, Model model) {
		BlogVo blogVo = blogService.getBlog(blog_id);
		model.addAttribute("blog", blogVo);
		model.addAttribute("blog_id", blog_id);
		return "blog/blog-admin-basic";
	}

	@RequestMapping(value = { "/admin/basic" }, method = RequestMethod.POST)
	public String basic(@PathVariable(value = "id") String blog_id, @ModelAttribute BlogVo vo,
			@RequestParam(value = "logo-file") MultipartFile multipartFile, Model model) {
		blogService.updateBlog(multipartFile, vo);
		return "redirect:/" + blog_id;
	}

	@RequestMapping(value = { "/admin/category" }, method = RequestMethod.GET)
	public String adminCategory(@PathVariable(value = "id") String blog_id, Model model) {
		List<CategoryVo> cateList = blogService.getAllcategoryInfor(blog_id);
		BlogVo blogVo = blogService.getBlog(blog_id);
		model.addAttribute("blog", blogVo);
		model.addAttribute("categoryList", cateList);
		model.addAttribute("blog_id", blog_id);
		return "blog/blog-admin-category";
	}

	@RequestMapping(value = { "/admin/write" }, method = RequestMethod.GET)
	public String adminWrite(@PathVariable(value = "id") String blog_id, Model model) {
		List<CategoryVo> voList = blogService.getCategory(blog_id);
		BlogVo blogVo = blogService.getBlog(blog_id);
		model.addAttribute("blog", blogVo);
		model.addAttribute("categoryList", voList);
		model.addAttribute("blog_id", blog_id);
		return "blog/blog-admin-write";
	}

	@RequestMapping(value = { "/admin/write" }, method = RequestMethod.POST)
	public String adminWrite(@PathVariable(value = "id") String blog_id, @ModelAttribute @Valid PostVo vo,
			@RequestParam(value = "category", required = true, defaultValue = "새 카테고리") String category, Model model) {
		blogService.write(vo.getTitle(), vo.getContent(), blog_id, category);
		model.addAttribute("blog_id", blog_id);
		return "redirect:/" + blog_id;
	}

	@ResponseBody
	@RequestMapping(value = { "/admin/addcategory" }, method = RequestMethod.POST)
	public JSONResult addCategory(@PathVariable(value = "id") String blog_id,
			@RequestParam(value = "name", required = true, defaultValue = "") String category_name,
			@RequestParam(value = "description", required = true, defaultValue = "") String description) {
		CategoryVo vo = new CategoryVo();
		vo.setId(blog_id);
		vo.setName(category_name);
		vo.setDescription(description);
		if (blogService.checkExistCategory(blog_id, category_name) == false) {
			// 이미 있는 경우.
			return JSONResult.success("NO", null);
		}

		blogService.addCategory(vo);
		List<CategoryVo> cateList = blogService.getAllcategoryInfor(blog_id);
		return JSONResult.success("OK", cateList);
	}

	@ResponseBody
	@RequestMapping(value = { "/admin/delete/{category}" }, method = RequestMethod.POST)
	public JSONResult deleteCategory(@PathVariable(value = "id") String blog_id,
			@PathVariable(value = "category") long category) {
		blogService.deleteCategory(category);
		List<CategoryVo> cateList = blogService.getAllcategoryInfor(blog_id);

		return JSONResult.success("OK", cateList);
	}

	@ResponseBody
	@RequestMapping(value = { "/api/checkid" }, method = RequestMethod.GET)
	public JSONResult checkId(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
		Boolean exist = userService.existID(id);
		return JSONResult.success("OK", exist);
	}

}
