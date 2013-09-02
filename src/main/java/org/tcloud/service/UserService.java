package org.tcloud.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tcloud.domain.User;
import org.tcloud.repository.UserDao;

@Service("userService")
public class UserService {
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Transactional 
	public User findUserByName(String name) {
		return userDao.findByUserName(name);
	}
	
	@Transactional
	public long getUserCount() {
		return userDao.count();
	}
	
	@Transactional 
	public User findById(long userId) {
		return userDao.findOne(userId);
	}
	
	@Transactional 
	public List<User> findAllUsers() {
		return userDao.findAll();
	}
	
	@Transactional 
	public Page<User> findAllUsers(int pageIndex, int pageSize) {
		return userDao.findAll(new PageRequest(pageIndex,pageSize));
	}
	
	@Transactional 
	public User addNewUser(User user) {
		if(userDao.findByUserName(user.getName())!=null){
			return null; 
		} else {
			return userDao.save(user);
		}
	}
	
	@Transactional 
	public User updateUser(User user){
		return userDao.save(user);
	}
	
	@Transactional 
	public boolean removeUserById(long userId) {
		User u = userDao.findOne(userId);
		if(u == null){
			return false;
		}else {
			userDao.delete(userId);
			return true;
		}	
	}
}