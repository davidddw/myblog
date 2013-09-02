package org.tcloud.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tcloud.domain.Article;

public interface ArticleDao extends JpaRepository<Article, Long> {	
	
	@Query("SELECT a FROM Article a INNER JOIN a.tags t where t.tagValue = :tagValue ")  
	Page<Article> findByTagsOrderByCreatedDateDesc(@Param("tagValue") String tagName, Pageable pageable);
	
	@Query("SELECT a FROM Article a INNER JOIN a.category c where c.enName = :enName ")  
	Page<Article> findByCategoryOrderByCreatedDateDesc(@Param("enName") String categoryName, Pageable pageable);
	
	@Query("SELECT a FROM Article a INNER JOIN a.category c where c.id = :id ")  
	Page<Article> findByCategoryId(@Param("id") long categoryId, Pageable pageable);
	
	@Query("SELECT COUNT(*) FROM Article a INNER JOIN a.category c where c.id = :id ")
	long findByCategoryId(@Param("id") long categoryId); 
	
	@Query("FROM Article a")  
	Page<Article> findAllByCreatedDateDesc(Pageable pageable); 
	
	@Query("FROM Article a ")  
	Page<Article> findAllByPVDesc(Pageable pageable); 

	@Query("FROM Article a WHERE a.createdDate < :now ORDER BY a.createdDate DESC, a.id DESC")
	List<Article> findPrevArticle(@Param("now") Date currentTime);
	
	@Query("FROM Article a WHERE a.createdDate> :now ORDER BY a.createdDate ASC, a.id ASC")
	List<Article> findNextArticle(@Param("now") Date currentTime);
	
	@Query("SELECT DISTINCT a FROM Article a INNER JOIN a.tags t WHERE a.id != :myid " +
			"AND t.id in " +
			"(SELECT t.id FROM Tag t INNER JOIN t.articles a where a.id = :myid) ")
	Page<Article> findArticlesInSameTag(@Param("myid") Long myId, Pageable pageable);
}
