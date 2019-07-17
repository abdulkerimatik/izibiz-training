package com.izibiz.training.entity.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DespatchDTO {

	public static final String LOAD="LOAD";
	public static final String SENT="SENT";
	public static final String RECEIVED="RECEIVED";
	public static final String DEFAULT_SERIAL_ID="IZI";
	
	private String uuid;
	private String despatchId;
	private String sender;
	private String receiver;
	private Date despatchDate;
	private BigDecimal amount;
	private String status;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getDespatchId() {
		return despatchId;
	}
	public void setDespatchId(String despatchId) {
		this.despatchId = despatchId;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public Date getDespatchDate() {
		return despatchDate;
	}
	public void setDespatchDate(Date despatchDate) {
		this.despatchDate = despatchDate;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
