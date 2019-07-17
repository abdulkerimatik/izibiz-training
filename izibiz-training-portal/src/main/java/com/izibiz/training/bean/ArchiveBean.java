package com.izibiz.training.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.util.CollectionUtils;

import com.izibiz.training.bean.base.GenericBean;
import com.izibiz.training.entity.dto.ArchiveDTO;
import com.izibiz.training.entity.dto.DataRepo;
import com.ocpsoft.pretty.faces.util.StringUtils;

@ViewScoped
@ManagedBean
public class ArchiveBean extends GenericBean<ArchiveDTO> {

	private ArchiveDTO archiveDto;
	private List<ArchiveDTO> archiveDTOs;
	
	private List<ArchiveDTO> selectedList;
	private ArchiveDTO selectedArchiveDto;

	private boolean earchiveEditAble = false;
	private boolean earchiveQueueAble = false;
	private boolean earchiveDeleteAble = false;
	private boolean earchiveSendAble = false;

	public void rowSelect() {
		setEarchiveEditAble(false);
		setEarchiveQueueAble(false);
		setEarchiveDeleteAble(false);
		setEarchiveSendAble(false);

		if (!CollectionUtils.isEmpty(selectedList)) {
			setEarchiveQueueAble(true);
			if (selectedList.size() == 1 && !selectedList.get(0).getStatus().equals("SEND"))
				setEarchiveEditAble(true);

			for (ArchiveDTO archiveDTO : selectedList) {
				if ("SEND".equals(archiveDTO.getStatus())) {
					setEarchiveSendAble(false);
					setEarchiveDeleteAble(false);
					break;
				}				
				else {
					setEarchiveDeleteAble(true);
					setEarchiveSendAble(true);
				}
			}
			
			for (ArchiveDTO archiveDTO : selectedList) {
				if ("SEND".equals(archiveDTO.getStatus()) || "QUEUE".equals(archiveDTO.getStatus())) {
					setEarchiveQueueAble(false);
					break;
				}
				else {
					setEarchiveQueueAble(true);
				}
			}
		}
	}

	public void actionSave() {
		System.out.println("Table saved");
	}

	public void openViewArchivePage() {
		setArchiveDTOs(new ArrayList<ArchiveDTO>());
		archiveDTOs.addAll(DataRepo.archive);
		clearArchive();
	}

	public void clearArchive() {
		archiveDto = new ArchiveDTO();
		archiveDto.setUuid(UUID.randomUUID().toString());
		Date today = Calendar.getInstance().getTime();
		archiveDto.setArchiveDate(today);
	}

	public void saveArchive() {
		if (!validate(archiveDto)) {
			addErrorMessage("E-arşiv faturası oluşturma işlemi başarısız");
			return;
		}
		archiveDto.setStatus("LOAD");
		getArchiveDTOs().add(archiveDto);
		DataRepo.archive.add(archiveDto);
		clearArchive();
		addInfoMessage("E-arşiv faturası oluşturma işlemi başarılı");
	}

	public void deleteArchive() {
		if (getSelectedList() != null) {
			for (ArchiveDTO archiveDTO : archiveDTOs) {
				for (ArchiveDTO selArchiveDTO : getSelectedList())
					if (archiveDTO.getUuid().equals(selArchiveDTO.getUuid())) {
						DataRepo.archive.remove(archiveDTO);
					}
			}
			openViewArchivePage();
			addInfoMessage("Silme işlemi başarılı şekilde tamamlanmıştır.");
			setSelectedList(null);
		}
	}

	public void editArchive() {

		ArchiveDTO selectedArchiveDto = getSelectedList().get(0);

		if (!validate(selectedArchiveDto)) {
			addErrorMessage("Düzenleme işlemi başarısız!");
			return;
		}
		for (ArchiveDTO archiveDTO : archiveDTOs) {
			if (archiveDTO.getUuid().equals(selectedArchiveDto.getUuid())) {
				DataRepo.archive.remove(archiveDTO);
				DataRepo.archive.add(selectedArchiveDto);
			}
		}
		openViewArchivePage();
		addInfoMessage("Düzenleme işlemi başarılı şekilde tamamlanmıştır.");
		selectedArchiveDto = null;
	}

