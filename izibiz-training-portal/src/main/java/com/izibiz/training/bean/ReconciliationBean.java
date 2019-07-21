package com.izibiz.training.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.izibiz.training.bean.base.GenericBean;
import com.izibiz.training.entity.dto.DataRepo;
import com.izibiz.training.entity.dto.ReconciDTO;


@ViewScoped
@ManagedBean
public class ReconciliationBean extends GenericBean<ReconciDTO> {

	private ReconciDTO reconciDto;
	private List<ReconciDTO> reconciDTOs;
	private List<ReconciDTO> listDTOs;
	private ReconciDTO selectedReconciDto;

	public void openViewReconciPage() {
		setReconciDTOs(new ArrayList<ReconciDTO>());
		reconciDTOs.addAll(DataRepo.reconci);
		clearReconci();
	}
	public void openViewReconciPageInsert() {
		setListDTOs(new ArrayList<ReconciDTO>());
		listDTOs.addAll(DataRepo.reconcisent);
		
		//clearArchive();
	}

	public void clearReconci() {
		reconciDto = new ReconciDTO();
		reconciDto.setFuId(UUID.randomUUID().toString());
	}
	
	public void sentReconci() {
		//archiveDto.setStatus("LOAD");
		setListDTOs(new ArrayList<ReconciDTO>());
		for (ReconciDTO reconciDTO : reconciDTOs) {
			if (reconciDTO.getFuId().equals(selectedReconciDto.getFuId())) {
				DataRepo.reconci.remove(reconciDTO);
				DataRepo.reconcisent.add(selectedReconciDto);
				//archiveDTOs.remove(o)	
			}
		}
		selectedReconciDto.setStatus("LOAD");
		openViewReconciPage();
		openViewReconciPageInsert();
		addInfoMessage("Fatura Baþarýlýyla Gönderildi");
	}
	public void saveReconci() {
		reconciDto.setStatus("SUCCED");
		getReconciDTOs().add(reconciDto);
		DataRepo.reconci.add(reconciDto);
		clearReconci();
		addInfoMessage("E-arsiv oluþturma iþlemi baþarýlý");
	}

	private boolean validationFailed(ReconciDTO selectedReconciDto2) {
		if (selectedReconciDto2 == null) {
			addErrorMessage("InvoiceDTO boþ olamaz");
			return false;
		} else {

			return false;
		}

	}

	public void editReconci() {

		for (ReconciDTO reconcDTO : reconciDTOs) {
			if (reconcDTO.getFuId().equals(selectedReconciDto.getFuId())) {
				DataRepo.reconci.remove(reconcDTO);
				DataRepo.reconci.add(selectedReconciDto);		
			}
		}
		openViewReconciPage();
		addInfoMessage("Düzenleme iþleme baþarýlý");
		selectedReconciDto = null;
	}
	public void changeStatus(String value) {
		if(selectedReconciDto!=null) {
			selectedReconciDto.setStatus(value);
		}
		}
	public String statusColor(String status) {
		if ("LOAD".equals(status)) {
			return "yellow";
		}else if("NEW".equals(status)){
			return "red";
		}else if("SUCCED".equals(status)){
			return "green";
		}
		return "";
	}
	public String statusDesc(String status) {
		if("LOAD".equals(status)) {
			return "Yüklendi";
		}else if("NEW".equals(status)) {
			return "Kuyruga eklendi";
		}else if("SUCCED".equals(status)) {
			return "Baþarýlý";
		}
		return "";
	}
	
	public void deleteReconci() {
		if (selectedReconciDto != null) {
			for (ReconciDTO reconciDTO : reconciDTOs) {
				if (reconciDTO.getFuId().equals(selectedReconciDto.getFuId())) {
					DataRepo.reconci.remove(reconciDTO);
				}
			}
			openViewReconciPage();
			addInfoMessage("Silme iþlemi baþarýlý þekilde tamamlanmýþtýr.");
			selectedReconciDto = null;
		}
	}
	public ReconciDTO getReconciDto() {
		return reconciDto;
	}
	public void setReconciDto(ReconciDTO reconciDto) {
		this.reconciDto = reconciDto;
	}
	public List<ReconciDTO> getReconciDTOs() {
		return reconciDTOs;
	}
	public void setReconciDTOs(List<ReconciDTO> reconciDTOs) {
		this.reconciDTOs = reconciDTOs;
	}
	public List<ReconciDTO> getListDTOs() {
		return listDTOs;
	}
	public void setListDTOs(List<ReconciDTO> listDTOs) {
		this.listDTOs = listDTOs;
	}
	public ReconciDTO getSelectedReconciDto() {
		return selectedReconciDto;
	}
	public void setSelectedReconciDto(ReconciDTO selectedReconciDto) {
		this.selectedReconciDto = selectedReconciDto;
	}



}
