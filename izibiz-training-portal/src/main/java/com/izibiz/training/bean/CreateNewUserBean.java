package com.izibiz.training.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;

import com.izibiz.training.bean.base.GenericBean;
import com.izibiz.training.entity.dto.BusinessPartnerDTO;
import com.izibiz.training.entity.dto.CompanyDTO;
import com.izibiz.training.entity.dto.DataRepo;
import com.izibiz.training.entity.dto.UserDTO;

@ManagedBean
@ViewScoped
public class CreateNewUserBean extends GenericBean<CreateNewUserBean> {

	private BusinessPartnerDTO businessPartnerDTO;
	private CompanyDTO companyDTO;
	private UserDTO userDTO;
	private List<String> channelList;
	private List<String> dealerList;
	private List<String> accountList;
	private String selectedChannel;
	private String selectedDealer;
	private String selectedAccount;
	
	private List<String> activationTypeList;
	private List<String> companyTypeList;
	private List<String> customerTypeList;
	private String selectedActivationType;
	private String selectedCompanyType;
	private String selectedCustomerType;
	private String selectedIdentifier;

	public void openPage() {
		businessPartnerDTO = new BusinessPartnerDTO();
		companyDTO = new CompanyDTO();
		userDTO = new UserDTO();
		dealerList = new ArrayList<>();
		accountList = new ArrayList<>();
		loadChannelList();
		
		activationTypeList = new ArrayList<>();
		companyTypeList = new ArrayList<>();
		customerTypeList = new ArrayList<>();
		activationTypeList.addAll(DataRepo.activationTypeList);
		companyTypeList.addAll(DataRepo.companyTypeList);
		customerTypeList = new ArrayList<>();
		customerTypeList.addAll(DataRepo.customerTypeList);
		
		
	}

	public String onFlowWizard(FlowEvent event) {
		return event.getNewStep();
	}

	public void loadChannelList() {
		channelList = getChannelList();
	}

	public List<String> getDealers() {
		selectedDealer=null;
		accountList= new ArrayList<>();
		dealerList = new ArrayList<String>();
		if(selectedChannel!=null && StringUtils.isNotEmpty(selectedChannel.trim())) {
			dealerList.addAll(DataRepo.getDealers(selectedChannel));
		}else {
			FacesContext.getCurrentInstance().validationFailed();
			addErrorMessage(getMsg("app.portal.user.create-new-user.wizard.tabs.company.inputs.channel.validation.error.channelIsEmpty.text"));
		}
		return dealerList;
	}

	public List<String> getAccounts() {
		selectedAccount=null;
		accountList = new ArrayList<String>();
		if(selectedDealer!=null && StringUtils.isNotEmpty(selectedDealer.trim())) {
			accountList.addAll(DataRepo.getAccounts(selectedDealer));
		}else {
			addErrorMessage(getMsg("app.portal.user.create-new-user.wizard.tabs.company.inputs.channel.validation.error.dealerIsEmpty.text"));
		}
		
		return accountList;
	}

	private boolean validatetabCompany() {
		if(StringUtils.isEmpty(selectedChannel)) {
			FacesContext.getCurrentInstance().validationFailed();
			addErrorMessage(getMsg("app.portal.user.create-new-user.wizard.tabs.company.inputs.channel.validation.error.channelIsEmpty.text"));
			return false;
		}else if(StringUtils.isEmpty(selectedDealer)) {
			FacesContext.getCurrentInstance().validationFailed();
			addErrorMessage(getMsg("app.portal.user.create-new-user.wizard.tabs.company.inputs.channel.validation.error.dealerIsEmpty.text"));
			return false;
		}else if(StringUtils.isEmpty(selectedAccount)) {
			FacesContext.getCurrentInstance().validationFailed();
			addErrorMessage(getMsg("app.portal.user.create-new-user.wizard.tabs.company.inputs.channel.validation.error.accountIsEmpty.text"));
			return false;
		}
		return true;
	}
	public BusinessPartnerDTO getBusinessPartnerDTO() {
		return businessPartnerDTO;
	}

	public void setBusinessPartnerDTO(BusinessPartnerDTO businessPartnerDTO) {
		this.businessPartnerDTO = businessPartnerDTO;
	}

	public CompanyDTO getCompanyDTO() {
		return companyDTO;
	}

	public void setCompanyDTO(CompanyDTO companyDTO) {
		this.companyDTO = companyDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String getSelectedChannel() {
		return selectedChannel;
	}

	public void setSelectedChannel(String selectedChannel) {
		this.selectedChannel = selectedChannel;
	}

	public String getSelectedDealer() {
		return selectedDealer;
	}

	public void setSelectedDealer(String selectedDealer) {
		this.selectedDealer = selectedDealer;
	}

	public String getSelectedAccount() {
		return selectedAccount;
	}

	public void setSelectedAccount(String selectedAccount) {
		this.selectedAccount = selectedAccount;
	}

	public List<String> getChannelList() {
		return DataRepo.channels;
	}

	public List<String> getDealerList() {
		return dealerList;
	}

	public void setDealerList(List<String> dealerList) {
		this.dealerList = dealerList;
	}

	public List<String> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<String> accountList) {
		this.accountList = accountList;
	}

	public List<String> getActivationTypeList() {
		return activationTypeList;
	}

	public void setActivationTypeList(List<String> activationTypeList) {
		this.activationTypeList = activationTypeList;
	}

	public List<String> getCompanyTypeList() {
		return companyTypeList;
	}

	public void setCompanyTypeList(List<String> companyTypeList) {
		this.companyTypeList = companyTypeList;
	}

	public List<String> getCustomerTypeList() {
		return customerTypeList;
	}

	public void setCustomerTypeList(List<String> customerTypeList) {
		this.customerTypeList = customerTypeList;
	}

	public String getSelectedActivationType() {
		return selectedActivationType;
	}

	public void setSelectedActivationType(String selectedActivationType) {
		this.selectedActivationType = selectedActivationType;
	}

	public String getSelectedCompanyType() {
		return selectedCompanyType;
	}

	public void setSelectedCompanyType(String selectedCompanyType) {
		this.selectedCompanyType = selectedCompanyType;
	}

	public String getSelectedCustomerType() {
		return selectedCustomerType;
	}

	public void setSelectedCustomerType(String selectedCustomerType) {
		this.selectedCustomerType = selectedCustomerType;
	}

	public String getSelectedIdentifier() {
		return selectedIdentifier;
	}

	public void setSelectedIdentifier(String selectedIdentifier) {
		this.selectedIdentifier = selectedIdentifier;
	}

}
