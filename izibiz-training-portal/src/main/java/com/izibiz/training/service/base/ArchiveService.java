package com.izibiz.training.service.base;

import java.util.List;

import com.izibiz.training.entity.Archive;
import com.izibiz.training.entity.Despatch;
import com.izibiz.training.entity.dto.ArchiveGDTO;

public interface ArchiveService {

	
	public List<Archive> getAllArchiveWithDirection(String direction);

	public List<ArchiveGDTO> getAllAsDto();

	public void saveOrUpdate(Archive Archive);

	public Archive findArchiveByUuidAndDirection(String uuid,String direction);
	
	public void deleteArchive(Archive Archive);

}
