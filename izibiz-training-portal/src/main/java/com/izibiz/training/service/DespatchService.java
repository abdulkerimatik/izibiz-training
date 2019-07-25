package com.izibiz.training.service;

import java.util.List;

import com.izibiz.training.entity.dto.DespatchDTO;

public interface DespatchService {
	List<DespatchDTO> getAllDespatchesWithType(String type);

	List<DespatchDTO> getAll();
	boolean updateDespatch(DespatchDTO despatchDTO);

	boolean saveDespatch(DespatchDTO despatchDTO);

	boolean deleteDespatch(DespatchDTO despatchDTO);

	DespatchDTO findDespatchByUuid(String uuid);
}
