package com.izibiz.training.lazy.model;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.izibiz.training.entity.dto.InvoiceDTO;
import com.izibiz.training.entity.dto.ReconciDTO;
import com.izibiz.training.service.base.InvoiceService;
import com.izibiz.training.service.base.ReconciliationService;


public class ReconciliationDtoLazyModel extends LazyDataModel<ReconciDTO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ReconciDTO> reconciDTOs;
	private ReconciliationService reconciliationService;
	private Map<String, Object> filtermap;
	
    public ReconciliationDtoLazyModel(ReconciliationService reconciliationService) {
        this.reconciliationService=reconciliationService;
    }
     
    @Override
    public ReconciDTO getRowData(String rowKey) {
        for(ReconciDTO rcn : reconciDTOs) {
            if(rcn.getUuid().equals(rowKey))
            System.out.println(rcn);
            	return rcn;
            
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(ReconciDTO rcn) {
        return rcn.getUuid();
    }
 
    @Override
    public List<ReconciDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
   		long reusltCount = getReconciliationService().getReconciDtoCount(filtermap);
		setRowCount((int)reusltCount);
		 
		return  getReconciliationService().getReconciDTOs(first, pageSize, sortField, sortOrder, filtermap);	
    }
    
    
	public ReconciliationService getReconciliationService() {
		return reconciliationService;
	}

	public void setReconciliationService(ReconciliationService reconciliationService) {
		this.reconciliationService = reconciliationService;
	}

	public Map<String, Object> getFiltermap() {
		return filtermap;
	}

	public void setFiltermap(Map<String, Object> filtermap) {
		this.filtermap = filtermap;
	}
}
