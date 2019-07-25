package com.izibiz.training.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang3.StringUtils;

import com.izibiz.training.bean.base.GenericBean;
import com.izibiz.training.entity.dto.CustomerClientDTO;
import com.izibiz.training.service.CustomerClientService;
import com.izibiz.training.service.base.CustomerClientServiceImpl;

@ManagedBean
@ViewScoped
public class NewCustomerBean extends GenericBean<NewCustomerBean> {

	private String identifier;
	private CustomerClientDTO customerClientDTO;
	private CustomerClientService customerClientService;
	private CustomerClientDTO selectedCustomer;

	public void loadPage() {
		customerClientService = new CustomerClientServiceImpl();
	}

	public List<CustomerClientDTO> completeIdentifier(String searchValue) {
		List<CustomerClientDTO> resultList = new ArrayList<>();
		if (StringUtils.isNotEmpty(searchValue) && searchValue.length() > 5) {
			resultList.addAll(customerClientService.findByIdentifierWithLikeUsingLimit(searchValue, 5));
		}
		return resultList;
	}

	public void saveCustomer() {
		System.out.println(selectedCustomer.getIdentifier());
	}
	
	public void printSelection() {
		System.out.println(selectedCustomer.getIdentifier());
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public CustomerClientDTO getCustomerClientDTO() {
		return customerClientDTO;
	}

	public void setCustomerClientDTO(CustomerClientDTO customerClientDTO) {
		this.customerClientDTO = customerClientDTO;
	}

	public CustomerClientDTO getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(CustomerClientDTO selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}
}
