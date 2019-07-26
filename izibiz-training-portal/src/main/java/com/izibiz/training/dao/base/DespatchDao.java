package com.izibiz.training.dao.base;

import java.util.List;

import com.izibiz.training.dao.common.GenericDao;
import com.izibiz.training.entity.Despatch;

public interface DespatchDao extends GenericDao<Despatch>{
	List getAllDespatchesWithType(String type);

	List getAll();

	Despatch findDespatchByUuid(String uuid);
}
