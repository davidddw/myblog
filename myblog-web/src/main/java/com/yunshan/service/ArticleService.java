package com.yunshan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;
import com.yunshan.domain.vo.ArticleInfo;
import com.yunshan.domain.Article;
import com.yunshan.domain.Category;
import com.yunshan.domain.Tag;
import com.yunshan.repository.ArticleDao;
import com.yunshan.repository.CategoryDao;
import com.yunshan.repository.TagDao;
import com.yunshan.repository.UserDao;
import com.yunshan.utils.SetOpt;

@Service("articleService")
public class ArticleService {
	private ArticleDao articleDao;
	private UserDao userDao;
	private CategoryDao categoryDao;
	private TagDao tagDao;

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public ArticleDao getArticleDao() {
		return articleDao;
	}

	@Resource
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public TagDao getTagDao() {
		return tagDao;
	}

	@Resource
	public void setTagDao(TagDao tagDao) {
		this.tagDao = tagDao;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	@Resource
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public Article getArticleFromArticleInfo(ArticleInfo articleInfo) {
		long inputUserId = Long.parseLong(articleInfo.getUser());
		long inputCategoryId = Long.parseLong(articleInfo.getCategory());
		String articleId = articleInfo.getId();
		Article article = new Article(HtmlUtils.htmlEscape(articleInfo.getTitle()),
				articleInfo.getContent());
		article.setUser(userDao.findOne(inputUserId));
		article.setCategory(categoryDao.findOne(inputCategoryId));
		article.setModifiedDate(new Date());

		List<Tag> list = tagDao.findAll();
		article.setTagStrings(HtmlUtils.htmlEscape(articleInfo.getTags()));
		String[] newStrings = articleInfo.getTags().split(",");
		Set<Tag> tags = new HashSet<Tag>();
		for (int i = 0; i < newStrings.length; i++) {
			String escapeString = HtmlUtils.htmlEscape(newStrings[i]);
			Tag t = new Tag(escapeString);
			if (SetOpt.exist(list, escapeString, Tag.class)) {
				t = tagDao.getOneTagByName(escapeString);
			}
			tags.add(t);
		}
		article.getTags().addAll(tags);
		if (articleId != null) {
			article.setId(Integer.parseInt(articleId));
		}
		return article;
	}

	@Transactional
	public long getArticleCount() {
		return articleDao.count();
	}
	
	@Transactional
	public Article addNewArticleFromInfo(ArticleInfo articleInfo) {
		return articleDao.save(getArticleFromArticleInfo(articleInfo));
	}

	@Transactional
	public Article updateArticleFromInfo(ArticleInfo articleInfo) {
		return articleDao.save(getArticleFromArticleInfo(articleInfo));
	}

	@Transactional
	public boolean removeArticleById(long articleId) {
		Article article = articleDao.findOne(articleId);
		if (article == null) {
			return false;
		} else {
			for (Tag tag : article.getTags()) {
				int articleNumber = tag.getArticles().size();
				tag.setArticles(null);
				if (articleNumber == 1)
					tagDao.delete(tag.getId());
			}
			article.setTags(null);
			articleDao.delete(articleId);
			return true;
		}
	}

	@Transactional
	public Article updateArticle(Article article) {
		return articleDao.save(article);
	}

	@Transactional
	public Article findById(long articleId) {
		return articleDao.findOne(articleId);
	}

	@Transactional
	public List<Article> findAllArticles() {
		return articleDao.findAll();
	}

	private Sort sortByCreatedDateDesc() {
		return new Sort(Sort.Direction.DESC, "createdDate");
	}
	
	private Sort sortByPvDesc() {
		return new Sort(Sort.Direction.DESC, "pv");
	}

	private Pageable constructCreatedDatePageSpecification(int pageIndex,
			int pageSize) {
		Pageable pageSpecification = new PageRequest(pageIndex, pageSize,
				sortByCreatedDateDesc());
		return pageSpecification;
	}
	
	private Pageable constructPvPageSpecification(int pageIndex,
			int pageSize) {
		Pageable pageSpecification = new PageRequest(pageIndex, pageSize,
				sortByPvDesc());
		return pageSpecification;
	}

	@Transactional
	public Page<Article> findLatestArticles(int pageIndex, int pageSize) {
		return articleDao.getArticlesByCreatedDate(
				constructCreatedDatePageSpecification(pageIndex, pageSize));
	}
	
	@Transactional
	public Page<Article> findMostPopArticles(int pageIndex, int pageSize) {
		return articleDao.getArticlesByPV(
				constructPvPageSpecification(pageIndex, pageSize));
	}
	
	@Transactional
	public long getArticleCountByCategory(long categoryId) {
		return articleDao.getArticleCountByCategoryId(categoryId);
	}
	
	@Transactional
	public Page<Article> findArticlesByCategoryId(long categoryId,
			int pageIndex, int pageSize) {
		return articleDao.getArticlesByCategoryId(categoryId, 
				constructCreatedDatePageSpecification(pageIndex, pageSize));
	}

	@Transactional
	public Page<Article> findArticlesFromCategory(Category category,
			int pageIndex, int pageSize) {
		return articleDao.getArticlesByCategory(
				category.getName(),
				constructCreatedDatePageSpecification(pageIndex, pageSize));
	}

	@Transactional
	public Page<Article> findArticlesFromTag(Tag tag, int pageIndex,
			int pageSize) {
		return articleDao.getArticlesByTags(tag.getName(),
				constructCreatedDatePageSpecification(pageIndex, pageSize));
	}

	@Transactional
	public HashMap<String, Article> findPrevAndNextArticle(Article article) {
		HashMap<String, Article> prevNextArticle = new HashMap<String, Article>();
		List<Article> prevArticle = articleDao.getPrevArticles(article
				.getCreatedDate());
		List<Article> nextArticle = articleDao.getNextArticles(article
				.getCreatedDate());
		prevNextArticle.put("prev_article",
				prevArticle.size() > 0 ? prevArticle.get(0) : null);
		prevNextArticle.put("next_article",
				nextArticle.size() > 0 ? nextArticle.get(0) : null);
		return prevNextArticle;
	}

	@Transactional
	public List<HashMap<String, Object>> findArticlesInSameTag(Article article,
			int pageIndex, int pageSize) {
		List<HashMap<String, Object>> relate_tags = new ArrayList<HashMap<String, Object>>();
		for (Article m : articleDao.getArticlesInSameTag(article.getId(),
				constructCreatedDatePageSpecification(pageIndex, pageSize))
				.getContent()) {
			HashMap<String, Object> temp = new HashMap<String, Object>();
			temp.put("id", m.getId());
			temp.put("title", m.getTitle());
			relate_tags.add(temp);
		}
		return relate_tags;
	}
}
