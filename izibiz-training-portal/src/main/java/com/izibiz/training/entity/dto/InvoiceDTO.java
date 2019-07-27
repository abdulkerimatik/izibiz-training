package com.izibiz.training.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InvoiceDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -915029403718064589L;
	private Long id;
	private String uuid;
	private String invoiceId;
	private String receiverName;
	private String receiverIdentifier;
	private String status;
	private String subStatus;
	private String readStatus;
	private Date issueDate;
	private Date createDate;
	private BigDecimal payableAmount;
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
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverIdentifier() {
		return receiverIdentifier;
	}
	public void setReceiverIdentifier(String receiverIdentifier) {
		this.receiverIdentifier = receiverIdentifier;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubStatus() {
		return subStatus;
	}
	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}
	public String getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public BigDecimal getPayableAmount() {
		return payableAmount;
	}
	public void setPayableAmount(BigDecimal payableAmount) {
		this.payableAmount = payableAmount;
	}

	
	
}
