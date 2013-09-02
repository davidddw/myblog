package org.tcloud.service;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tcloud.domain.Article;
import org.tcloud.domain.Tag;
import org.tcloud.repository.TagDao;
import org.tcloud.support.SetOpt;

@Service("tagService")
public class TagService {
	private TagDao tagDao;
	
	public TagDao getTagDao() {
		return tagDao;
	}

	@Resource
	public void setTagDao(TagDao tagDao) {
		this.tagDao = tagDao;
	}
	
	@Transactional
	public void addNewTag(String tagValue){
		Tag tag = new Tag(tagValue);
		tagDao.save(tag);
	}
	
	@Transactional 
	public boolean deleteByTagId(long tagId){
		Tag tag = tagDao.findOne(tagId);
		if(tag==null){
			return false;
		}else {
			for (Article article : tag.getArticles()){
				article.setTags(null);
			}
			tag.setArticles(null);
			tagDao.delete(tagId);
			return true;
		}
	}
	
	@Transactional 
	public Tag findById(long tagId) {
		return tagDao.findOne(tagId);
	}
	
	@Transactional 
	public Tag findByName(String tagName) {
		List<Tag> tags = tagDao.findByTagValue(tagName);
		return tags.size()>0 ? tags.get(0): null;
	}
	
	@Transactional 
	public boolean checkTagExistById(long id) {
		return tagDao.exists(id);
	}
	
	public boolean checkTagExistByName(String tagValue) {
		return tagDao.findByTagValue(tagValue).size()>0 ? true:false;
	}
	
	@Transactional 
	public void addTagFromStrings(String[] tagValues) {
		List<Tag> list = tagDao.findAll();
		Set<Tag> newTag = SetOpt.diff(list, tagValues, Tag.class);
		for(Tag tag : newTag){
			tagDao.save(tag);
		}
	}
}
