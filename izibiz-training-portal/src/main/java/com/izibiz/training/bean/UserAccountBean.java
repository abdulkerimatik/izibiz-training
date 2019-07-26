package com.izibiz.training.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.izibiz.training.bean.base.GenericBean;
import com.izibiz.training.entity.Account;
import com.izibiz.training.entity.User;

@ManagedBean
@ViewScoped
public class UserAccountBean extends GenericBean<UserAccountBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Account selectedAccount;
	private List<Account> accountList;
	private List<User> userList;
	
	public void openPage() {
		accountList =  getAccountService().getAllAccounts();
		userList = accountList.get(0).getUsers();
	}
	
	public void accountChanged() {
		System.out.println("selected account changed: "+selectedAccount.getAccountName());
		userList = selectedAccount.getUsers();
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public Account getSelectedAccount() {
		return selectedAccount;
	}

	public void setSelectedAccount(Account selectedAccount) {
		this.selectedAccount = selectedAccount;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	
	
}
