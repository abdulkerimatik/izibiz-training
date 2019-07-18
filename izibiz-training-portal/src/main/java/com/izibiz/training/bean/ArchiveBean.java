package com.izibiz.training.bean;

import java.util.ArrayList;
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
	private List<ArchiveDTO> archiveDTOs; // all invoices
	private List<ArchiveDTO> selectedList; // selected invoices

	private boolean earchiveEditAble = false;
	private boolean earchiveQueueAble = false;
	private boolean earchiveDeleteAble = false;
	private boolean earchiveSendAble = false;

	public void rowSelect() { // called after every checkbox change and update the buttons
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
				} else {
					setEarchiveDeleteAble(true);
					setEarchiveSendAble(true);
				}
			}

			for (ArchiveDTO archiveDTO : selectedList) {
				if ("SEND".equals(archiveDTO.getStatus()) || "QUEUE".equals(archiveDTO.getStatus())) {
					setEarchiveQueueAble(false);
					break;
				} else {
					setEarchiveQueueAble(true);
				}
			}
		}
	}

	public void openViewArchivePage() {
		setArchiveDTOs(new ArrayList<ArchiveDTO>());
		archiveDTOs.addAll(DataRepo.archive);
		clearArchive();
	}

	public void clearArchive() {
		archiveDto = new ArchiveDTO();
		archiveDto.setUuid(UUID.randomUUID().toString());
		//Date now = Calendar.getInstance().getTime();
		Date now = new Date();
		archiveDto.setArchiveDate(now);
		archiveDto.setcDate(now);
		archiveDto.setuDate(now);	
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
		addInfoMessage("E-arÅŸiv faturasÄ± oluÅŸturma iÅŸlemi baÅŸarÄ±lÄ±");
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
			addInfoMessage("Silme iÅŸlemi baÅŸarÄ±lÄ± ÅŸekilde tamamlanmÄ±ÅŸtÄ±r.");
			setSelectedList(null);
		}
	}

	public void editArchive() {

		if (!validate(selectedList.get(0))) {
			addErrorMessage("DÃ¼zenleme iÅŸlemi baÅŸarÄ±sÄ±z!");
			return;
		}
		for (ArchiveDTO archiveDTO : archiveDTOs) {
			if (archiveDTO.getUuid().equals(selectedList.get(0).getUuid())) {
				DataRepo.archive.remove(archiveDTO);		
				Date now = new Date();
				selectedList.get(0).setuDate(now);
				DataRepo.archive.add(selectedList.get(0));
				
			}
		}
		openViewArchivePage();
		addInfoMessage("DÃ¼zenleme iÅŸlemi baÅŸarÄ±lÄ± ÅŸekilde tamamlanmÄ±ÅŸtÄ±r.");
		selectedList = null;
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
			addErrorMessage("E-ArÅŸiv FaturasÄ± boÅŸ olamaz");
			return false;
		} else if (StringUtils.isBlank(archiveDto.getUuid())) {
			addErrorMessage("Fatura IoD boÅŸ olamaz");
			return false;
		} else if (StringUtils.isBlank(archiveDto.getArchiveId())) {
			addErrorMessage("Fatura numarasÄ± boÅŸ olamaz");
			return false;
		} else if (!org.apache.commons.lang3.math.NumberUtils.isCreatable(archiveDto.getArchiveId().substring(0, 2))
				|| (archiveDto.getArchiveId().substring(3).matches(".*\\d.*"))) {
			addErrorMessage("Fatura numarasÄ±nÄ±n ilk Ã¼Ã§ hanesi rakam, geri kalanÄ± harflerden oluÅŸmalÄ±dÄ±r");
			return false;
		} else if (StringUtils.isBlank(archiveDto.getSenderName())) {
			addErrorMessage("GÃ¶nderici boÅŸ olamaz");
			return false;
		} else if (StringUtils.isBlank(archiveDto.getReceiverName())) {
			addErrorMessage("AlÄ±cÄ± boÅŸ olamaz");
			return false;
		} else if (StringUtils.isBlank(archiveDto.getAmount().toString())) {
			addErrorMessage("Tutar boÅŸ olamaz");
			return false;
		} else if (StringUtils.isBlank(archiveDto.getArchiveDate().toString())) {
			addErrorMessage("Tarih boÅŸ olamaz");
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
						Date now = new Date();
						selArchiveDTO.setuDate(now);
						DataRepo.archive.add(selArchiveDTO);
					}
			}
		}

		selectedList = null;
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
