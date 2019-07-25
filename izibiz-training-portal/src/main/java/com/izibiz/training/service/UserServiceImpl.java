package com.izibiz.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.izibiz.training.dao.base.UserDao;
import com.izibiz.training.entity.User;
import com.izibiz.training.service.base.UserService;

@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User findByUsernanme(String username) {
		return userDao.findByUsernanme(username);
		
	}
	
	@Override
	public User findByUsernanmeHqlType(String username) {
		return userDao.findByUsernanmeHqlType(username);
		
	}
}
