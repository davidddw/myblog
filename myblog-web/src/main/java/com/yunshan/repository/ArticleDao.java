package com.yunshan.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.yunshan.domain.Article;

public interface ArticleDao extends JpaRepository<Article, Long> {	
	
	@Query("SELECT a FROM Article a INNER JOIN a.tags t where t.name = :tagValue ")  
	Page<Article> getArticlesByTags(@Param("tagValue") String tagName, Pageable pageable);
	
	@Query("SELECT a FROM Article a INNER JOIN a.category c where c.name = :enName ")  
	Page<Article> getArticlesByCategory(@Param("enName") String categoryName, Pageable pageable);
	
	@Query("SELECT a FROM Article a INNER JOIN a.category c where c.id = :id ")  
	Page<Article> getArticlesByCategoryId(@Param("id") long categoryId, Pageable pageable);
	
	@Query("SELECT COUNT(*) FROM Article a INNER JOIN a.category c where c.id = :id ")
	long getArticleCountByCategoryId(@Param("id") long categoryId); 
	
	@Query("FROM Article a")  
	Page<Article> getArticlesByCreatedDate(Pageable pageable); 
	
	@Query("FROM Article a ")  
	Page<Article> getArticlesByPV(Pageable pageable); 

	@Query("FROM Article a WHERE a.createdDate < :now ORDER BY a.createdDate DESC, a.id DESC")
	List<Article> getPrevArticles(@Param("now") Date currentTime);
	
	@Query("FROM Article a WHERE a.createdDate> :now ORDER BY a.createdDate ASC, a.id ASC")
	List<Article> getNextArticles(@Param("now") Date currentTime);
	
	@Query("SELECT DISTINCT a FROM Article a INNER JOIN a.tags t "
			+ "WHERE a.id != :myid AND t.id in " 
			+ "(SELECT t.id FROM Tag t INNER JOIN t.articles a where a.id = :myid) ")
	Page<Article> getArticlesInSameTag(@Param("myid") long myId, Pageable pageable);
}
