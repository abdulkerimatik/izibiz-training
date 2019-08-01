package com.izibiz.training.dao.base;

import java.util.List;

import com.izibiz.training.dao.common.GenericDao;
import com.izibiz.training.entity.Archive;
import com.izibiz.training.entity.dto.ArchiveGDTO;

public interface ArchiveDao extends GenericDao<Archive> {

	
	
	public List<Archive> getAllArchiveWithDirection(String direction);

	public List<ArchiveGDTO> getAllAsDto();

	public Archive findArchiveByUuidAndDirection(String uuid,String direction);

	
	
	
}


