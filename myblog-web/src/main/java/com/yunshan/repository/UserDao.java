package com.yunshan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.yunshan.domain.User;

public interface UserDao extends JpaRepository<User, Long> {
	
	@Query("FROM User u WHERE u.name = :name")
	User getUserByUsername(@Param("name") String username);
}
