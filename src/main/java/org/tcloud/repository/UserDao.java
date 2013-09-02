package org.tcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tcloud.domain.User;

public interface UserDao extends JpaRepository<User, Long> {
	
	@Query("FROM User u WHERE u.name = :name")
	User findByUserName(@Param("name") String username);
}
