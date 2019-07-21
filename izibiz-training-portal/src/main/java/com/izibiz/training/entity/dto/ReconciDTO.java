package com.izibiz.training.entity.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ReconciDTO {
	
	private String fuId;
	//private String archiveId;
	private String customerName;
	private String status;
	private String VKN_TCKNO;
	//private Date arrangementDate;
	private Date loadingDate;//= new Date();
	private BigDecimal amount;	
	
	
	
	public String getFuId() {
		return fuId;
	}
	public void setFuId(String fuId) {
		this.fuId = fuId;
	}

	/*
	 * public String getArchiveId() { return archiveId; } public void
	 * setArchiveId(String archiveId) { this.archiveId = archiveId;}
	 */
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getVKN_TCKNO() {
		return VKN_TCKNO;
	}
	public void setVKN_TCKNO(String vKN_TCKNO) {
		VKN_TCKNO = vKN_TCKNO;
	}

	/*
	 * public Date getArrangementDate() { return arrangementDate; } public void
	 * setArrangementDate(Date arrangementDate) { this.arrangementDate =
	 * arrangementDate; }
	 */
	public Date getLoadingDate() {
		return loadingDate;
	}
	public void setLoadingDate(Date loadingDate) {
		this.loadingDate = loadingDate;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


}
