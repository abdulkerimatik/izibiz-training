package com.izibiz.training.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.izibiz.training.dao.base.ArchiveDao;
import com.izibiz.training.dao.common.GenericDaoHibernateImpl;
import com.izibiz.training.entity.Archive;
import com.izibiz.training.entity.dto.ArchiveGDTO;

@Repository
public class ArchiveDaoImpl extends GenericDaoHibernateImpl<Archive> implements ArchiveDao {

	private static final long serialVersionUID = 1L;

	public ArchiveDaoImpl() {
		super(Archive.class);
	}

	@Override
	public List<Archive> getAllArchiveWithDirection(String direction) {

		Map<String, Object> filter = new HashMap<>();
		filter.put("direction", direction);
		return getMatching(filter);

		/*
		 * Criteria crt = getCurrentSession().createCriteria(Archive.class).add(Restrictions.eq("direction", direction));
		 *  return (List<Archive>) crt.list();
		 */
	}

	@Override
	public List<ArchiveGDTO> getAllAsDto() {

		String sql = "select * from archive";
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql).addEntity(ArchiveGDTO.class);

		return sqlQuery.list();
	}
	

	@Override
	public Archive findArchiveByUuidAndDirection(String uuid, String direction) {

		Criteria crt = getCurrentSession().createCriteria(Archive.class).add(Restrictions.eq("UUID", uuid))
				.add(Restrictions.eq("DIRECTION", direction));
		return (Archive) crt.uniqueResult();

	}
	
	
	

}
