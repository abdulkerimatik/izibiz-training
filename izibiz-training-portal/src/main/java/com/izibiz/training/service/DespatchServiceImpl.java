package com.izibiz.training.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.izibiz.training.dao.base.DespatchDao;
import com.izibiz.training.entity.Despatch;
import com.izibiz.training.entity.dto.DataRepo;
import com.izibiz.training.entity.dto.DespatchDTO;
import com.izibiz.training.service.base.DespatchService;

@Transactional
public class DespatchServiceImpl implements DespatchService {

	@Autowired
	private DespatchDao despatchDao;
	@Override
	public List<Despatch> getAllDespatchesWithType(String type) {
		return despatchDao.getAllDespatchesWithType(type);
	}

	@Override
	public List<Despatch> getAll() {
		return despatchDao.getAll();
	}

	public List<Despatch> findByStatus(String status){
		Map<String,Object> mapFilter = new HashMap<>();
		mapFilter.put("status", status);
		return despatchDao.getOrderedMatchingDesc(mapFilter, "id");
	}

	@Override
	public void saveOrUpdate(Despatch despatchDTO) {
		despatchDao.saveOrUpdate(despatchDTO);
	}



	@Override
	public Despatch findDespatchByUuid(String uuid) {
		return despatchDao.findDespatchByUuid(uuid);
	}

	

}
