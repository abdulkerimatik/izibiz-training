package com.izibiz.training.service.base;

import com.izibiz.training.entity.User;

public interface UserService {

	User findByUsernanme(String username);

	User findByUsernanmeHqlType(String username);

}
