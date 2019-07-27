package com.izibiz.training.dao.base;

import com.izibiz.training.dao.common.GenericDao;
import com.izibiz.training.entity.User;

public interface UserDao extends GenericDao<User>{

	User findByUsernanme(String username);

	User findByUsernanmeHqlType(String username);

	

}
