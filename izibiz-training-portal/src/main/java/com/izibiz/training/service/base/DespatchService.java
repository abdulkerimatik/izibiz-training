package com.izibiz.training.service.base;

import java.util.List;

import com.izibiz.training.entity.Despatch;

public interface DespatchService {
	
	 List<Despatch> getAllDespatchesWithType(String type);

	 List<Despatch> getAll();

	void saveOrUpdate(Despatch despatchDTO);


	Despatch findDespatchByUuid(String uuid);
}
