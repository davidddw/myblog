package com.yunshan.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yunshan.domain.Category;
import com.yunshan.domain.User;
import com.yunshan.service.ArticleService;
import com.yunshan.service.CategoryService;
import com.yunshan.service.CommentService;
import com.yunshan.service.OptionsService;
import com.yunshan.service.TagService;
import com.yunshan.service.UserService;

@SessionAttributes(value={"categories", "users", "options"})
public class BaseController {
	
	@Resource
	protected CategoryService categoryService;
	@Resource
	protected OptionsService optionsService;
	@Resource
	protected ArticleService articleService;
	@Resource
	protected CommentService commentService;
	@Resource
	protected TagService tagService;
	@Resource
	protected UserService userService;

	public TagService getTagService() {
		return tagService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public OptionsService getOptionsService() {
		return optionsService;
	}

	public void setOptionsService(OptionsService optionsService) {
		this.optionsService = optionsService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryService.findAllCategories();
	}
	
	@ModelAttribute("users")
	public List<User> getUsers() {
		return userService.findAllUsers();
	}
	
	@ModelAttribute("options")
	public HashMap<String, String> getOptions() {
		return optionsService.findAllOptions();
	}
}
