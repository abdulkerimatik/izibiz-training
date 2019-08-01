package com.izibiz.training.bean.base;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import com.izibiz.training.service.base.AccountService;
import com.izibiz.training.service.base.ArchiveService;
import com.izibiz.training.service.base.DespatchService;
import com.izibiz.training.service.base.InvoiceService;
import com.izibiz.training.service.base.UserService;

public class GenericBean<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final String bundleName = "com.izibiz.training.locale.messages";


	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	@ManagedProperty(value="#{despatchService}")
	private DespatchService despatchService;
	
	@ManagedProperty(value="#{archiveService}")
	private ArchiveService archiveService;
	
	
	@ManagedProperty( value="#{accountService}")
	private AccountService accountService;
	
	@ManagedProperty( value="#{invoiceService}")
	private InvoiceService invoiceService;
	
	public String getResourceBundleMessage(String key) {
		if (StringUtils.isEmpty(key)) {
			return "";
		}
		return getResourceBundle().getString(key);
	}

	private ResourceBundle getResourceBundle() {
		return ResourceBundle.getBundle(bundleName, FacesContext.getCurrentInstance().getApplication().getDefaultLocale());
	}

	public void addInfoMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addWarnMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addErrorMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public DespatchService getDespatchService() {
		return despatchService;
	}

	public void setDespatchService(DespatchService despatchService) {
		this.despatchService = despatchService;
	}

	public ArchiveService getArchiveService() {
		return archiveService;
	}

	public void setArchiveService(ArchiveService archiveService) {
		this.archiveService = archiveService;
	}
	
	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public InvoiceService getInvoiceService() {
		return invoiceService;
	}

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

}
