package com.izibiz.training.dao;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.izibiz.training.dao.base.UserDao;
import com.izibiz.training.dao.common.GenericDaoHibernateImpl;
import com.izibiz.training.entity.User;

@Repository
public class UserDaoImpl extends GenericDaoHibernateImpl<User> implements UserDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3473952407068379869L;



	public UserDaoImpl() {
		super(User.class);
	}

	
	
	@Override
	public User findByUsernanme(String username) {
	
		String sql="select * from users where username=:username";
		
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql).addEntity(User.class);
		sqlQuery.setParameter("username", username);
		User user=(User) sqlQuery.uniqueResult();
		return user;
	}
	
	@Override
	public User findByUsernanmeHqlType(String username) {
	
		String sql="select * from User where username=:username";
		
		Query query = getCurrentSession().createQuery(sql);
		query.setParameter("username", username);
		User user=(User) query.uniqueResult();
		return user;
		
	}
}
