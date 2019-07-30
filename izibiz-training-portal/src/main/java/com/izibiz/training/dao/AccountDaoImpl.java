package com.izibiz.training.dao;

import org.springframework.stereotype.Repository;

import com.izibiz.training.dao.base.AccountDao;
import com.izibiz.training.dao.common.GenericDaoHibernateImpl;
import com.izibiz.training.entity.Account;

@Repository
public class AccountDaoImpl extends GenericDaoHibernateImpl<Account> implements AccountDao{

	public AccountDaoImpl() {super(Account.class);}
	public AccountDaoImpl(Class<Account> persistentClass) {
		super(persistentClass);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		
	}

}
