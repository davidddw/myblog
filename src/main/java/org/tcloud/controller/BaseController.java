package org.tcloud.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.tcloud.domain.Category;
import org.tcloud.domain.User;
import org.tcloud.service.ArticleService;
import org.tcloud.service.CategoryService;
import org.tcloud.service.CommentService;
import org.tcloud.service.GlobalSettingService;
import org.tcloud.service.TagService;
import org.tcloud.service.UserService;

@SessionAttributes(value={"categories", "users", "globalSetting"})
public class BaseController {
	
	@Resource
	protected CategoryService categoryService;
	@Resource
	protected GlobalSettingService globalSettingService;
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

	public GlobalSettingService getGlobalSettingService() {
		return globalSettingService;
	}

	public void setGlobalSettingService(GlobalSettingService globalSettingService) {
		this.globalSettingService = globalSettingService;
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
	
	@ModelAttribute("globalSetting")
	public HashMap<String, String> getGlobalSettings() {
		return globalSettingService.findAllGlobalSettings();
	}
}
