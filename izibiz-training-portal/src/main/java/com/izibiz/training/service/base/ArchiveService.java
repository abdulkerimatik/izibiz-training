package com.izibiz.training.service.base;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import com.izibiz.training.entity.Archive;
import com.izibiz.training.entity.dto.ArchiveGDTO;

public interface ArchiveService {

	
	public List<Archive> getAllArchiveWithDirection(String direction);

	public List<ArchiveGDTO> getAllAsDto();

	public void saveOrUpdate(Archive Archive);

	public Archive findArchiveByUuidAndDirection(String uuid,String direction);
	
	public ArchiveGDTO findArchiveeByid(long id);
	
	public void deleteArchive(Archive Archive);

	
	public List<ArchiveGDTO> getArchives(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filterEqual, Map<String, Object> filterContains);
	
	
	public long getArchivesCount(Map<String, Object> filterEqual, Map<String, Object> filterContains);
	
	
	
	
}
