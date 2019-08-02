package com.izibiz.training.lazy.model;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.izibiz.training.entity.dto.InvoiceDTO;
import com.izibiz.training.service.base.InvoiceService;

public class InvoiceDtoLazyModel extends LazyDataModel<InvoiceDTO> {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = -6420110949260171973L;
	private List<InvoiceDTO> invoiceDTOs;
	private InvoiceService invoiceService;
	private Map<String, Object> filtermap;
	
	
    public InvoiceDtoLazyModel(InvoiceService invoiceService) {
        this.invoiceService=invoiceService;
    }
    
    
    @Override
    public InvoiceDTO getRowData(String rowKey) {
        for(InvoiceDTO inv : invoiceDTOs) {
            if(inv.getId().equals(rowKey))
                return inv;
        }
        return null;
    }
 
    
    @Override
    public Object getRowKey(InvoiceDTO inv) {
        return inv.getId();
    }
 
    @Override
    public List<InvoiceDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
    		long reusltCount = getInvoiceService().getInvoiceDtoCount(filtermap);
		setRowCount((int)reusltCount);
		return getInvoiceService().getInvoiceDtos(first, pageSize, sortField, sortOrder, filtermap);
    }

	public InvoiceService getInvoiceService() {
		return invoiceService;
	}

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	public Map<String, Object> getFiltermap() {
		return filtermap;
	}

	public void setFiltermap(Map<String, Object> filtermap) {
		this.filtermap = filtermap;
	}
}