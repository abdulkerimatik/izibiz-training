package com.izibiz.training.service.base;

import java.util.ArrayList;
import java.util.List;

import com.izibiz.training.entity.dto.DataRepo;
import com.izibiz.training.entity.dto.DespatchDTO;
import com.izibiz.training.service.DespatchService;

public class DespatchServiceImpl implements DespatchService {

	@Override
	public List<DespatchDTO> getAllDespatchesWithType(String type) {
		List<DespatchDTO> list = new ArrayList<>();
		for(DespatchDTO despatch:DataRepo.despatches) {
			if(despatch.getStatus().equals(type)) {
				list.add(despatch);
			}
		}
		return list;
	}

	@Override
	public List<DespatchDTO> getAll() {
		List<DespatchDTO> list = new ArrayList<>();
		list.addAll(DataRepo.despatches);
		return list;
	}
	
	@Override
	public boolean updateDespatch(DespatchDTO despatchDTO) {
		for (DespatchDTO despatch : getAllDespatchesWithType(DespatchDTO.LOAD)) {
			if (despatchDTO.getUuid().equals(despatch.getUuid())) {
				DataRepo.despatches.remove(despatch);
				DataRepo.despatches.add(despatchDTO);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean saveDespatch(DespatchDTO despatchDTO) {
		return DataRepo.despatches.add(despatchDTO);
	}

	@Override
	public boolean deleteDespatch(DespatchDTO despatchDTO) {
		return DataRepo.despatches.remove(despatchDTO);
	}

	@Override
	public DespatchDTO findDespatchByUuid(String uuid) {
		DespatchDTO dto = null;
		for(DespatchDTO despatchDTO : DataRepo.despatches) {
			if(despatchDTO.getUuid().equals(uuid)) {
				dto = despatchDTO;
			}
		}
		return dto;
	}

}
