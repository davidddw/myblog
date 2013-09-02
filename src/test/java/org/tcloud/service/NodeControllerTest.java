package org.tcloud.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.tcloud.controller.vo.ArticleInfo;
import org.tcloud.controller.vo.CommentInfo;
import org.tcloud.domain.Article;
import org.tcloud.domain.Article.CommentStatusType;
import org.tcloud.domain.Category;
import org.tcloud.domain.GlobalSetting;
import org.tcloud.domain.Tag;
import org.tcloud.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:conf/applicationContext.xml")
@TransactionConfiguration
@Transactional
public class NodeControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private UserService userService;
	@Resource
	private GlobalSettingService globalSettingService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private CommentService commentService;
	@Resource
	private ArticleService articleService;
	@Resource
	private TagService tagService;
    
    @Test
    @Rollback(false)
    public void test01AddUserTest() {  
    	User u = new User("admin","password");
        userService.addNewUser(u);
    }
    
    @Test
    @Rollback(false)
    public void test02FindUserTest() {  
    	User u = new User("admin", "password");
    	u.setId(1);
        System.out.println(userService.findById(u.getId()));
    }
    
    @Test
    @Rollback(false)
    public void test03DeleteUserTest() {  
    	User u = new User("admin", "password");
    	u.setId(1);
        System.out.println(userService.removeUserById(u.getId()));
    }
    
    @Test
    @Rollback(false)
    public void test11AddGlobalSettingTest() {  
    	globalSettingService.addNewGlobalSetting(new GlobalSetting("url", "http://127.0.0.1/myBlog"));
    	globalSettingService.addNewGlobalSetting(new GlobalSetting("title", "极客集"));
    	globalSettingService.addNewGlobalSetting(new GlobalSetting("pagePer", "5"));
    }
    
    @Test
    @Rollback(false)
    public void test12findGlobalSettingTest() {  
    	test11AddGlobalSettingTest();
    	HashMap<String, String> h = globalSettingService.findAllGlobalSettings();
    	Iterator<Entry<String, String>> iter = h.entrySet().iterator();
    	while (iter.hasNext()) {
    	    Entry<String, String> entry = iter.next();
    	    System.out.println(entry.getKey()+" : "+ entry.getValue());
    	} 
    }
    
    @Test
    @Rollback(false)
    public void test21addCatagoryTest() {  
    	categoryService.addNewCategory(new Category("gadget", "奇趣酷玩"));
    	categoryService.addNewCategory(new Category("home", "家居/生活"));
    	categoryService.addNewCategory(new Category("mobile", "手机/无线"));
    	categoryService.addNewCategory(new Category("digital", "数码/影音"));
    	categoryService.addNewCategory(new Category("computer", "电脑/硬件"));
    	categoryService.addNewCategory(new Category("game", "游戏周边"));
    	categoryService.addNewCategory(new Category("appliance", "家用电器"));
    	categoryService.addNewCategory(new Category("science", "科技/探索"));
    	categoryService.addNewCategory(new Category("vehicle", "交通工具"));
    	categoryService.addNewCategory(new Category("design", "创意设计"));
    	categoryService.addNewCategory(new Category("mess", "大杂烩"));
    }
    
    @Test
    @Rollback(false)
    public void test22findCatagoryTest() {
    	Category catagory = categoryService.findById(1);
    	System.out.println(catagory);
    }
    
    @Test
    @Rollback(false)
    public void test41addArticleTest() {
    	for(int i=0; i<5; i++){
    		ArticleInfo a = new ArticleInfo(null,"david is boy45!"+i, "注：如果是页面上输入的字符串，", "1", "1","david1");
    		articleService.addNewArticleFromInfo(a);
    	}
    }
    
    @Test
    @Rollback(false)
    public void test42findArticleTest() {
    	List<Article> articles = articleService.findLatestArticles(0, 3).getContent();
    	System.out.println(articles);
    	List<Article> articles2 = articleService.findLatestArticles(1, 3).getContent();
    	System.out.println(articles2);
    }
    
    @Test
    @Rollback(false)
    public void test43getCatagoryArticleTest() {
    	Category category = categoryService.findById(10);
    	List<Article> articles = articleService.findArticlesFromCategory(category, 0,3).getContent();
    	System.out.println(articles);
    }
    
    @Test
    @Rollback(false)
    public void test44getTagArticleTest() {
    	Tag tag = tagService.findByName("david1");
    	List<Article> articles = articleService.findArticlesFromTag(tag,  0,3).getContent();
    	System.out.println(articles);
    }
    
    @Test
    @Rollback(false)
    public void test45findArticleInSameTagTest() {
    	Article article = new Article();
    	article.setId(1);
    	for(HashMap<String, Object> s : articleService.findArticlesInSameTag(article,  0,3)){
    		System.out.println(s.get("id"));
    		System.out.println(s.get("title"));
    	}    		
    }
    
    @Test
    @Rollback(false)
    public void test46getPrevAndNextArticleTest() {
    	Article a = articleService.findById(1);
    	HashMap<String, Article> h =  articleService.findPrevAndNextArticle(a);
     	Iterator<Entry<String, Article>> iter = h.entrySet().iterator();
     	while (iter.hasNext()) {
     	    Entry<String, Article> entry = iter.next();
     	    System.out.println(entry.getKey()+" : "+ entry.getValue());
     	}  
    }
    
    @Test
    @Rollback(false)
    public void test47deleteArticleTest() {
    	articleService.removeArticleById(1);
    }
    
    @Test
    @Rollback(false)
    public void test52getTagTest() {
    	Tag tag = tagService.findByName("david1");
    	System.out.println(tag);
    	Tag tag2 = tagService.findByName("david");
    	System.out.println(tag2);
    }
    
    @Test
    @Rollback(false)
    public void test53checkTagTest() {
    	System.out.println(tagService.checkTagExistByName("1234"));
    }
    
    @Test
    @Rollback(false)
    public void test61addCommentTest() {
    	CommentInfo commentInfo = new CommentInfo("submitter", "david@sdf.com" ,null ,"String content");
    	Article article = articleService.findById(1);
    	if(article.getCommentStatus().equals(CommentStatusType.OPEN))
    		commentService.addNewComment(commentInfo, article);
    }
    
    @Test
    @Rollback(false)
    public void test99addAllTest() {
    	test01AddUserTest();
    	test11AddGlobalSettingTest();
    	test21addCatagoryTest();
    	test41addArticleTest();
    	test61addCommentTest();
    }
    
/*    @Test
    public void saveValidate() {
    	String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY, 4, null);
        BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, null, null, null);
        try {
			ImageIO.write(bim, "JPEG", new File("saved.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }*/

}
