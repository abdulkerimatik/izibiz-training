package com.izibiz.training.bean.base;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

public class GenericBean<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	private static ResourceBundle msgBundle;
	private static ResourceBundle getMsgBundle() {
		if(msgBundle==null) {
			FacesContext context = FacesContext.getCurrentInstance();
			msgBundle = context.getApplication().evaluateExpressionGet(context, "#{msg}", ResourceBundle.class);
		}
		return msgBundle;
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
	
	public String getMsg(String prop) {
		
		return getMsgBundle().getString(prop);
	}

}
