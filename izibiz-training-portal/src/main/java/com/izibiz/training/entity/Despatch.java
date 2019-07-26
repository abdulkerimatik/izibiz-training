package com.izibiz.training.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DESPATCH")
public class Despatch implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String uuid;
	private String despatchId;
	private String sender;
	private String receiver;
	private Date despatchDate;
	private BigDecimal amount;
	private String status;
	
	@Id
	@GeneratedValue(generator = "despatch_gen")
	@SequenceGenerator(name = "despatch_gen", sequenceName = "despatch_seq")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "UUID")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	@Column(name="DESPATCH_ID")
	public String getDespatchId() {
		return despatchId;
	}
	public void setDespatchId(String despatchId) {
		this.despatchId = despatchId;
	}
	
	@Column(name="SENDER")
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	@Column(name="RECEIVER")
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	@Column(name="DESPATCH_DATE")
	public Date getDespatchDate() {
		return despatchDate;
	}
	public void setDespatchDate(Date despatchDate) {
		this.despatchDate = despatchDate;
	}
	
	@Column(name="AMOUNT")
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
