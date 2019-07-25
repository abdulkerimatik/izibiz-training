package com.izibiz.training.bean;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import com.izibiz.training.bean.base.GenericBean;
import com.izibiz.training.entity.dto.CustomerClientDTO;
import com.izibiz.training.service.CustomerClientService;
import com.izibiz.training.service.base.CustomerClientServiceImpl;
@ManagedBean
@ViewScoped
public class EditCustomerBean extends GenericBean<EditCustomerBean>{
	private String passedIdentifier;
	private CustomerClientService service;
	private CustomerClientDTO customerClientDTO;
	public void openPage() {
		service = new CustomerClientServiceImpl();
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		passedIdentifier = params.get("identifierParam");
		if(StringUtils.isEmpty(passedIdentifier)) {
			System.out.println("Düzenlenecek kayıt bulunamadı");
			return;
		}else {
			customerClientDTO =  service.findByIdentifier(passedIdentifier);
			if(customerClientDTO==null) {
				System.out.println("Düzenlenecek kayıt bulunamadı");
				return;
			}else {
				System.out.println("Passed Customer title is:"+customerClientDTO.getTitle());
			}
		}
	}
}
