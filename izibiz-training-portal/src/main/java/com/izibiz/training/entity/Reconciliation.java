package com.izibiz.training.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import oracle.net.aso.s;
		
@Entity
@Table(name = "RECONCILIATION")
public class Reconciliation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String uuid;
	private String reconcilitionId;
	private String sender;
	private String receiver;
	private Date loadingDate;
	private Date arrangementDate;
	private BigDecimal amount;
	private String DIRECTION;
	private String status;
	private int customerPartyId;
	private int acountId;
	
	
	@Id
	@GeneratedValue(generator = "reconcilition")
	@SequenceGenerator(name = "reconcilition", sequenceName = "SEQ_RECONCILIATION",initialValue = 1,allocationSize = 1)
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
	
	@Column(name="RECONCILIATION_ID")
	public String getReconcilitionId() {
		return reconcilitionId;
	}
	public void setReconcilitionId(String reconcilitionId) {
		this.reconcilitionId = reconcilitionId;
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
	
	@Column(name="LOADING_DATE")
	public Date getLoadingDate() {
		return loadingDate;
	}
	public void setLoadingDate(Date loadingDate) {
		this.loadingDate = loadingDate;
	}
	@Column(name="ARRANGEMENT_DATE")
	public Date getArrangementDate() {
		return arrangementDate;
	}
	public void setArrangementDate(Date arrangementDate) {
		this.arrangementDate = arrangementDate;
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
	@Column(name="DIRECTION")
	public String getDIRECTION() {
		return DIRECTION;
	}
	public void setDIRECTION(String dIRECTION) {
		DIRECTION = dIRECTION;
	}
	@Column(name="CUSTOMER_PARTY_ID")
	public int getCustomerPartyId() {
		return customerPartyId;
	}
	public void setCustomerPartyId(int customerPartyId) {
		this.customerPartyId = customerPartyId;
	}
	@Column(name="ACCOUNT_ID")
	public int getAcountId() {
		return acountId;
	}
	public void setAcountId(int acountId) {
		this.acountId = acountId;
	}
	
	
}
