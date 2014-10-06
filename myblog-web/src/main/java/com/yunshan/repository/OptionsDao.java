package com.yunshan.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import com.yunshan.domain.Options;

public interface OptionsDao extends JpaRepository<Options, Long> {
	@Query("SELECT o FROM Options o")  
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	List<Options> getOptions();
}
