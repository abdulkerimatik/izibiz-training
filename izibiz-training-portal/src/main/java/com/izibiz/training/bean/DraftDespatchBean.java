package com.izibiz.training.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.izibiz.training.bean.base.GenericBean;
import com.izibiz.training.entity.dto.DataRepo;
import com.izibiz.training.entity.dto.DespatchDTO;

@ManagedBean
@ViewScoped
public class DraftDespatchBean extends GenericBean<DraftDespatchBean> {
	private DespatchDTO currentDraftDespatch;
	private DespatchDTO selectedDraftDespatch;
	private List<DespatchDTO> draftDespatchList;
	private boolean validDespatch;

	public void openDraftDespatchPage() {
		selectedDraftDespatch = null;
		clearCurrentDraftDespatch();
		draftDespatchList = new ArrayList<>();
		draftDespatchList.addAll(DataRepo.despatches);
	}

	public void saveDespatch() {
		if (!isValidDespatch()) {
			return;
		}
		DataRepo.despatches.add(currentDraftDespatch);
		this.draftDespatchList.add(currentDraftDespatch);
		clearCurrentDraftDespatch();
	}

	public void editDespatch() {
		if (validateDestapch(selectedDraftDespatch)) {
			return;
		}
		for(DespatchDTO despatch : draftDespatchList) {
			if(selectedDraftDespatch.getUuid().equals(despatch.getUuid())) {
				DataRepo.despatches.remove(despatch);
				DataRepo.despatches.add(selectedDraftDespatch);
			}
		}
		selectedDraftDespatch=null;
		openDraftDespatchPage();
		addInfoMessage("Düzenleme başarılı");
	}

	public void clearCurrentDraftDespatch() {
		this.currentDraftDespatch = new DespatchDTO();
		this.currentDraftDespatch.setUuid(UUID.randomUUID().toString());
		this.currentDraftDespatch.setStatus("LOAD");
	}
	

	private boolean validateDestapch(DespatchDTO despatch) {
		if (despatch == null) {
			addErrorMessage("İrsaliye bulunamadı!");
			return false;
		} else if (StringUtils.isEmpty(despatch.getUuid())) {
			addErrorMessage("UUID boş olamaz!");
			return false;
		} else if (StringUtils.isEmpty(despatch.getReceiver())) {
			addErrorMessage("Alıcı bilgisi boş geçilemez!");
			return false;
		} else if (StringUtils.isEmpty(despatch.getSender())) {
			addErrorMessage("Gönderici bilgisi boş geçilemez!");
			return false;
		} else if (StringUtils.isEmpty(despatch.getDespatchId())) {
			addErrorMessage("İrsaliye numarası boş geçilemez!");
			return false;
		}
		return true;
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

}
