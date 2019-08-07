package com.izibiz.training.dao;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.izibiz.training.dao.base.ArchiveDao;
import com.izibiz.training.dao.common.GenericDaoHibernateImpl;
import com.izibiz.training.entity.Archive;
import com.izibiz.training.entity.dto.ArchiveGDTO;
import com.izibiz.training.entity.dto.InvoiceDTO;

@Repository
public class ArchiveDaoImpl extends GenericDaoHibernateImpl<Archive> implements ArchiveDao {

	private static final Logger logger = Logger.getLogger(ArchiveDaoImpl.class);
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
		 * Criteria crt =
		 * getCurrentSession().createCriteria(Archive.class).add(Restrictions.eq(
		 * "direction", direction)); return (List<Archive>) crt.list();
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

	@Override
	public List<ArchiveGDTO> getArchives(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filtersEqual, Map<String, Object> filtersContain) {

		// logger.debug("start InvoiceDaoImpl.getInvoiceDtos with params ");

		String sql = "SELECT    "+
									"  a.id AS id,   "+                      
									"  a.uuid AS uuid,  "+
				                    "  a.archive_date AS  archiveDate, "+
				                    "  a.archive_id AS  archiveId,  "+
				                    "  a.status AS status,            "+
				                    "  PC.IDENTIFIER AS receiverIdentifier,  "+
				                    "  PC.NAME AS receiver,  "+
				                    "  PS.IDENTIFIER AS senderIdentifier,  "+
				                    "  PS.NAME AS sender,  "+
				                    "  a.amount AS amount  "+                    
									" FROM ARCHIVE a, PARTY PC,  "+
				                    "PARTY PS"+
									" WHERE  a.CUSTOMER_PARTY_ID = PC.id  AND  a.SUPPLIER_PARTY_ID = PS.id ";

		StringBuilder sqlBuilder = new StringBuilder(sql);
		
		filterEqualSql(sqlBuilder, filtersEqual);
		filterContainSql(sqlBuilder,filtersContain);
		sortSql(sqlBuilder, sortField, sortOrder);
		
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sqlBuilder.toString());
		sqlQuery.setResultTransformer(Transformers.aliasToBean(ArchiveGDTO.class));
		sqlQuery.addScalar("id", LongType.INSTANCE);
		sqlQuery.addScalar("uuid");
		sqlQuery.addScalar("archiveDate", DateType.INSTANCE);
		sqlQuery.addScalar("archiveId");
		sqlQuery.addScalar("status");
		sqlQuery.addScalar("receiverIdentifier");  
		sqlQuery.addScalar("receiver"); 
		sqlQuery.addScalar("senderIdentifier");  
		sqlQuery.addScalar("sender");
		sqlQuery.addScalar("amount", BigDecimalType.INSTANCE);

		sqlQuery.setFirstResult(first);
		sqlQuery.setMaxResults(pageSize);

		List<ArchiveGDTO> archives = sqlQuery.list();
		logger.debug("end InvoiceDaoImpl.getInvoiceDtos with params ");

		return archives;
	}
	
	
	private void sortSql(StringBuilder builder, String sortField, SortOrder sortOrder) {

		// SORT
		if (!StringUtils.isEmpty(sortField)) {
			builder.append(" ORDER BY ");

			switch (sortField) {
	
			case "archiveId":
				builder.append("a.archive_id ");
				break;
			case "archiveDate":
				builder.append("a.archive_date ");
				break;
			case "amount":
				builder.append("a.amount ");
				break;		
			case "sender":
				builder.append("PS.name ");
				break;	
			case "receiver":
				builder.append("PC.name ");
				break;
			default:
				break;
			}
			
			if (SortOrder.ASCENDING.toString().equals(sortOrder.toString())) {
				builder.append("ASC");
				builder.append(",a.id ASC");
			} else {
				builder.append("DESC");
				builder.append(",a.id DESC");
			}
		} else {
			builder.append("ORDER BY a.archive_date DESC");
		}
		
	}

	private void filterEqualSql(StringBuilder sqlBuilder, Map<String, Object> filters) {

		for (Entry<String, Object> filter : filters.entrySet()) {
			String key = filter.getKey();
			Object value = filter.getValue();
			switch (key) {
			case "accountId":
				sqlBuilder.append(" and account_Id=").append(value);
				break;
			case "direction":
				sqlBuilder.append(" and direction='").append(value).append("'");
				break;
			default:
				break;
			}
		}
	}
	

	private void filterContainSql(StringBuilder sqlBuilder, Map<String, Object> filters) {

		for (Entry<String, Object> filter : filters.entrySet()) {
			String key = filter.getKey();
			Object value = filter.getValue();

			switch (key) {
			case "archiveId":
				if (value != null) {
					sqlBuilder.append(" AND ARCHIVE_ID LIKE '%").append(value).append("%'");
				}
				break;
			case "receiver":
				if (value != null) {
					sqlBuilder.append(" AND PC.NAME LIKE '%").append(value).append("%'");
				}
				break;
			case "sender":
				if (value != null) {
					sqlBuilder.append(" AND PS.NAME LIKE '%").append(value).append("%'");
				}
				break;
			case "amount":
				if (value != null) {
					sqlBuilder.append(" AND AMOUNT LIKE '%").append(value).append("%'");
				}
				break;
			case "archiveDate":
				if (value != null) {
					sqlBuilder.append(" AND ARCHIVE_DATE LIKE '%").append(value).append("%'");
				}
				break;
			default:
				break;
			}
		}
	}
	

	@Override
	public long getArchivesCount(Map<String, Object> filterEqual,Map<String, Object> filterContain) {

		long resultCount = 0;
		String sql = "SELECT count(*) " + " FROM GAMZE_SAHIN.ARCHIVE a, GAMZE_SAHIN.party PC , GAMZE_SAHIN.party PS "
				+ " WHERE  a.CUSTOMER_PARTY_ID = PC.id AND a.SUPPLIER_PARTY_ID = PS.id  ";

		StringBuilder sqlBuilder = new StringBuilder(sql);
		filterEqualSql(sqlBuilder, filterEqual);
		filterContainSql(sqlBuilder, filterContain);
		
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sqlBuilder.toString());

		Number count = (Number) sqlQuery.uniqueResult();
		if (count != null)
			resultCount = count.longValue();

		return resultCount;
	}

	@Override
	public ArchiveGDTO findArchiveeById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
