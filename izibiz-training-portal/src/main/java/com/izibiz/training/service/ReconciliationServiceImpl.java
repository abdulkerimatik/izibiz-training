package com.izibiz.training.service;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.izibiz.training.dao.base.ReconciliationDao;
import com.izibiz.training.entity.Reconciliation;
import com.izibiz.training.entity.dto.ReconciDTO;
import com.izibiz.training.service.base.ReconciliationService;

@Transactional
public class ReconciliationServiceImpl implements ReconciliationService{
	
	@Autowired
	private ReconciliationDao reconciliationDao;

	@Override
	public List<Reconciliation> getAlls() {
		
		return reconciliationDao.getAlls();
	}

	@Override
	public void saveOrUpdate(Reconciliation reco) {
		reconciliationDao.saveOrUpdate(reco);
		
	}

	@Override
	public List<Reconciliation> getAll() {
		
		return reconciliationDao.getAll();
	}

	@Override
	public void delete(Reconciliation reconcilition) {
		
		reconciliationDao.delete(reconcilition);
	}

	@Override
	public List<ReconciDTO> getReconciDTOs(int first, int pageSize, String sortField, SortOrder sortOrder,Map<String, Object> filters) {
		if (CollectionUtils.isEmpty(filters)) {
			throw new RuntimeException("filter must not empty");
		}
		return reconciliationDao.getReconciDTOs(first, pageSize, sortField, sortOrder, filters);
	}

	@Override
	public long getReconciDtoCount(Map<String, Object> filters) {
		return reconciliationDao.getReconciDtoCount(filters);
	}

	
	
	
	
}
