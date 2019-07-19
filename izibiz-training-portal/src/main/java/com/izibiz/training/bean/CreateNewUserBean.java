package com.izibiz.training.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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

	public void openPage() {
		businessPartnerDTO = new BusinessPartnerDTO();
		companyDTO = new CompanyDTO();
		userDTO = new UserDTO();
		loadChannelList();
	}

	public String onFlowWizard(FlowEvent event) {
		return event.getNewStep();
	}

	public void loadChannelList() {
		channelList = getChannelList();
	}

	public List<String> getDealers() {
		dealerList = null;
		accountList = null;
		selectedDealer=null;
		selectedAccount=null;
		if(StringUtils.isNotEmpty(selectedChannel)) {
			dealerList = new ArrayList<String>();
			dealerList.addAll(DataRepo.getDealers(selectedChannel));
		}
		
		return dealerList;
	}

	public List<String> getAccounts() {
		accountList = new ArrayList<String>();
		accountList.addAll(DataRepo.getAccounts(selectedDealer));
		return accountList;
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

}
