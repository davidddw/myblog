package com.yunshan.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yunshan.domain.Category;
import com.yunshan.repository.CategoryDao;

@Service("categoryService")
public class CategoryService {
	private CategoryDao categoryDao;
	
	@Resource
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}
	
	@Transactional
	public long getCategoryCount() {
		return categoryDao.count();
	}
	
	@Transactional 
	public Category findById(long categoryId) {
		return categoryDao.findOne(categoryId);
	}
	
	@Transactional 
	public Category findByClass(Category category) {
		return categoryDao.findOne(category.getId());
	}
	
	@Transactional 
	public List<Category> findAllCategories() {
		return categoryDao.getCategories();
	}
	
	@Transactional 
	public Page<Category> findAllCategories(int pageIndex, int pageSize) {
		return categoryDao.findAll(new PageRequest(pageIndex,pageSize));
	}
	
	@Transactional 
	public Category addNewCategory(Category category){
		return categoryDao.save(category);
	}
	
	@Transactional 
	public Category updateCategory(Category category){
		return categoryDao.save(category);
	}
	
	@Transactional 
	public boolean removeCategory(Category category){
		Category c = categoryDao.findOne(category.getId());
		if(c==null){
			return false;
		} else {
			categoryDao.delete(c);
			return true;
		}
	}
	
	@Transactional
	public boolean removeCategoryById(long categoryId) {
		Category c = categoryDao.findOne(categoryId);
		if(c==null){
			return false;
		} else {
			categoryDao.delete(c);
			return true;
		}
	}
	
}
