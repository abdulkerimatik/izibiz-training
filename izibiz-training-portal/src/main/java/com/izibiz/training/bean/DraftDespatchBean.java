package com.izibiz.training.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.izibiz.training.bean.base.GenericBean;
import com.izibiz.training.entity.Despatch;
import com.izibiz.training.entity.dto.DespatchDTO;
import com.izibiz.training.service.DespatchServiceImpl;
import com.izibiz.training.service.base.DespatchService;

@ManagedBean
@ViewScoped
public class DraftDespatchBean extends GenericBean<DraftDespatchBean> {
	/**
	 *
	 */

	private static final long serialVersionUID = 676708930015358326L;
	private Despatch currentDraftDespatch;
	private Despatch selectedDraftDespatch;
	private List<Despatch> draftDespatchList = new ArrayList<>();
	private boolean validDespatch;
	public List<String> despatchSeriesList;
	public String selectedSeries;

	public void openDraftDespatchPage() {
		selectedDraftDespatch = null;
		selectedSeries = "";
		clearCurrentDraftDespatch();
		draftDespatchList = new ArrayList<>();
		for (Object des : getDespatchService().getAllDespatchesWithType(DespatchDTO.LOAD)) {
			// if (des.getStatus().equals(DespatchDTO.LOAD))
			draftDespatchList.add((Despatch) des);
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
		getDespatchService().saveOrUpdate(currentDraftDespatch);
		this.draftDespatchList.add(currentDraftDespatch);
		DespatchDTO.seriesMap.put(DespatchDTO.DEFAULT_SERIAL_ID,
				DespatchDTO.getSerialNoFromId(currentDraftDespatch.getDespatchId()));
		clearCurrentDraftDespatch();
	}

	public void editDespatch() {
		if (!validateDestapch(selectedDraftDespatch)) {
			return;
		}
		getDespatchService().saveOrUpdate(selectedDraftDespatch);
		addInfoMessage(
				getResourceBundleMessage("app.portal.despatch.draftDespatch.messages.crud.success.editDespatch.text"));

		addInfoMessage(
				getResourceBundleMessage("app.portal.despatch.draftDespatch.messages.crud.success.editDespatch.text"));
		openDraftDespatchPage();
	}

	private boolean changeStatus(Despatch despatch, String newStatus) {
		despatch.setStatus(newStatus);
		return true;
	}

	public void sendDraftDespatch() {
		if (validateDestapch(selectedDraftDespatch)) {
			if (changeStatus(this.selectedDraftDespatch, DespatchDTO.SENT)) {
				getDespatchService().saveOrUpdate(selectedDraftDespatch);
				DespatchDTO.seriesMap.put(selectedSeries,
						DespatchDTO.getSerialNoFromId(selectedDraftDespatch.getDespatchId()));
				addInfoMessage(getResourceBundleMessage(
						"app.portal.despatch.draftDespatch.messages.crud.success.sendDraftDespatch.text"));
				openDraftDespatchPage();
			}
		}
	}

	public void clearCurrentDraftDespatch() {
		this.currentDraftDespatch = new Despatch();
		this.currentDraftDespatch.setUuid(UUID.randomUUID().toString());
		this.currentDraftDespatch.setStatus(DespatchDTO.LOAD);
		this.currentDraftDespatch.setDespatchId(DespatchDTO.DEFAULT_SERIAL_ID
				+ (DespatchDTO.seriesMap.get(DespatchDTO.DEFAULT_SERIAL_ID).add(BigDecimal.valueOf(1))));
	}

	public void deleteDespatch() {
		if (this.selectedDraftDespatch == null) {
			return;
		}
		/*
		 * if(getDespatchService().deleteDespatch(this.selectedDraftDespatch)) {
		 * addInfoMessage(getResourceBundleMessage(
		 * "app.portal.despatch.draftDespatch.messages.crud.success.deleteDespatch.text"
		 * )); }
		 */
		openDraftDespatchPage();
		addInfoMessage(getResourceBundleMessage(
				"app.portal.despatch.draftDespatch.messages.crud.success.deleteDespatch.text"));
	}

	private boolean validateDestapch(Despatch despatch) {
		if (despatch == null) {
			addErrorMessage(getResourceBundleMessage(
					"app.portal.despatch.draftDespatch.messages.validation.error.despatchNotFound.text"));
			return false;
		} else if (StringUtils.isEmpty(despatch.getUuid())) {
			addErrorMessage(getResourceBundleMessage(
					"app.portal.despatch.draftDespatch.messages.validation.error.despatchUuidIsEmpty.text"));
			return false;
		} else if (StringUtils.isEmpty(despatch.getReceiver())) {
			addErrorMessage(getResourceBundleMessage(
					"app.portal.despatch.draftDespatch.messages.validation.error.despatctReceiverIsEmpty.text"));
			return false;
		} else if (StringUtils.isEmpty(despatch.getSender())) {
			addErrorMessage(getResourceBundleMessage(
					"app.portal.despatch.draftDespatch.messages.validation.error.despatctSenderIsEmpty.text"));
			return false;
		} else if (StringUtils.isEmpty(despatch.getDespatchId())) {
			addErrorMessage(getResourceBundleMessage(
					"app.portal.despatch.draftDespatch.messages.validation.error.despatctIdIsEmpty.text"));
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

	public Despatch getCurrentDraftDespatch() {
		return currentDraftDespatch;
	}

	public void setCurrentDraftDespatch(Despatch currentDraftDespatch) {
		this.currentDraftDespatch = currentDraftDespatch;
	}

	public Despatch getSelectedDraftDespatch() {
		return selectedDraftDespatch;
	}

	public void setSelectedDraftDespatch(Despatch selectedDraftDespatch) {
		this.selectedDraftDespatch = selectedDraftDespatch;
	}

	public List<Despatch> getDraftDespatchList() {
		return draftDespatchList;
	}

	public void setDraftDespatchList(List<Despatch> draftDespatchList) {
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
