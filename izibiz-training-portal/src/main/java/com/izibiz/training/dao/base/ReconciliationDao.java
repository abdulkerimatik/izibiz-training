package com.izibiz.training.dao.base;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import com.izibiz.training.dao.common.GenericDao;
import com.izibiz.training.entity.Reconciliation;
import com.izibiz.training.entity.dto.ReconciDTO;


public interface ReconciliationDao extends GenericDao<Reconciliation>{
	
	//List getAllReconcipatchesWithType
	List<Reconciliation> getAlls();
	List<ReconciDTO> getAllas();
	
	List<ReconciDTO> getReconciDTOs(int first, int pageSize, String sortField, SortOrder sortOrder,Map<String, Object> filters);
	
	long getReconciDtoCount(Map<String, Object> filters);
}
