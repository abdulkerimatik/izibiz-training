package com.izibiz.training.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardUpLeftHandler;

import org.apache.commons.lang3.StringUtils;

import com.izibiz.training.bean.base.GenericBean;
import com.izibiz.training.entity.dto.DataRepo;
import com.izibiz.training.entity.dto.DespatchDTO;

@ManagedBean
@ViewScoped
public class DraftDespatchBean extends GenericBean<DraftDespatchBean> {
	private DespatchDTO currentDraftDespatch;
	private DespatchDTO selectedDraftDespatch;
	private List<DespatchDTO> draftDespatchList = new ArrayList<>();
	private boolean validDespatch;
	public List<String> despatchSeriesList;
	public String selectedSeries;
	public void openDraftDespatchPage() {
		selectedDraftDespatch = null;
		selectedSeries="";
		clearCurrentDraftDespatch();
		draftDespatchList = new ArrayList<>();
		for(DespatchDTO des : DataRepo.despatches) {
			if(des.getStatus().equals(DespatchDTO.LOAD))
				draftDespatchList.add(des);
		}
		
		/*DataRepo.despatches.forEach(x->{
			if(x.getStatus().equals(DespatchDTO.LOAD)) {
				draftDespatchList.add(x);
			}
		});*/
	}

	public void saveDespatch() {
		if (!isValidDespatch()) {
			clearCurrentDraftDespatch();
			return;
		}
		DataRepo.despatches.add(currentDraftDespatch);
		this.draftDespatchList.add(currentDraftDespatch);
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
		addInfoMessage("D�zenleme ba�ar�l�");
		openDraftDespatchPage();
	}

	private boolean changeStatus(DespatchDTO despatch, String newStatus) {
		despatch.setStatus(newStatus);
		return true;
	}

	public void sendDraftDespatch() {
		if (validateDestapch(selectedDraftDespatch)) {
			if (changeStatus(this.selectedDraftDespatch, DespatchDTO.SENT)) {
				addInfoMessage("�rsaliye ba�ar�yla g�nderildi");
				openDraftDespatchPage();
			}
		}

	}

	public void clearCurrentDraftDespatch() {
		this.currentDraftDespatch = new DespatchDTO();
		this.currentDraftDespatch.setUuid(UUID.randomUUID().toString());
		this.currentDraftDespatch.setStatus(DespatchDTO.LOAD);
	}

	public void deleteDespatch() {
		if (this.selectedDraftDespatch == null) {
			return;
		}
		DataRepo.despatches.remove(selectedDraftDespatch);
		openDraftDespatchPage();
		addInfoMessage("�rsaliye silme i�lemi ba�ar�l�!");
	}

	private boolean validateDestapch(DespatchDTO despatch) {
		if (despatch == null) {
			addErrorMessage("�rsaliye bulunamad�!");
			return false;
		} else if (StringUtils.isEmpty(despatch.getUuid())) {
			addErrorMessage("UUID bo� olamaz!");
			return false;
		} else if (StringUtils.isEmpty(despatch.getReceiver())) {
			addErrorMessage("Al�c� bilgisi bo� ge�ilemez!");
			return false;
		} else if (StringUtils.isEmpty(despatch.getSender())) {
			addErrorMessage("G�nderici bilgisi bo� ge�ilemez!");
			return false;
		} else if (StringUtils.isEmpty(despatch.getDespatchId())) {
			addErrorMessage("�rsaliye numaras� bo� ge�ilemez!");
			return false;
		}
		return true;
	}
	
	public String statusToString(String status) {
		switch (status) {
		case DespatchDTO.LOAD:
			return "Y�klendi";
		case DespatchDTO.SENT:
			return "G�nderildi";
		case DespatchDTO.RECEIVED:
			return "Al�nd�";
		default:
			return "Tan�ms�z";
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
		this.despatchSeriesList.addAll(DataRepo.despatchSeriesList);
		return despatchSeriesList;
	}
	
	
}
