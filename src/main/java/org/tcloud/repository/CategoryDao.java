package org.tcloud.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.tcloud.domain.Category;

public interface CategoryDao extends JpaRepository<Category, Long>  {
	
	@Query("SELECT c FROM Category c")  
	Page<Category> findAllCategories(Pageable pageable);
	
	@Query("SELECT c FROM Category c")  
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	List<Category> findAllCategories();
}
