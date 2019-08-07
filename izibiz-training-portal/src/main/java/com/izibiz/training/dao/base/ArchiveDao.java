package com.izibiz.training.dao.base;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import com.izibiz.training.dao.common.GenericDao;
import com.izibiz.training.entity.Archive;
import com.izibiz.training.entity.dto.ArchiveGDTO;

public interface ArchiveDao extends GenericDao<Archive> {

	
	
	public List<Archive> getAllArchiveWithDirection(String direction);

	public List<ArchiveGDTO> getAllAsDto();

	public Archive findArchiveByUuidAndDirection(String uuid,String direction);

	
	public ArchiveGDTO findArchiveeById(long id);

	
	public List<ArchiveGDTO> getArchives(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filterEquals,Map<String, Object> filterContains);
	
	
	public long getArchivesCount(Map<String, Object> filterEquals,Map<String, Object> filterContains);
	
	
}


