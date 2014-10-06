package com.yunshan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.yunshan.domain.Tag;

public interface TagDao extends JpaRepository<Tag, Long> {
	
	List<Tag> findByName(String name);
	
	@Query("FROM Tag t WHERE t.name = :tagValue")
	Tag getOneTagByName(@Param("tagValue") String tagValue);
	
	
}
