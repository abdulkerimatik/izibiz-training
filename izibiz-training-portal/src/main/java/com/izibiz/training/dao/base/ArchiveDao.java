package com.izibiz.training.dao.base;

import java.util.List;

import com.izibiz.training.dao.common.GenericDao;
import com.izibiz.training.entity.Archive;
import com.izibiz.training.entity.dto.ArchiveGDTO;

public interface ArchiveDao extends GenericDao<Archive> {
	
	
	public List<ArchiveGDTO> findByArchiveId(String archiveID);

	

}
