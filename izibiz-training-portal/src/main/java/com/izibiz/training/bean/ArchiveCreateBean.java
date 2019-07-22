package com.izibiz.training.bean;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.izibiz.training.bean.base.GenericBean;
import com.izibiz.training.entity.dto.ArchiveDTO;

@ViewScoped
@ManagedBean
public class ArchiveCreateBean extends GenericBean<ArchiveDTO>{
	
	private String query;
	private boolean itemSelected= false;
	
	public ArrayList<String> complete(String query) {
		ArrayList<String> customers = new ArrayList<String>();
		customers.add("eren");
		customers.add("veysel");
		customers.add("hasan");
		customers.add("gamze");
        
		ArrayList<String> results= new ArrayList<String>();
        
        for (String customer : customers) {
			if(customer.startsWith(query))
				results.add(customer);
		}
         
        return results;
    }
	
	
	public void onSelect(AjaxBehaviorEvent event) {
		setItemSelected(true);
	}
	
	
	
	
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public boolean isItemSelected() {
		return itemSelected;
	}
	public void setItemSelected(boolean itemSelected) {
		this.itemSelected = itemSelected;
	}
	
	
	
}
