package com.yunshan.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.yunshan.domain.Comment;

public interface CommentDao extends JpaRepository<Comment, Long>  {
	
	@Query("SELECT c FROM Comment c")  
	Page<Comment> getCommentsBySubmitDate(Pageable pageable); 
	
	@Query("SELECT COUNT(*) FROM Comment c INNER JOIN c.article a WHERE a.id = :article")  
	long getCommentCountByArticle(@Param("article") long articleId); 
	
	@Query("SELECT c FROM Comment c INNER JOIN c.article a WHERE a.id = :article")  
	Page<Comment> getCommentsByArticle(@Param("article") long articleId, Pageable pageable); 
	
}
