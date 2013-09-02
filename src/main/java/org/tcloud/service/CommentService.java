package org.tcloud.service;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;
import org.tcloud.controller.vo.CommentInfo;
import org.tcloud.domain.Article;
import org.tcloud.domain.Comment;
import org.tcloud.repository.CommentDao;

@Service("commentService")
public class CommentService {
	private CommentDao commentDao;
	
	public CommentDao getCommentDao() {
		return commentDao;
	}

	@Resource
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	
	@Transactional
	public long getCommentCount() {
		return commentDao.count();
	}
	
	@Transactional
	public long getCommentCountByArticle(long articleId) {
		return commentDao.findAllByArticle(articleId);
	}
	
	@Transactional
	public Comment addNewComment(CommentInfo commentInfo, Article article){
		Comment com = new Comment(HtmlUtils.htmlEscape(commentInfo.getSubmitter()), 
				HtmlUtils.htmlEscape(commentInfo.getEmail()), HtmlUtils.htmlEscape(commentInfo.getContent()));
		com.setArticle(article);
		article.setCommentCount(article.getCommentCount()+1);
		return commentDao.save(com);
	}
	
	@Transactional
	public Comment findById(long commentId){
		return commentDao.findOne(commentId);
	}
	
	@Transactional
	public Page<Comment> findAllByArticle(long articleId, int pageIndex, int pageSize){
		return commentDao.findAllByArticle(articleId, constructPageSpecificationASC(pageIndex, 
				pageSize));
	}
	
	private Sort sortBySubmitDateDesc() {
		return new Sort(Sort.Direction.DESC, "submitDate");
	}
	
	private Sort sortBySubmitDate() {
		return new Sort(Sort.Direction.ASC, "submitDate");
	}

	private Pageable constructPageSpecification(int pageIndex, int pageSize) {
		Pageable pageSpecification = new PageRequest(pageIndex, pageSize, 
				sortBySubmitDateDesc());
		return pageSpecification;
	}
	
	private Pageable constructPageSpecificationASC(int pageIndex, int pageSize) {
		Pageable pageSpecification = new PageRequest(pageIndex, pageSize, 
				sortBySubmitDate());
		return pageSpecification;
	}
	
	@Transactional
	public Page<Comment> findLatestComments(int pageIndex, int pageSize){
		return commentDao.findAllBySubmitDateDesc(constructPageSpecification(pageIndex, 
				pageSize));
	}
	
	@Transactional 
	public Comment updateComment(Comment comment){
		return commentDao.save(comment);
	}
	
	@Transactional 
	public boolean removeComment(Comment comment){
		Comment c = commentDao.findOne(comment.getId());
		if(c==null){
			return false;
		} else {
			Article article = c.getArticle();
			article.setCommentCount(article.getCommentCount()-1);
			commentDao.delete(c);
			return true;
		}
	}
	
	@Transactional 
	public boolean removeCommentById(long commentId){
		Comment c = commentDao.findOne(commentId);
		if(c==null){
			return false;
		} else {
			Article article = c.getArticle();
			article.setCommentCount(article.getCommentCount()-1);
			commentDao.delete(c);
			return true;
		}
	}
}
