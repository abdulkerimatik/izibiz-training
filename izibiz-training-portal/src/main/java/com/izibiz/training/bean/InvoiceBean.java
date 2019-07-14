package com.izibiz.training.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.izibiz.training.bean.base.GenericBean;
import com.izibiz.training.entity.dto.DataRepo;
import com.izibiz.training.entity.dto.InvoiceDTO;

@ViewScoped
@ManagedBean
public class InvoiceBean extends GenericBean<InvoiceDTO> {

	private InvoiceDTO invoiceDto;
	private List<InvoiceDTO> invoiceDTOs;
	private InvoiceDTO selectedInvoiceDto;
	
	
	public void openViewInvoicePage() {
		setInvoiceDTOs(new ArrayList<InvoiceDTO>());
		invoiceDTOs.addAll(DataRepo.invoice);
		clearInvoice();
	}
	
	
	public void saveInvoice() {
		if (!validationFailed(invoiceDto))
			return;
		
		invoiceDto.setStatus("LOAD");
		getInvoiceDTOs().add(invoiceDto);
		DataRepo.invoice.add(invoiceDto);
		clearInvoice();
		addInfoMessage("Efatura oluştruma işelmi bşarılı");
	}
	
	
	private boolean validationFailed(InvoiceDTO invDto) {
		if (invDto == null) {
			addErrorMessage("InvoiceDTO boş olamaz");
			return false;
		} else if (invDto.getUuid() == null || invDto.getUuid().length() == 0) {
			addErrorMessage("UUID boş olamaz");
			return false;
		} else if (invDto.getInvoiceId() == null || invDto.getInvoiceId().length() == 0) {
			addErrorMessage("InvoiceId boş olamaz");
			return false;
		} else if (invDto.getSenderName() == null || invDto.getSenderName().length() == 0) {
			addErrorMessage("Gönderici boş olamaz");
			return false;
		} else if (invDto.getReceiverName() == null || invDto.getReceiverName().length() == 0) {
			addErrorMessage("Alıcı boş olamaz");
			return false;
		}
		return true;
	}
	
	public void deleteInvoice() {
		if(selectedInvoiceDto!=null) {
			for (InvoiceDTO invoiceDTO : invoiceDTOs) {
				if(invoiceDTO.getUuid().equals(selectedInvoiceDto.getUuid())) {
					DataRepo.invoice.remove(invoiceDTO);
				}
			}
			openViewInvoicePage();
			addInfoMessage("Silme işlemi başarılı şekilde tamamlanmıştrı.");
			selectedInvoiceDto=null;
		}	
	}
	public void clearInvoice() {
		invoiceDto=new InvoiceDTO();
		invoiceDto.setUuid(UUID.randomUUID().toString());
	}
	
	public String statusDesc(String status) {
		if("LOAD".equals(status)) {
			return "Yüklendi";
		}else if("NEW".equals(status)) {
			return "Kuyruga eklendi";
		}else if("SUCCEED".equals(status)) {
			return "Başarılı";
		}
		return "";
			
		
	}
	
	public void changeStatus(String value) {
		if(selectedInvoiceDto!=null) {
			selectedInvoiceDto.setStatus(value);
		}
	}
	
	public String statusColor(String status) {
		if("LOAD".equals(status)) {
			return "yellow";
		}else if("NEW".equals(status)) {
			return "blue";
		}else if("SUCCEED".equals(status)) {
			return "green";
		}
		return "";
			
		
	}
	
	public void editInvoice() {
		if (!validationFailed(selectedInvoiceDto))
			return;
		
		for (InvoiceDTO invoiceDTO : invoiceDTOs) {
			if(invoiceDTO.getUuid().equals(selectedInvoiceDto.getUuid())) {
				DataRepo.invoice.remove(invoiceDTO);
				DataRepo.invoice.add(selectedInvoiceDto);
			}
		}
		openViewInvoicePage();
		addInfoMessage("Düzenleme işlemi başarılı şekilde tamamlanmıştrı.");
		selectedInvoiceDto=null;
	}

	public InvoiceDTO getInvoiceDto() {
		return invoiceDto;
	}

	public void setInvoiceDto(InvoiceDTO invoiceDto) {
		this.invoiceDto = invoiceDto;
	}


	public List<InvoiceDTO> getInvoiceDTOs() {
		return invoiceDTOs;
	}


	public void setInvoiceDTOs(List<InvoiceDTO> invoiceDTOs) {
		this.invoiceDTOs = invoiceDTOs;
	}


	public InvoiceDTO getSelectedInvoiceDto() {
		return selectedInvoiceDto;
	}


	public void setSelectedInvoiceDto(InvoiceDTO selectedInvoiceDto) {
		this.selectedInvoiceDto = selectedInvoiceDto;
	}
	
	
	
}
