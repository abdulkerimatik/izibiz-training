package com.izibiz.training.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.izibiz.training.dao.base.DespatchDao;
import com.izibiz.training.dao.common.GenericDaoHibernateImpl;
import com.izibiz.training.entity.Despatch;


@Repository
public class DespatchDaoImpl extends GenericDaoHibernateImpl<Despatch> implements DespatchDao{

	public DespatchDaoImpl() {super(Despatch.class);}
	public DespatchDaoImpl(Class<Despatch> persistentClass) {
		super(persistentClass);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List getAllDespatchesWithType(String type) {
		String sql = "select * from despatch where status = :status";
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql).addEntity(Despatch.class);
		sqlQuery.setParameter("status", type);
		return sqlQuery.list();
	}

	@Override
	public Despatch findDespatchByUuid(String uuid) {
		Criteria crt = getCurrentSession().createCriteria(Despatch.class).add(Restrictions.eq("UUID", uuid));
		return (Despatch) crt.uniqueResult();
	}




}
