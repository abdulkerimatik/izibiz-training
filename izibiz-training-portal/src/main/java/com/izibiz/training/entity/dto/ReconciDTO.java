package com.izibiz.training.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReconciDTO implements Serializable{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -915029403718064589L;
	private Long id;
	private String uuid;
	private String reconciliationId;
	private String sender;
	private String status;
	private String receiverName;
	private String receiverIdentifier;
	private Date loadingDate;
	private Date arrangementDate;
	private BigDecimal amount;
	private int ACCOUNT_ID;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getReconciliationId() {
		return reconciliationId;
	}
	public void setReconciliationId(String reconciliationId) {
		this.reconciliationId = reconciliationId;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public Date getLoadingDate() {
		return loadingDate;
	}
	public void setLoadingDate(Date loadingDate) {
		this.loadingDate = loadingDate;
	}
	public Date getArrangementDate() {
		return arrangementDate;
	}
	public void setArrangementDate(Date arrangementDate) {
		this.arrangementDate = arrangementDate;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getReceiverIdentifier() {
		return receiverIdentifier;
	}
	public void setReceiverIdentifier(String receiverIdentifier) {
		this.receiverIdentifier = receiverIdentifier;
	}
	public int getACCOUNT_ID() {
		return ACCOUNT_ID;
	}
	public void setACCOUNT_ID(int ACCOUNT_ID) {
		this.ACCOUNT_ID = ACCOUNT_ID;
	}


	
	
}
	/*private static final long serialVersionUID = 1L;
	private String uuId;
	private String reconciliationId;
	private String customerName;
	private String status;
	private String VKN_TCKNO;
	private Date arrangementDate;
	private Date loadingDate;//= new Date();
	private BigDecimal amount;	
	
	
	
	public String getUuId() {
		return uuId;
	}
	public void setUuId(String uuId) {
		this.uuId = uuId;
	}
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
	public String getReconciliationId() {
		return reconciliationId;
	}
	public void setReconciliationId(String reconciliationId) {
		this.reconciliationId = reconciliationId;
	}
	public Date getArrangementDate() {
		return arrangementDate;
	}
	public void setArrangementDate(Date arrangementDate) {
		this.arrangementDate = arrangementDate;
	}*/



