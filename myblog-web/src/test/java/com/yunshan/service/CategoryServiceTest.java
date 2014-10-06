package com.yunshan.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.yunshan.domain.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/applicationContext.xml")
@TransactionConfiguration
@Transactional

public class CategoryServiceTest {
	
	@Resource
	private CategoryService categoryService;

	@Test
	public void testSayHello() {
		Page<Category> categories = categoryService.findAllCategories(1, 2);
		for(Category category: categories.getContent()){
			System.out.println(category.getName()+": "+category.getName());
		}
		//Assert.assertEquals("Hello world!", helloService.sayHello());
	}
    
    @Test
    @Rollback(false)
    public void DeleteGlobalSettingTest() {  
    	categoryService.removeCategoryById(9);
    }
}
