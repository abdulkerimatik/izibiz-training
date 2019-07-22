package com.izibiz.training.entity.dto;

public class CustomerClientDTO {

	private String clientCode;
	private String branchType;
	private String title;
	private String identifier;
	private String taxOffice;
	private String eMail;
	private String telephone;
	private String city;
	private String region;
	private String  address;
	private boolean status;
	
	public CustomerClientDTO(String clientCode,String branchType,String title,String identifier,boolean status) {
		this.clientCode = clientCode;
		this.branchType = branchType;
		this.title = title;
		this.status = status;
		this.identifier=identifier;
	}
	
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getBranchType() {
		return branchType;
	}
	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getTaxOffice() {
		return taxOffice;
	}
	public void setTaxOffice(String taxOffice) {
		this.taxOffice = taxOffice;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
