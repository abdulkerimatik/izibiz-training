package com.izibiz.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.izibiz.training.dao.base.AccountDao;
import com.izibiz.training.entity.Account;
import com.izibiz.training.service.base.AccountService;

@Transactional
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountDao accountDao;
	
	@Override
	public List<Account> getAllAccounts() {
		return accountDao.getAll();
	}

}
