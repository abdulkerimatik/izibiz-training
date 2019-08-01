package com.izibiz.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.izibiz.training.dao.base.ArchiveDao;
import com.izibiz.training.entity.Archive;
import com.izibiz.training.entity.dto.ArchiveGDTO;
import com.izibiz.training.service.base.ArchiveService;

@Transactional
public class ArchiveServiceImpl implements ArchiveService {

	
	@Autowired
	private ArchiveDao archiveDao;

	
	
	@Override
	public List<Archive> getAllArchiveWithDirection(String direction) {
		
		return archiveDao.getAllArchiveWithDirection(direction);
	}

	@Override
	public List<ArchiveGDTO> getAllAsDto() {
	
		return archiveDao.getAllAsDto();
	}

	@Override
	public void saveOrUpdate(Archive Archive) {
		
		archiveDao.saveOrUpdate(Archive);
	}
	

	@Override
	public Archive findArchiveByUuidAndDirection(String uuid, String direction) {
		
		return archiveDao.findArchiveByUuidAndDirection(uuid, direction);
	}

	
	
	@Override
	public void deleteArchive(Archive Archive) {
		archiveDao.delete(Archive);		
	}
	
	

	
	
	
	
	
	
	

}
