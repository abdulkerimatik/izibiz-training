package com.izibiz.training.bean;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.izibiz.training.bean.base.GenericBean;
import com.izibiz.training.entity.dto.CustomerClientDTO;
import com.izibiz.training.entity.dto.DataRepo;
import com.izibiz.training.service.CustomerClientService;
import com.izibiz.training.service.base.CustomerClientServiceImpl;

@ManagedBean
@ViewScoped
public class CustomerClientListBean extends GenericBean<CustomerClientDTO>{

	private List<CustomerClientDTO> customerList;
	private List<CustomerClientDTO> filteredCustomerList;
	private CustomerClientDTO selectedCustomerClient;
	private CustomerClientService customerClientService;
	public void loadPage() {
		if(customerClientService==null)
			customerClientService = new CustomerClientServiceImpl();
		customerList = new ArrayList<>();
		filteredCustomerList = new ArrayList<>();
		customerList.addAll(customerClientService.getAll());
		//filteredCustomerList.addAll(customerList);
	}

	public void updateStatus() {
		if(selectedCustomerClient==null) {
			addErrorMessage("Hata!");
			return;
		}
		if(customerClientService.updateCustomerClient(selectedCustomerClient)) {
			addInfoMessage(selectedCustomerClient.getTitle()+" firmasının durumu güncellendi!");
		}
		loadPage();
	}
	
	public void showInfo() {
		addInfoMessage("Client Code: "+selectedCustomerClient.getClientCode());
	}
	
	public List<CustomerClientDTO> getFilteredCustomerList() {
		return filteredCustomerList;
	}

	public void setFilteredCustomerList(List<CustomerClientDTO> filteredCustomerList) {
		this.filteredCustomerList = filteredCustomerList;
	}

	public List<CustomerClientDTO> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerClientDTO> customerList) {
		this.customerList = customerList;
	}

	public CustomerClientDTO getSelectedCustomerClient() {
		return selectedCustomerClient;
	}

	public void setSelectedCustomerClient(CustomerClientDTO selectedCustomerClient) {
		this.selectedCustomerClient = selectedCustomerClient;
	}
	
}
