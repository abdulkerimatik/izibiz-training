package com.izibiz.training.dao.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.izibiz.training.dao.util.Filter;
import com.izibiz.training.dao.util.FilterQuery;
import com.izibiz.training.entity.common.Deactivetable;

@Repository
public abstract class GenericDaoHibernateImpl<T extends Serializable> implements Serializable, GenericDao<T>, LogService {

	private static final long serialVersionUID = 1L;

	private transient final Logger logger;

	private Class<T> persistentClass;
	private SessionFactory sessionFactory;

	public GenericDaoHibernateImpl(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
		this.logger = Logger.getLogger(persistentClass);
	}

	@Override
	public long countAll() {
		putDebugLog("countAll()");
		return getRowCount(getAllRecordsCriteria());
	}

	@Override
	public long countMatching(Map<String, Object> params) {
		putDebugLog("countMatching()");
		return getRowCount(addRestrictionsToCriteria(getAllRecordsCriteria(), params));
	}

	@Override
	public long countMatching(String field, Object value) {
		putDebugLog("countMatching()");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(field, value);
		return countMatching(params);
	}

	@Override
	public long countActive() {
		putDebugLog("countActive");
		return getRowCount(getActiveRecordsCriteria());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T get(Long id) {
		putDebugLog("get()", "[id:" + id + "]");
		return (T) getCurrentSession().get(persistentClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		putDebugLog("getAll()");
		return getAllRecordsCriteria().list();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<T> getFilterCdateAll() {
		putDebugLog("getAll()");
		return getAllRecordsCriteria().addOrder(Order.desc("createDate")).list();
	}
	
	@Override
	public List<T> getMatching(String field, Object value) {
		putDebugLog("getMatching()", "[Where:", field, "=", value != null ? value.toString() : "", "]");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(field, value);
		return getMatching(params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getMatching(Map<String, Object> params) {
		putDebugLog("getMatching()");
		return addRestrictionsToCriteria(getAllRecordsCriteria(), params).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getMatchingWithFilter(List<FilterQuery> filter) {
		putDebugLog("getMatchingFilter()");
		return addRestrictionsToCriteria(getAllRecordsCriteria(), filter).list();
	}

	@Override
	public T getUniqueMatching(String field, Object value) {
		putDebugLog("getMatchingUnique()", "[Where:", field, "=", value != null ? value.toString() : "", "]");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(field, value);
		return getUniqueMatching(params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getUniqueMatching(Map<String, Object> params) {
		putDebugLog("getMatchingUnique()");
		T value = (T) addRestrictionsToCriteria(getAllRecordsCriteria(), params).uniqueResult();
		return value;
	}

	@Override
	public List<T> getNotMatching(String field, Object value) {
		putDebugLog("getNotMatching()", "[Where:", field, "!=", value != null ? value.toString() : "", "]");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(field, value);
		return getNotMatching(params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getNotMatching(Map<String, Object> params) {
		putDebugLog("getNotMatching()");
		return addNotRestrictionsToCriteria(getAllRecordsCriteria(), params).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getActive() {
		putDebugLog("getActive()");
		return getActiveRecordsCriteria().list();
	}

	@Override
	public List<T> getMatchingActive(String field, Object value) {
		putDebugLog("getMacthingActive()", "[Where: ", field, "=", value != null ? value.toString() : "", "]");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(field, value);
		return getMatchingActive(params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getMatchingActive(Map<String, Object> params) {
		putDebugLog("getActiveMatching()");
		return addRestrictionsToCriteria(getActiveRecordsCriteria(), params).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getUniqueMatchingActive(Map<String, Object> params) {
		putDebugLog("getActiveMatchingUnique()");
		return (T) addRestrictionsToCriteria(getActiveRecordsCriteria(), params).uniqueResult();
	}

	@Override
	public T getUniqueMatchingActive(String field, Object value) {
		putDebugLog("getUniqueMatchingActive()", "[Where: ", field, "=", value != null ? value.toString() : "", "]");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(field, value);
		return getUniqueMatchingActive(params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getLimitedNumber(int maxNumber) {
		putDebugLog("getLimitedNumber()", "[Where: rownum <=", String.valueOf(maxNumber), "]");
		return getAllRecordsCriteria().setMaxResults(maxNumber).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getLimitedNumberActive(int maxNumber) {
		putDebugLog("getLimitedNumberActive()", "[Where: rownum <=", String.valueOf(maxNumber), "]");
		return getActiveRecordsCriteria().setMaxResults(maxNumber).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllStartsWith(String field, String value) {
		putDebugLog("getAllStartsWith()", "[Where", field, " like ", value != null ? value.toString() : "", "%", "]");
		Criteria criteria = getAllRecordsCriteria();
		criteria.add(Restrictions.like(field, value, MatchMode.START));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getMatchingContains(Map<String, Object> params, String field, String valueContained) {
		putDebugLog("getMatchingContains()", "[Where: ", field, " like %", valueContained != null ? valueContained.toString() : "", "%", "]");
		Criteria criteria = addRestrictionsToCriteria(getAllRecordsCriteria(), params);
		criteria.add(Restrictions.like(field, valueContained, MatchMode.ANYWHERE));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getLimitedMatchingContains(Map<String, Object> params, String field, String valueContained, int number) {
		putDebugLog("getLimitedMatchingContains()", "[Where: ", field, " like %", valueContained != null ? valueContained.toString() : "", "%", "]");
		Criteria criteria = addRestrictionsToCriteria(getAllRecordsCriteria(), params);
		criteria = criteria.add(Restrictions.like(field, valueContained, MatchMode.ANYWHERE));
		return criteria.setMaxResults(number).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllEndsWith(String field, String value) {
		putDebugLog("getAllEndsWith()", "[Where", field, " like ", value != null ? value.toString() : "", "%", "]");
		Criteria criteria = getAllRecordsCriteria();
		criteria.add(Restrictions.like(field, value, MatchMode.END));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getLimitedNumberMatching(Map<String, Object> params, int maxNumber) {
		putDebugLog("getLimitedNumberMatching()", "[Where: rownum <=", String.valueOf(maxNumber), "]");
		return addRestrictionsToCriteria(getAllRecordsCriteria(), params).setMaxResults(maxNumber).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getLimitedNumberMatchingActive(Map<String, Object> params, int maxNumber) {
		putDebugLog("getLimitedNumberMatchingActive()", "[Where: rownum <=", String.valueOf(maxNumber), "]");
		return addRestrictionsToCriteria(getActiveRecordsCriteria(), params).setMaxResults(maxNumber).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getOrderedAsc(String propertyName) {
		return getAllRecordsCriteria().addOrder(Order.asc(propertyName)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getOrderedDesc(String propertyName) {
		return getAllRecordsCriteria().addOrder(Order.desc(propertyName)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getOrderedActiveAsc(String propertyName) {
		return getActiveRecordsCriteria().addOrder(Order.asc(propertyName)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getOrderedActiveDesc(String propertyName) {
		return getActiveRecordsCriteria().addOrder(Order.desc(propertyName)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getOrderedMatchingAsc(Map<String, Object> params, String orderPropertyName) {
		return addRestrictionsToCriteria(getAllRecordsCriteria(), params).addOrder(Order.asc(orderPropertyName)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getOrderedMatchingDesc(Map<String, Object> params, String orderPropertyName) {
		return addRestrictionsToCriteria(getAllRecordsCriteria(), params).addOrder(Order.desc(orderPropertyName)).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getOrderedLimitedNumberMatchingAsc(Map<String, Object> params, String orderPropertyName, int maxNumber) {
		putDebugLog("getOrderedLimitedNumberMatchingAsc()");
		return addRestrictionsToCriteria(getAllRecordsCriteria(), params).addOrder(Order.asc(orderPropertyName)).setMaxResults(maxNumber).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getOrderedLimitedNumberMatchingDesc(Map<String, Object> params, String orderPropertyName, int maxNumber) {
		putDebugLog("getOrderedLimitedNumberMatchingDesc()");
		return addRestrictionsToCriteria(getAllRecordsCriteria(), params).addOrder(Order.desc(orderPropertyName)).setMaxResults(maxNumber).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getOrderedActiveMatchingAsc(Map<String, Object> params, String orderPropertyName) {
		return addRestrictionsToCriteria(getActiveRecordsCriteria(), params).addOrder(Order.asc(orderPropertyName)).list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getOrderedActiveMatchingDesc(Map<String, Object> params, String orderPropertyName) {
		return addRestrictionsToCriteria(getActiveRecordsCriteria(), params).addOrder(Order.desc(orderPropertyName)).list();
	}

	@Override
	public Serializable save(T t) {
		putDebugLog("create()", t == null ? "" : t);
		Serializable serializable = getCurrentSession().save(t);
		return serializable;
	}

	@Override
	public void update(T t) {
		putDebugLog("update()", t == null ? "" : t);
		getCurrentSession().update(t);
	}

	@Override
	public void saveOrUpdate(T t) {
		putDebugLog("saveOrUpdate()", t == null ? "" : t);
		getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void saveOrUpdate(List<T> list) {
		putDebugLog("saveOrUpdate()", list == null ? "" : list);
		getCurrentSession().saveOrUpdate(list);
	}

	@Override
	public void appplyChanges() {
		putDebugLog("flush()");
		getCurrentSession().flush();
	}

	@Override
	public void refresh(T t) {
		putDebugLog("refresh()", t);
		getCurrentSession().refresh(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T merge(T t) {
		putDebugLog("merge()", t);
		return (T) getCurrentSession().merge(t);
	}

	@Override
	public void delete(T t) {
		putDebugLog("delete()", t == null ? "" : t);
		getCurrentSession().delete(t);
	}

	@Override
	public void deactivate(T t) {
		putDebugLog("deactivate()", t == null ? "" : t);
		// t.setActive(false);
		getCurrentSession().update(t);
	}

	@Override
	public void activate(T t) {
		putDebugLog("activate()", t == null ? "" : t);
		// t.setActive(true);
		getCurrentSession().update(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(Long id) {
		putDebugLog("findById()", "[id:" + id + "]");
		return (T) getCurrentSession().load(getPersistentClass(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByExample(T t) {
		return (List<T>) getAllRecordsCriteria().add(Example.create(t)).list();
	}

	/**
	 * 
	 * Converts given criteria's query into a count query
	 * 
	 * @param criteria
	 * @return number of records
	 */
	protected long getRowCount(Criteria criteria) {
		long rowCount = (Long) criteria.setProjection(Projections.rowCount()).list().get(0);
		putDebugLog("[getRowCount()]", "[Number of rows:", String.valueOf(rowCount), "]");
		return rowCount;
	}

	protected Criteria getActiveRecordsCriteria() {
		if (Deactivetable.class.isAssignableFrom(persistentClass)) {
			return getAllRecordsCriteria().add(Restrictions.eq("active", true));
		}
		return getAllRecordsCriteria();
	}

	protected Criteria getAllRecordsCriteria() {
		Criteria criteria = getCurrentSession().createCriteria(getPersistentClass());
		return criteria;
	}

	/**
	 * Adds where clause filter's to given criteria
	 * 
	 * @param criteria
	 * @param params
	 * @return
	 */
	protected Criteria addRestrictionsToCriteria(Criteria criteria, Map<String, Object> params) {

		if (params != null && params.isEmpty() == false) {
			for (Entry<String, Object> paramSet : params.entrySet()) {
				putDebugLog("addRestrictionToCriteria()", "[AND:", paramSet.getKey(), "=", paramSet.getValue() != null ? paramSet.getValue().toString() : "", "]");
				if (paramSet.getKey().contains(".")) {
					String parameters[] = paramSet.getKey().split("\\.");
					criteria.createCriteria(parameters[0]).add(Restrictions.eq(parameters[1], paramSet.getValue()));
				} else {
					criteria.add(Restrictions.eq(paramSet.getKey(), paramSet.getValue()));
				}
			}
		} else {
			putDebugLog("addRestrictionToCriteria()", "[No filter params are given]");
		}
		return criteria;
	}

	@SuppressWarnings("unchecked")
	protected Criteria addRestrictionsToCriteria(Criteria criteria, List<FilterQuery> filter) {

		if (filter != null && filter.isEmpty() == false) {
			for (FilterQuery query : filter) {

				String property = query.getField();
				Object value = query.getValue();

				switch (query.getOperand()) {
				case Filter.OP_EQUAL:
					criteria.add(Restrictions.eq(property, value));
					break;
				case Filter.OP_NOT_EQUAL:
					criteria.add(Restrictions.ne(property, value));
					break;
				case Filter.OP_GREATER_THAN:
					criteria.add(Restrictions.gt(property, value));
					break;
				case Filter.OP_LESS_THAN:
					criteria.add(Restrictions.lt(property, value));
					break;
				case Filter.OP_GREATER_OR_EQUAL:
					criteria.add(Restrictions.ge(property, value));
					break;
				case Filter.OP_LESS_OR_EQUAL:
					criteria.add(Restrictions.le(property, value));
					break;
				case Filter.OP_IN:
					criteria.add(Restrictions.in(property, (Collection<Object>) value));
					break;
				case Filter.OP_NOT_IN:
					criteria.add(Restrictions.not(Restrictions.in(property, (Collection<Object>) value)));
					break;
				case Filter.OP_IS_NULL:
					criteria.add(Restrictions.isNull(property));
					break;
				case Filter.OP_IS_NOT_NULL:
					criteria.add(Restrictions.isNotNull(property));
					break;
				case Filter.ORDER_ASC:
					criteria.addOrder(Order.asc(property));
					break;
				case Filter.ORDER_DESC:
					criteria.addOrder(Order.desc(property));
					break;
				case Filter.DISTINCT:
					criteria.setProjection(Projections.distinct(Projections.property(property)));
					break;
				case Filter.DISTINCT_ROOT_ENTITY:
					criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
					break;
				default:
					break;
				}
			}
		} else {
			putDebugLog("addRestrictionToCriteria()", "[No filter params are given]");
		}
		return criteria;
	}

	/**
	 * Adds where clause filter's to given criteria, all restrictions are unequalities as in where a != b
	 * 
	 * @param criteria
	 * @param params
	 * @return
	 */

	private Criteria addNotRestrictionsToCriteria(Criteria criteria, Map<String, Object> params) {
		if (params != null && params.isEmpty() == false) {
			for (Entry<String, Object> paramSet : params.entrySet()) {
				putDebugLog("addRestrictionToCriteria()", "[AND:", paramSet.getKey(), "=", paramSet.getValue() != null ? paramSet.getValue().toString() : "", "]");
				criteria.add(Restrictions.ne(paramSet.getKey(), paramSet.getValue()));
			}
		} else {
			putDebugLog("addNotRestrictionToCriteria() ", "[No filter params given]");
		}
		return criteria;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	protected void setPersistentClass(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	protected Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	private String getLogTemplate(String operation, Object relatedObject, String... messages) {
		StringBuilder message = new StringBuilder();
		
		for (int i = 0; i < messages.length; i++) {
			message.append( messages[i]);
		}
		return "[Data Access]["
				+ operation
				+ "]["
				+ persistentClass.getSimpleName()
				+ "]"
				+ message
				+ (relatedObject == null ? "" : ("[Releated Object:" + relatedObject + "]" + "[Session:" + getCurrentSession().hashCode() + "][Transaction:"
						+ getCurrentSession().getTransaction().hashCode() + "]"));
	}

	@Override
	public void putErrorLog(String operation, Throwable exception, String... messages) {
		putErrorLog(operation, exception, null, messages);
	}

	@Override
	public void putErrorLog(String operation, Object relatedObject, Throwable exception, String... messages) {
		logger.error( getLogTemplate(operation, relatedObject, messages), exception);
	}

	@Override
	public void putDebugLog(String operation, String... messages) {
		putDebugLog(operation, null, messages);
	}

	@Override
	public void putDebugLog(String operation, Object relatedObject, String... messages) {
		if (logger.isDebugEnabled()) {
			logger.debug(  getLogTemplate(operation, relatedObject, messages));
		}
	}

	@Override
	public void putWarnLog(String operation, Object relatedObject, String message) {
		logger.warn(  getLogTemplate(operation, relatedObject, message));
	}

	@Override
	public void putWarnLog(String operation, Object relatedObject, String message, Throwable exception) {
		logger.warn(  getLogTemplate(operation, relatedObject, message), exception);
	}

	@Override
	public void putWarnLog(String operation, String message) {
		logger.warn( getLogTemplate(operation, message));
	}

	@Override
	public void putWarnLog(String operation, String message, Throwable exception) {
		logger.warn(  getLogTemplate(operation, message), exception);
	}

	@Override
	public void putInfoLog(String operation, Object relatedObject) {
		if (logger.isDebugEnabled()) {
			logger.debug(  getLogTemplate(operation, relatedObject));
		}
	}

	@Override
	public void putInfoLog(String operation, Object relatedObject, String... messages) {
		if (logger.isDebugEnabled()) {
			logger.debug( getLogTemplate(operation, relatedObject, messages));
		}
	}

	@Override
	public void putInfoLog(String operation, Object relatedObject, Throwable exception, String... messages) {
		if (logger.isDebugEnabled()) {
			logger.debug( exception+ getLogTemplate(operation, relatedObject, messages));
		}
	}

	@Override
	public void putInfoLog(String operation, String... messages) {
		if (logger.isDebugEnabled()) {
			logger.debug( getLogTemplate(operation, messages));
		}
	}

	@Override
	public void flushAndClear() {
		getCurrentSession().flush();
		getCurrentSession().clear();
	}
}
