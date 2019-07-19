package com.izibiz.training.entity.dto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

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
	
public static List<String> despatchSeriesList = Arrays.asList("IRS","ABC","XY1");
	
	public static Map<String,BigDecimal> seriesMap = new HashMap<>();
	static {
		seriesMap.put(DEFAULT_SERIAL_ID, BigDecimal.valueOf(2019000000000l));
		for(String serial : despatchSeriesList) {
			seriesMap.put(serial, BigDecimal.valueOf(2019000000000l));
		}
	}
	
	public static BigDecimal getSerialNoFromId(String despatchId) {
		if(StringUtils.isEmpty(despatchId)) {
			return null;
		}else {
			if(despatchId.length()==16) {
				String no = despatchId.substring(3);
				BigDecimal serialNo = BigDecimal.valueOf(Double.parseDouble(no));
				return serialNo;
			}
			return null;
		}
	}
	
	public static String getSerialFromId(String despatchId) {
		if(StringUtils.isEmpty(despatchId)) {
			return null;
		}else {
			if(despatchId.length()==16) {
				String no = despatchId.substring(0,2);
				return no;
			}
			return null;
		}
	}
	
	public static String getIdFromSerial(String serial) {
		BigDecimal serialNo = seriesMap.get(serial);
		if(serialNo!=null) {
			String despatchId = serial.concat(serialNo.add(BigDecimal.valueOf(1)).toPlainString());
			return despatchId;
		}
		return null;
	}
	
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
