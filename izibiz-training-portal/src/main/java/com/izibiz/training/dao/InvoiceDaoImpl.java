package com.izibiz.training.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;

import com.izibiz.training.dao.base.InvoiceDao;
import com.izibiz.training.dao.common.GenericDaoHibernateImpl;
import com.izibiz.training.entity.dto.InvoiceDTO;

@Repository
public class InvoiceDaoImpl extends GenericDaoHibernateImpl<InvoiceDTO> implements InvoiceDao{

	private static final Logger logger=Logger.getLogger(InvoiceDaoImpl.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 3473952407068379869L;



	public InvoiceDaoImpl() {
		super(InvoiceDTO.class);
	}

	@Override
    public List<InvoiceDTO> getInvoiceDtos(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
    	logger.debug("start InvoiceDaoImpl.getInvoiceDtos with params ");
    	
    	String sql="SELECT i.id AS id, " + 
    				"	uuid AS uuid, " + 
    				"	issue_date AS  issueDate, " + 
    				"	invoice_id AS  invoiceId, " + 
    				"	status AS status, " + 
    				"	sub_status AS subStatus, " + 
    				"	READ_STATUS AS readStatus, " + 
    				"	p.name AS receiverName, " + 
    				"	p.IDENTIFIER AS receiverIdentifier, " + 
    				"	i.cdate AS createDate, " + 
    				"	i.payable_amount AS payableAmount " + 
    				" FROM EFATURADEV.INVOICE i, EFATURADEV.party p " + 
    				" WHERE  i.CUSTOMER_PARTY_ID = p.id";
    		
    		StringBuilder sqlBuilder=new StringBuilder(sql);
    		buildSql(sqlBuilder, filters);
    		
    		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sqlBuilder.toString());
    		sqlQuery.setResultTransformer(Transformers.aliasToBean(InvoiceDTO.class));
		sqlQuery.addScalar("id", LongType.INSTANCE);
		sqlQuery.addScalar("uuid");
		sqlQuery.addScalar("invoiceId");
		sqlQuery.addScalar("status");
		sqlQuery.addScalar("subStatus");
		sqlQuery.addScalar("readStatus");
		sqlQuery.addScalar("receiverName");
		sqlQuery.addScalar("payableAmount",BigDecimalType.INSTANCE);
		sqlQuery.addScalar("receiverIdentifier");
		sqlQuery.addScalar("createDate", DateType.INSTANCE);
		sqlQuery.addScalar("issueDate", DateType.INSTANCE);
		
		sqlQuery.setFirstResult(first);
		sqlQuery.setMaxResults(pageSize);
		
		List<InvoiceDTO> invoiceDTOs=sqlQuery.list();
		logger.debug("end InvoiceDaoImpl.getInvoiceDtos with params ");

		
    		return invoiceDTOs;
    	
    }
    
    
	private void buildSql(StringBuilder sqlBuilder, Map<String, Object> filters) {
		for (Entry<String, Object> filter : filters.entrySet()) {
			String key = filter.getKey();
			Object value = filter.getValue();
			switch (key) {
			case "accountId":
				sqlBuilder.append(" and account_Id=").append(value);
				break;
			case "direction":
				sqlBuilder.append(" and direction=").append(value);
				break;
			default:
				break;
			}
		}
	}
	
	@Override
    public long getInvoiceDtoCount(Map<String,Object> filters) {
    	logger.debug("start InvoiceDaoImpl.getInvoiceDtos with params ");
    	long resultCount=0;
    	String sql="SELECT count(*) "+
    				" FROM EFATURADEV.INVOICE i, EFATURADEV.party p " + 
    				" WHERE  i.CUSTOMER_PARTY_ID = p.id";
    		
    		StringBuilder sqlBuilder=new StringBuilder(sql);
    		buildSql(sqlBuilder, filters);
    		
    		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sqlBuilder.toString());
		
    		Number count=(Number) sqlQuery.uniqueResult();
		if(count!=null)
			resultCount=count.longValue();
		
		logger.debug("end InvoiceDaoImpl.getInvoiceDtos with params ");

		
    		return resultCount;
    	
    }

	@Override
	public List<InvoiceDTO> findByInvoiceId(String invoiceID) {
		// TODO Auto-generated method stub
		return null;
	}

    	


	

}
