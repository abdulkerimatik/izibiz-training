package com.izibiz.training.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import com.izibiz.training.bean.base.GenericBean;
import com.izibiz.training.entity.dto.DataRepo;
import com.izibiz.training.entity.dto.DespatchDTO;

@ManagedBean
@ViewScoped
public class DraftDespatchBean extends GenericBean<DraftDespatchBean> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 676708930015358326L;
	private DespatchDTO currentDraftDespatch;
	private DespatchDTO selectedDraftDespatch;
	private List<DespatchDTO> draftDespatchList = new ArrayList<>();
	private boolean validDespatch;
	public List<String> despatchSeriesList;
	public String selectedSeries;

	public void openDraftDespatchPage() {
		
		FacesContext.getCurrentInstance()
		.getViewRoot().setLocale(Locale.ENGLISH);
		
		selectedDraftDespatch = null;
		selectedSeries = "";
		clearCurrentDraftDespatch();
		draftDespatchList = new ArrayList<>();
		for (DespatchDTO des : DataRepo.despatches) {
			// if (des.getStatus().equals(DespatchDTO.LOAD))
			draftDespatchList.add(des);
		}
		
		/*
		 * DataRepo.despatches.forEach(x->{ if(x.getStatus().equals(DespatchDTO.LOAD)) {
		 * draftDespatchList.add(x); } });
		 */
	}

	public void saveDespatch() {
		if (!isValidDespatch()) {
			clearCurrentDraftDespatch();
			return;
		}
		this.currentDraftDespatch.setDespatchId(DespatchDTO.DEFAULT_SERIAL_ID
				+ (DespatchDTO.seriesMap.get(DespatchDTO.DEFAULT_SERIAL_ID).add(BigDecimal.valueOf(1))));
		DataRepo.despatches.add(currentDraftDespatch);
		this.draftDespatchList.add(currentDraftDespatch);
		DespatchDTO.seriesMap.put(DespatchDTO.DEFAULT_SERIAL_ID,
				DespatchDTO.getSerialNoFromId(currentDraftDespatch.getDespatchId()));
		clearCurrentDraftDespatch();
	}

	public void editDespatch() {
		if (!validateDestapch(selectedDraftDespatch)) {
			return;
		}
		for (DespatchDTO despatch : draftDespatchList) {
			if (selectedDraftDespatch.getUuid().equals(despatch.getUuid())) {
				DataRepo.despatches.remove(despatch);
				DataRepo.despatches.add(selectedDraftDespatch);
			}
		}
		addInfoMessage(getResourceBundleMessage("app.portal.despatch.draftDespatch.messages.crud.success.editDespatch.text"));
		openDraftDespatchPage();
	}

	private boolean changeStatus(DespatchDTO despatch, String newStatus) {
		despatch.setStatus(newStatus);
		return true;
	}

	public void sendDraftDespatch() {
		if (validateDestapch(selectedDraftDespatch)) {
			if (changeStatus(this.selectedDraftDespatch, DespatchDTO.SENT)) {
				DespatchDTO.seriesMap.put(selectedSeries, DespatchDTO.getSerialNoFromId(selectedDraftDespatch.getDespatchId()));
				addInfoMessage(getResourceBundleMessage("app.portal.despatch.draftDespatch.messages.crud.success.sendDraftDespatch.text"));
				openDraftDespatchPage();
			}
		}
	}

	public void clearCurrentDraftDespatch() {
		this.currentDraftDespatch = new DespatchDTO();
		this.currentDraftDespatch.setUuid(UUID.randomUUID().toString());
		this.currentDraftDespatch.setStatus(DespatchDTO.LOAD);
		this.currentDraftDespatch.setDespatchId(DespatchDTO.DEFAULT_SERIAL_ID
				+ (DespatchDTO.seriesMap.get(DespatchDTO.DEFAULT_SERIAL_ID).add(BigDecimal.valueOf(1))));
	}

	public void deleteDespatch() {
		if (this.selectedDraftDespatch == null) {
			return;
		}
		DataRepo.despatches.remove(selectedDraftDespatch);
		openDraftDespatchPage();
		addInfoMessage(getResourceBundleMessage("app.portal.despatch.draftDespatch.messages.crud.success.deleteDespatch.text"));
	}

	private boolean validateDestapch(DespatchDTO despatch) {
		if (despatch == null) {
			addErrorMessage(getResourceBundleMessage("app.portal.despatch.draftDespatch.messages.validation.error.despatchNotFound.text"));
			return false;
		} else if (StringUtils.isEmpty(despatch.getUuid())) {
			addErrorMessage(getResourceBundleMessage("app.portal.despatch.draftDespatch.messages.validation.error.despatchUuidIsEmpty.text"));
			return false;
		} else if (StringUtils.isEmpty(despatch.getReceiver())) {
			addErrorMessage(getResourceBundleMessage("app.portal.despatch.draftDespatch.messages.validation.error.despatctReceiverIsEmpty.text"));
			return false;
		} else if (StringUtils.isEmpty(despatch.getSender())) {
			addErrorMessage(getResourceBundleMessage("app.portal.despatch.draftDespatch.messages.validation.error.despatctSenderIsEmpty.text"));
			return false;
		} else if (StringUtils.isEmpty(despatch.getDespatchId())) {
			addErrorMessage(getResourceBundleMessage("app.portal.despatch.draftDespatch.messages.validation.error.despatctIdIsEmpty.text"));
			return false;
		}
		return true;
	}

	public String statusToString(String status) {
		switch (status) {
		case DespatchDTO.LOAD:
			return getResourceBundleMessage("app.portal.despatch.status.load.text");
		case DespatchDTO.SENT:
			return getResourceBundleMessage("app.portal.despatch.status.sent.text");
		case DespatchDTO.RECEIVED:
			return getResourceBundleMessage("app.portal.despatch.status.received.text");
		default:
			return getResourceBundleMessage("app.portal.despatch.status.undefined.text");
		}
	}

	public String statusToColor(String status) {
		switch (status) {
		case DespatchDTO.LOAD:
			return "orange";
		case DespatchDTO.SENT:
			return "green";
		case DespatchDTO.RECEIVED:
			return "yellow";
		default:
			return "white";
		}
	}

	public DespatchDTO getCurrentDraftDespatch() {
		return currentDraftDespatch;
	}

	public void setCurrentDraftDespatch(DespatchDTO currentDraftDespatch) {
		this.currentDraftDespatch = currentDraftDespatch;
	}

	public DespatchDTO getSelectedDraftDespatch() {
		return selectedDraftDespatch;
	}

	public void setSelectedDraftDespatch(DespatchDTO selectedDraftDespatch) {
		this.selectedDraftDespatch = selectedDraftDespatch;
	}

	public List<DespatchDTO> getDraftDespatchList() {
		return draftDespatchList;
	}

	public void setDraftDespatchList(List<DespatchDTO> draftDespatchList) {
		this.draftDespatchList = draftDespatchList;
	}

	public boolean isValidDespatch() {
		return validateDestapch(currentDraftDespatch);
	}

	public String getSelectedSeries() {
		return selectedSeries;
	}

	public void setSelectedSeries(String selectedSeries) {
		this.selectedSeries = selectedSeries;
	}

	public List<String> getDespatchSeriesList() {
		this.despatchSeriesList = new ArrayList<>();
		this.despatchSeriesList.addAll(DespatchDTO.despatchSeriesList);
		return despatchSeriesList;
	}

	public void getDespatchIdFromSerial() {
		this.selectedDraftDespatch.setDespatchId(DespatchDTO.getIdFromSerial(this.selectedSeries));
	}
}
