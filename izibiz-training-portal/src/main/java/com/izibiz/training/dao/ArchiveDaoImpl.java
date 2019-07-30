package com.izibiz.training.dao;

import java.util.List;

import com.izibiz.training.dao.base.ArchiveDao;
import com.izibiz.training.dao.common.GenericDaoHibernateImpl;
import com.izibiz.training.entity.Archive;
import com.izibiz.training.entity.dto.ArchiveGDTO;

public class ArchiveDaoImpl extends GenericDaoHibernateImpl<Archive> implements ArchiveDao {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public ArchiveDaoImpl() {
		super(Archive.class);
	}



	@Override
	public List<ArchiveGDTO> findByArchiveId(String archiveID) {
		
		return null;
	}



	
	
	
	
	
	
}