	public String statusDesc(String status) {
		if ("LOAD".equals(status)) {
			return "YÜKLENDİ";
		} else if ("QUEUE".equals(status)) {
			return "KUYRUĞA EKLENDİ";
		} else if ("SEND".equals(status)) {
			return "GÖNDERİLDİ";
		}
		return "";
	}

	public String statusColor(String status) {
		if ("LOAD".equals(status)) {
			return "yellow";
		} else if ("QUEUE".equals(status)) {
			return "blue";
		} else if ("SEND".equals(status)) {
			return "green";
		}
		return "";
	}

	private boolean validate(ArchiveDTO archiveDto) {
		if (archiveDto == null) {
			addErrorMessage("E-Arşiv Faturası boş olamaz");
			return false;
		} else if (StringUtils.isBlank(archiveDto.getUuid())) {
			addErrorMessage("Fatura IoD boş olamaz");
			return false;
		} else if (StringUtils.isBlank(archiveDto.getArchiveId())) {
			addErrorMessage("Fatura numarası boş olamaz");
			return false;
		} else if (!org.apache.commons.lang3.math.NumberUtils.isCreatable(archiveDto.getArchiveId().substring(0, 2))
				|| (archiveDto.getArchiveId().substring(3).matches(".*\\d.*"))) {
			addErrorMessage("Fatura numarasının ilk üç hanesi rakam, geri kalanı harflerden oluşmalıdır");
			return false;
		} else if (StringUtils.isBlank(archiveDto.getSenderName())) {
			addErrorMessage("Gönderici boş olamaz");
			return false;
		} else if (StringUtils.isBlank(archiveDto.getReceiverName())) {
			addErrorMessage("Alıcı boş olamaz");
			return false;
		} else if (StringUtils.isBlank(archiveDto.getAmount().toString())) {
			addErrorMessage("Tutar boş olamaz");
			return false;
		} else if (StringUtils.isBlank(archiveDto.getArchiveDate().toString())) {
			addErrorMessage("Tarih boş olamaz");
			return false;
		}
		return true;
	}

	public void changeStatus(String value) {

		if (getSelectedList() != null) {
			for (ArchiveDTO archiveDTO : archiveDTOs) {
				for (ArchiveDTO selArchiveDTO : getSelectedList())
					if (archiveDTO.getUuid().equals(selArchiveDTO.getUuid())) {
						selArchiveDTO.setStatus(value);
						DataRepo.archive.clear();
						DataRepo.archive.add(selArchiveDTO);
					}
			}
		}
		
		selectedList=null;
	}

	public ArchiveDTO getArchiveDto() {
		return archiveDto;
	}

	public void setArchiveDto(ArchiveDTO archiveDto) {
		this.archiveDto = archiveDto;
	}

	public List<ArchiveDTO> getArchiveDTOs() {
		return archiveDTOs;
	}

	public void setArchiveDTOs(List<ArchiveDTO> archiveDTOs) {
		this.archiveDTOs = archiveDTOs;
	}

	public ArchiveDTO getSelectedArchiveDto() {
		return selectedArchiveDto;
	}

	public void setSelectedArchiveDto(ArchiveDTO selectedArchiveDto) {
		this.selectedArchiveDto = selectedArchiveDto;
	}

	public List<ArchiveDTO> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<ArchiveDTO> selectedList) {
		this.selectedList = selectedList;
	}

	public boolean isEarchiveEditAble() {
		return earchiveEditAble;
	}

	public void setEarchiveEditAble(boolean earchiveEditAble) {
		this.earchiveEditAble = earchiveEditAble;
	}

	public boolean isEarchiveDeleteAble() {
		return earchiveDeleteAble;
	}

	public void setEarchiveDeleteAble(boolean earchiveDeleteAble) {
		this.earchiveDeleteAble = earchiveDeleteAble;
	}

	public boolean isEarchiveSendAble() {
		return earchiveSendAble;
	}

	public void setEarchiveSendAble(boolean earchiveSendAble) {
		this.earchiveSendAble = earchiveSendAble;
	}

	public boolean isEarchiveQueueAble() {
		return earchiveQueueAble;
	}

	public void setEarchiveQueueAble(boolean earchiveQueueAble) {
		this.earchiveQueueAble = earchiveQueueAble;
	}

}
