package com.izibiz.training.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;

public class ArchiveGDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2384229819311637553L;
	//enumlar
	public static final String LOAD="LOAD";
	public static final String SENT="SENT";
	public static final String RECEIVED="RECEIVED";
	public static final String DEFAULT_SERIAL_ID="IZI";
	public static final String DRAFT="DRAFT";
	public static final String OUT="OUT";
	public static final String IN="IN";
	//
	
	private long id;
	private String uuid;
	private String archiveId;
	private String direction;
	private String sender;
	private String senderIdentifier;
	private String receiver;
	private String receiverIdentifier;
	private String status;
	private Date archiveDate;
	private BigDecimal amount;
	private String profileId;
	private String archiveType;
	private String sendingType;
	private boolean sendMail;
	private boolean sendSms;

	
	
//

	public boolean isSendMail() {
		return sendMail;
	}

	public void setSendMail(boolean sendMail) {
		this.sendMail = sendMail;
	}

	public boolean isSendSms() {
		return sendSms;
	}

	public void setSendSms(boolean sendSms) {
		this.sendSms = sendSms;
	}

	public String getSendingType() {
		return sendingType;
	}

	public void setSendingType(String sendingType) {
		this.sendingType = sendingType;
	}

	public String getArchiveType() {
		return archiveType;
	}

	public void setArchiveType(String archiveType) {
		this.archiveType = archiveType;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getArchiveId() {
		return archiveId;
	}

	public void setArchiveId(String archiveId) {
		this.archiveId = archiveId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String senderName) {
		this.sender = senderName;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiverTcVkn) {
		this.receiver = receiverTcVkn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getArchiveDate() {
		return archiveDate;
	}

	public void setArchiveDate(Date archiveDate) {
		this.archiveDate = archiveDate;
	}


	public String getReceiverIdentifier() {
		return receiverIdentifier;
	}

	public void setReceiverIdentifier(String receiverIdentifier) {
		this.receiverIdentifier = receiverIdentifier;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSenderIdentifier() {
		return senderIdentifier;
	}

	public void setSenderIdentifier(String senderIdentifier) {
		this.senderIdentifier = senderIdentifier;
	}

	
	
}
