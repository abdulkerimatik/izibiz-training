package com.izibiz.training.bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.izibiz.training.bean.base.GenericBean;
import com.izibiz.training.entity.Archive;
import com.izibiz.training.entity.dto.ArchiveGDTO;
import com.izibiz.training.lazy.model.ArchiveEntitiyLazyModel;
import com.izibiz.training.lazy.model.InvoiceDtoLazyModel;

@ViewScoped
@ManagedBean(name = "archiveBeanG")
public class ArchiveBeanG extends GenericBean<Archive> {

	private static final long serialVersionUID = 1L;

	private List<Archive> archives;
	private Archive selectedArchive ;
	private Archive newArchive;
	public String direction;
	private Date minDate = new Date(System.currentTimeMillis() - (7L * 24 * 3600 * 1000));
	private Date today = new Date(System.currentTimeMillis());
	
	private ArchiveEntitiyLazyModel archiveLazy;
	private ArchiveGDTO selectedArchiveDto ;
	private ArchiveGDTO newArchiveDto;
	
	
	public  void loadArchivesLazy() {
		Map<String, Object> filter=new HashMap<String, Object>();
		filter.put("direction", "DRAFT");
		archiveLazy=new ArchiveEntitiyLazyModel(getArchiveService());
		archiveLazy.setFiltermap(filter);
	}
	

	public void openViewArchivePage() {

		setArchives(new ArrayList<Archive>());
		archives.addAll(getArchiveService().getAllArchiveWithDirection(direction));
		clearNewArchive();
	}

	public List<Archive> getArchivesFromDirection() {

		return getArchiveService().getAllArchiveWithDirection(direction);
		/*
		 * for (ArchiveGDTO archiveDto : archiveDTOs) { if
		 * (archiveDto.getDirection().equals(direction)) {
		 * 
		 * liste.add(archiveDto); } }
		 */

		// return archiveDTOs.stream().filter(arc
		// ->arc.getDirection().equals(direction)).collect(Collectors.toList());
	}

	public void editArchive() {

				getArchiveService().saveOrUpdate(selectedArchive);
				openViewArchivePage();
				addInfoMessage("Düzenleme işlemi başarılı şekilde tamamlanmıştrı.");
				selectedArchive = null;
		}

	

	public void saveArchive() {
		if (!validationFailed(newArchive))
			return;

		newArchive.setStatus("LOAD");
		newArchive.setDirection("DRAFT");
		getArchives().add(newArchive);
		getArchiveService().saveOrUpdate(newArchive);
		clearNewArchive();
		addInfoMessage("Efatura oluştruma işelmi bşarılı");
	}

	
	private boolean validationFailed(Archive arc) {
		if (arc == null) {
			addErrorMessage("InvoiceDTO boş olamaz ");
			return false;
		} else if (arc.getUuid() == null || arc.getUuid().length() == 0) {
			addErrorMessage("UUID boş olamaz");
			return false;
		} else if (arc.getArchiveId() == null || arc.getArchiveId().length() != 3) {
			addErrorMessage("InvoiceId boş olamaz ve 3 haneden olusmalı");
			return false;
		} else if (arc.getSender() == null || arc.getSender().length() == 0) {
			addErrorMessage("Gönderici boş olamaz");
			return false;
		} else if (arc.getReceiver() == null || arc.getReceiver().length() == 0) {
			addErrorMessage("Alıcı boş olamaz");
			return false;
		}
		// arsivid formatlı mı fonk eklenecek

		return true;
	}

	public boolean isValidateVknTckn() {

		if (newArchive.getReceiver() == null) {
			addErrorMessage("Vkn Tckn boş olamaz ");
			FacesContext.getCurrentInstance().validationFailed();
			return false;
		} else if (newArchive.getReceiver().length() != 10 || newArchive.getReceiver().length() != 11) {
			addErrorMessage("Vkn Tckn 10 ya da 11 haneli olmalı");
			FacesContext.getCurrentInstance().validationFailed();
			return false;
		} else if (isNumeric(newArchive.getReceiver())) {
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

	
	
	public void clearNewArchive() {
		newArchive = new Archive();
		newArchive.setUuid(UUID.randomUUID().toString());
	}
	
	

	public void deleteArchive() {
		if (selectedArchive != null) {

			getArchiveService().deleteArchive(selectedArchive);		
			addInfoMessage("Silme işlemi başarılı şekilde tamamlanmıştrı.");
			selectedArchive = null;
			openViewArchivePage();
		}
	}

	

	public void changeStatus(String value) {
		if (selectedArchive != null) {

			selectedArchive.setStatus(value);
			getArchiveService().saveOrUpdate(selectedArchive);
			openViewArchivePage();
		}
	}

	public void sendArchive() {
		if (selectedArchive != null) {
			selectedArchive.setStatus("SEND");
			selectedArchive.setDirection("OUT");
			getArchiveService().saveOrUpdate(selectedArchive);

			addInfoMessage("gönderme işlemi basarılı");
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
	
	
	

	public void directionSet(String direc) {	
		direction=direc;
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

	public List<Archive> getArchives() {
		return archives;
	}

	public void setArchives(List<Archive> archives) {
		this.archives = archives;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Archive getSelectedArchive() {
		return selectedArchive;
	}

	public void setSelectedArchive(Archive selectedArchive) {
		this.selectedArchive = selectedArchive;
	}

	public Archive getNewArchive() {
		return newArchive;
	}

	public void setNewArchive(Archive newArchive) {
		this.newArchive = newArchive;
	}
	public ArchiveEntitiyLazyModel getArchiveLazy() {
		return archiveLazy;
	}

	public void setArchiveLazy(ArchiveEntitiyLazyModel archiveLazy) {
		this.archiveLazy = archiveLazy;
	}


	public ArchiveGDTO getSelectedArchiveDto() {
		return selectedArchiveDto;
	}


	public void setSelectedArchiveDto(ArchiveGDTO selectedArchiveDto) {
		this.selectedArchiveDto = selectedArchiveDto;
	}


	public ArchiveGDTO getNewArchiveDto() {
		return newArchiveDto;
	}


	public void setNewArchiveDto(ArchiveGDTO newArchiveDto) {
		this.newArchiveDto = newArchiveDto;
	}
	
	
	
	
}
