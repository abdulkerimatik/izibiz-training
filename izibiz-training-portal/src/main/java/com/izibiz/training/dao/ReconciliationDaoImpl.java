package com.izibiz.training.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;

import com.izibiz.training.dao.base.ReconciliationDao;
import com.izibiz.training.dao.common.GenericDaoHibernateImpl;
import com.izibiz.training.entity.Reconciliation;
import com.izibiz.training.entity.dto.InvoiceDTO;
import com.izibiz.training.entity.dto.ReconciDTO;

@Repository
public class ReconciliationDaoImpl extends GenericDaoHibernateImpl<Reconciliation> implements ReconciliationDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ReconciliationDaoImpl() {
		super(Reconciliation.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<Reconciliation> getAlls() {
		String sql ="select * from reconciliation";
		SQLQuery sqlQuery =getCurrentSession().createSQLQuery(sql).addEntity(Reconciliation.class);
		return sqlQuery.list();
	}
	
	@Override
	public List<ReconciDTO> getReconciDTOs(int first, int pageSize, String sortField, SortOrder sortOrder,Map<String, Object> filters) {
//logger.debug("start econciliationDaoImpl.getReconciDtos with params ");
    	
    	String sql="SELECT i.id AS id, " + 
    				"	uuid AS uuid, " + 
    				"	loading_date AS  loadingDate, " + 
    				"	RECONCILIATION_ID AS  reconciliationId, " + 
    				"	status AS status, " + 
    				"	sender AS sender, " + 
    				"	p.name AS receiverName, " +
    				"	i.ARRANGEMENT_DATE AS arrangementDate, " + 
    				"	i.amount AS amount " + 
    				" FROM VEYSEL_KONT.RECONCILIATION i, VEYSEL_KONT.PARTY p " + 
    				" WHERE  i.CUSTOMER_PARTY_ID = p.id";
    		
    		StringBuilder sqlBuilder=new StringBuilder(sql);
    		buildSql(sqlBuilder, filters);
    		
    		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sqlBuilder.toString());
    		sqlQuery.setResultTransformer(Transformers.aliasToBean(ReconciDTO.class));
    		sqlQuery.addScalar("id", LongType.INSTANCE);
    		sqlQuery.addScalar("uuid");
    		sqlQuery.addScalar("reconciliationId");
    		sqlQuery.addScalar("status");
    		sqlQuery.addScalar("sender");
    		sqlQuery.addScalar("receiverName");
    		sqlQuery.addScalar("amount",BigDecimalType.INSTANCE);
    		sqlQuery.addScalar("loadingDate", DateType.INSTANCE);
    		sqlQuery.addScalar("arrangementDate", DateType.INSTANCE);
		
    		sqlQuery.setFirstResult(first);
    		sqlQuery.setMaxResults(pageSize);
		
		List<ReconciDTO> reconciDTOs=sqlQuery.list();
	//	logger.debug("end InvoiceDaoImpl.getInvoiceDtos with params ");

		
    		return reconciDTOs;
	}
	private void buildSql(StringBuilder sqlBuilder, Map<String, Object> filters) {
		for (Entry<String, Object> filter : filters.entrySet()) {
			String key = filter.getKey();
			Object value = filter.getValue();
			switch (key) {
			case "accountID":
				sqlBuilder.append(" and ACCOUNT_ID=").append(value);
				break;
			case "direction":
				sqlBuilder.append(" and direction='").append(value).append("'");
				break;
			default:
				break;
			}
		}	
		
	}
	@Override
	public long getReconciDtoCount(Map<String, Object> filters) {
		long resultCount=0;
    	String sql="SELECT count(*) "+
    				" FROM VEYSEL_KONT.RECONCILIATION i, VEYSEL_KONT.PARTY p " + 
    				" WHERE  i.CUSTOMER_PARTY_ID = p.id";
    		
    		StringBuilder sqlBuilder=new StringBuilder(sql);
    		buildSql(sqlBuilder, filters);
    		
    		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sqlBuilder.toString());
		
    		Number count=(Number) sqlQuery.uniqueResult();
		if(count!=null)
			resultCount=count.longValue();
		return resultCount;
	}

	@Override
	public List<ReconciDTO> getAllas() {
		// TODO Auto-generated method stub
		return null;
	}
}
