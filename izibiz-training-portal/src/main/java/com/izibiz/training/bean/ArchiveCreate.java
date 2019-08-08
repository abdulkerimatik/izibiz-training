package com.izibiz.training.bean;

import java.util.List;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.izibiz.training.bean.base.GenericBean;
import com.izibiz.training.entity.Archive;
import com.izibiz.training.entity.dto.ArchiveGDTO;
import com.izibiz.training.models.ArchiveLines;


@ViewScoped
@ManagedBean(name = "archiveCreateBean")
public class ArchiveCreate extends GenericBean<ArchiveGDTO> {

	
	private static final long serialVersionUID = -1337310654633095964L;
	
	private ArchiveGDTO newArchive;
	
	private List<ArchiveLines> listArchiveLines;
	
	
	
	public void clearNewArchive() {
		newArchive = new ArchiveGDTO();
		newArchive.setUuid(UUID.randomUUID().toString());
	}
	

	public boolean isValidateVknTckn() {

		if (newArchive.getReceiver() == null) {
			addErrorMessage("Vkn Tckn boş olamaz ");
			FacesContext.getCurrentInstance().validationFailed();
			return false;
		} 
		else if (newArchive.getReceiver().length() != 10 && newArchive.getReceiver().length() != 11) {
			FacesContext.getCurrentInstance().validationFailed();
			return false;
		}
		else if (!isNumeric(newArchive.getReceiver())) {
			addErrorMessage("sayılardan olusmalı");
			FacesContext.getCurrentInstance().validationFailed();
			return false;
		}
		return true;
	}
	
	
	private boolean isNumeric(String strNum) {
		try {
			Double.parseDouble(strNum);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	
	
	 public void onRowEdit(RowEditEvent event) {
	        FacesMessage msg = new FacesMessage("Car Edited");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	     
	 
	    public void onRowCancel(RowEditEvent event) {
	        FacesMessage msg = new FacesMessage("Edit Cancelled");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	 
	    
	    public void onAddNew() {
	        // Add one new car to the table:
	        Car car2Add = service.createCars(1).get(0);
	        cars1.add(car2Add);
	        FacesMessage msg = new FacesMessage("New Car added", car2Add.getId());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	
	
	
	
	

	public ArchiveGDTO getNewArchive() {
		return newArchive;
	}
	public void setNewArchive(ArchiveGDTO newArchive) {
		this.newArchive = newArchive;
	}
	public List<ArchiveGDTO> getListArchiveLines() {
		return listArchiveLines;
	}
	public void setListArchiveLines(List<ArchiveGDTO> listArchiveLines) {
		this.listArchiveLines = listArchiveLines;
	}
	
	
	

}
