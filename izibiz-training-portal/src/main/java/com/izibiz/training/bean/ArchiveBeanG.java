package com.izibiz.training.bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import com.izibiz.training.bean.base.GenericBean;
import com.izibiz.training.entity.dto.ArchiveDTO;
import com.izibiz.training.entity.dto.ArchiveGDTO;
import com.izibiz.training.entity.dto.DataRepo;


@ViewScoped
@ManagedBean(name = "archiveBeanG")
public class ArchiveBeanG extends GenericBean<ArchiveGDTO> {

	private ArchiveGDTO archiveDto;
	private List<ArchiveGDTO> archiveDTOs;
	private ArchiveGDTO selectedArchiveDto;
	private Date minDate = new Date(System.currentTimeMillis() - (7L * 24 * 3600 * 1000));
	private Date today = new Date(System.currentTimeMillis());

	public void editArchive() {

		for (ArchiveGDTO ArchiveDTO : archiveDTOs) {
			if (ArchiveDTO.getUuid().equals(selectedArchiveDto.getUuid())) {
				DataRepo.archiveG.remove(ArchiveDTO);
				DataRepo.archiveG.add(selectedArchiveDto);
			}
		}
		openViewArchivePage();
		addInfoMessage("Düzenleme işlemi başarılı şekilde tamamlanmıştrı.");
		selectedArchiveDto = null;
	}

	public void saveArchive() {
		if (!validationFailed(archiveDto))
			return;

		archiveDto.setStatus("LOAD");
		archiveDto.setDirection("DRAFT");
		getArchiveDTOs().add(archiveDto);
		DataRepo.archiveG.add(archiveDto);
		clearArchive();
		addInfoMessage("Efatura oluştruma işelmi bşarılı");
	}

	private boolean validationFailed(ArchiveGDTO arcDto) {
		if (arcDto == null) {
			addErrorMessage("InvoiceDTO boş olamaz ");
			return false;
		} else if (arcDto.getUuid() == null || arcDto.getUuid().length() == 0) {
			addErrorMessage("UUID boş olamaz");
			return false;
		} else if (arcDto.getArchiveId() == null || arcDto.getArchiveId().length() != 3) {
			addErrorMessage("InvoiceId boş olamaz ve 3 haneden olusmalı");
			return false;
		} else if (arcDto.getSenderName() == null || arcDto.getSenderName().length() == 0) {
			addErrorMessage("Gönderici boş olamaz");
			return false;
		} else if (arcDto.getReceiverTcVkn() == null || arcDto.getReceiverTcVkn().length() == 0) {
			addErrorMessage("Alıcı boş olamaz");
			return false;
		}
		// arsivid formatlı mı fonk eklenecek
		

		return true;
	}

	public boolean isValidateVknTckn() {

		if (archiveDto.getReceiverTcVkn() == null) {
			addErrorMessage("Vkn Tckn boş olamaz ");
			FacesContext.getCurrentInstance().validationFailed();
			return false;
		} else if (archiveDto.getReceiverTcVkn().length() != 10 || archiveDto.getReceiverTcVkn().length() != 11) {
			addErrorMessage("Vkn Tckn 10 ya da 11 haneli olmalı");
			FacesContext.getCurrentInstance().validationFailed();
			return false;
		} else if (isNumeric(archiveDto.getReceiverTcVkn())) {
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

	
	public void openViewArchivePage() {
		setArchiveDTOs(new ArrayList<ArchiveGDTO>());
		archiveDTOs.addAll(DataRepo.archiveG);
		clearArchive();
	}

	public List<ArchiveGDTO> getArchivesFromDirection(String direction) {

		List<ArchiveGDTO> liste = new ArrayList<ArchiveGDTO>();

		for (ArchiveGDTO archiveDto : archiveDTOs) {
			if (archiveDto.getDirection().equals(direction)) {

				liste.add(archiveDto);
			}
		}
		return liste;
		// return archiveDTOs.stream().filter(arc
		// ->arc.getDirection().equals(direction)).collect(Collectors.toList());
	}

	public void clearArchive() {
		archiveDto = new ArchiveGDTO();
		archiveDto.setUuid(UUID.randomUUID().toString());
	}

	public void deleteArchive() {
		if (selectedArchiveDto != null) {
			for (ArchiveGDTO archiveDto : archiveDTOs) {
				if (archiveDto.getUuid().equals(selectedArchiveDto.getUuid())) {
					DataRepo.archiveG.remove(archiveDto);
				}
			}
			openViewArchivePage();
			addInfoMessage("Silme işlemi başarılı şekilde tamamlanmıştrı.");
			selectedArchiveDto = null;
		}
	}

	public void changeStatus(String value) {
		if (selectedArchiveDto != null) {
			for (ArchiveGDTO archiveDto : archiveDTOs) {
				if (archiveDto.getUuid().equals(selectedArchiveDto.getUuid())) {
					selectedArchiveDto.setStatus(value);
					DataRepo.archiveG.remove(archiveDto);
					DataRepo.archiveG.add(selectedArchiveDto);
				}
			}
		}
		openViewArchivePage();
	}

	public void sendArchive() {
		if (selectedArchiveDto != null) {
			/*
			 * archiveDTOs.stream() .filter(arc ->
			 * arc.getUuid().equals(selectedArchiveDto.getUuid())).findAny().orElse(null);
			 */
			for (ArchiveGDTO archiveDto : archiveDTOs) {
				if (archiveDto.getUuid().equals(selectedArchiveDto.getUuid())) {
					DataRepo.archiveG.remove(archiveDto);
					archiveDto.setStatus("SEND");
					archiveDto.setDirection("OUT");
					DataRepo.archiveG.add(archiveDto);
					addInfoMessage("gönderme işlemi basarılı");
				}
			}
			/*
			 * if (senderArchive != null) { senderArchive.setStatus("SEND");
			 * senderArchive.setDirection("OUT"); }
			 */
		}
		openViewArchivePage();
	}

	public String statusColor(String status) {
		if ("LOAD".equals(status)) {
			return "yellow";
		} else if ("NEW".equals(status)) {
			return "blue";
		} else if ("SUCCEED".equals(status)) {
			return "green";
		} else if ("SEND".equals(status)) {
			return "green";
		}
		return "";
	}

	public String statusDesc(String status) {
		if ("LOAD".equals(status)) {
			return "Yüklendi";
		} else if ("NEW".equals(status)) {
			return "Kuyruga eklendi";
		} else if ("SUCCEED".equals(status)) {
			return "Başarılı";
		} else if ("SEND".equals(status)) {
			return "Başarılı";
		}
		return "";
	}

	public ArchiveGDTO getArchiveDto() {
		return archiveDto;
	}

	public void setArchiveDto(ArchiveGDTO archiveDto) {
		this.archiveDto = archiveDto;
	}

	public List<ArchiveGDTO> getArchiveDTOs() {
		return archiveDTOs;
	}

	public void setArchiveDTOs(List<ArchiveGDTO> archiceDTOs) {
		this.archiveDTOs = archiceDTOs;
	}

	public ArchiveGDTO getSelectedArchiveDto() {
		System.out.print("kjlkjlkjlj     "+selectedArchiveDto);
		return selectedArchiveDto;
	}

	public void setSelectedArchiveDto(ArchiveGDTO selectedArchiveDto) {
		System.out.print("kjlkjlkjlj     "+selectedArchiveDto);
		this.selectedArchiveDto = selectedArchiveDto;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

}
