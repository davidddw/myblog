package org.tcloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tcloud.domain.Tag;

public interface TagDao extends JpaRepository<Tag, Long> {
	
	List<Tag> findByTagValue(String name);
	
	@Query("FROM Tag t WHERE t.tagValue = :tagValue")
	Tag findOneByName(@Param("tagValue") String tagValue);
	
	
}
