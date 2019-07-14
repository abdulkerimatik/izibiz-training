package com.izibiz.training.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "editor")
public class EditorBean {

	private String value = "eren Çelik";

	
	public void openEditorPage() {
		System.out.println("asdasd");
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}