package com.izibiz.training.service.base;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import com.izibiz.training.entity.Reconciliation;
import com.izibiz.training.entity.dto.InvoiceDTO;
import com.izibiz.training.entity.dto.ReconciDTO;


public interface ReconciliationService {
	
	List<Reconciliation> getAlls();
	List<Reconciliation> getAll();
	void saveOrUpdate(Reconciliation reco);
	void delete(Reconciliation reco);
	
	List<ReconciDTO> getReconciDTOs(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters);
	
	long getReconciDtoCount(Map<String, Object> filters);

}
