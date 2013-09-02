package org.tcloud.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.tcloud.domain.GlobalSetting;

public interface GlobalSettingDao extends JpaRepository<GlobalSetting, Long> {
	@Query("SELECT g FROM GlobalSetting g")  
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
	List<GlobalSetting> findAllGlobalSettings();
}
