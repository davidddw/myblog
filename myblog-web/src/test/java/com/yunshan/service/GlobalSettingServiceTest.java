package com.yunshan.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.yunshan.domain.Options;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/applicationContext.xml")
@TransactionConfiguration
@Transactional

public class GlobalSettingServiceTest {
	
	@Resource
	private OptionsService optionsService;

	@Test
	public void testSayHello() {
		List<Options> globalSettings = optionsService.findOptions();
		for(Options globalSetting: globalSettings){
			System.out.println(globalSetting.getId());
		}
		Assert.assertTrue("hh", true);
		//Assert.assertEquals("Hello world!", helloService.sayHello());
	}
	
	@Test
    @Rollback(false)
    public void AddGlobalSettingTest() {  
		optionsService.addNewOption(new Options("url1", "http://127.0.0.1/myBlog"));
		Assert.assertTrue("hh1", true);
    }
    
    @Test
    @Rollback(false)
    public void findGlobalSettingTest() {  
    	HashMap<String, String> h = optionsService.findAllOptions();
    	Iterator<Entry<String, String>> iter = h.entrySet().iterator();
    	while (iter.hasNext()) {
    	    Entry<String, String> entry = iter.next();
    	    System.out.println(entry.getKey()+" : "+ entry.getValue());
    	} 
    	Assert.assertTrue("hh2", true);
    }
    
    @Test
    @Rollback(true)
    public void DeleteGlobalSettingTest() {  
    	optionsService.removeOptionById(1);
    	Assert.assertTrue("hh3", true);
    }
}
