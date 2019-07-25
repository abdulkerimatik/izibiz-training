package com.izibiz.training.dao.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.izibiz.training.dao.util.FilterQuery;

public interface GenericDao<T extends Serializable> {

	/**
	 * Method that returns the number of all records from a table
	 * 
	 * @param params
	 *            sql parameters
	 * @return the number of records in table
	 */
	long countAll();

	/**
	 * Method that returns the number of records from a table which meets given conditions
	 * 
	 * @param params
	 *            sql parameters
	 * @return the number of records meeting the criteria
	 */
	long countMatching(Map<String, Object> params);

	/**
	 * Method that returns the number of records from a table which meets a single condition
	 * 
	 * @param params
	 *            sql parameters
	 * @return the number of records meeting the criteria
	 */
	long countMatching(String field, Object value);

	/**
	 * Method that returns the number of records from a table which are active
	 * 
	 * @param params
	 *            sql parameters
	 * @return the number of records meeting the criteria
	 */
	long countActive();
	
	/**
	 * Method that returns only the mathing record from a table
	 * 
	 * @param params
	 *            sql parameters
	 * @return the only object of record in table
	 */
	T get(Long id);

	/**
	 * Method that returns all records from a table
	 * 
	 * @param params
	 *            sql parameters
	 * @return the number of records in table
	 */
	List<T> getAll();

	/**
	 * Method that returns all records from a table which meet a single condition
	 * 
	 * @param params
	 *            sql parameters, if empty or null params given full tables is returned
	 * @return the number of records in table
	 */
	List<T> getMatching(String field, Object value);

	/**
	 * Method that returns all records from a table which meet some conditions(with where clause params).
	 * 
	 * @param params
	 *            sql parameters, if empty or null params given full tables is returned
	 * @return the number of records in table
	 */
	List<T> getMatching(Map<String, Object> params);

	/**
	 * Method that returns all records from a table which meet some conditions(with where clause params).
	 * 
	 * @param filter
	 *            sql parameters, if empty or null params given full tables is returned
	 * @return list of the records in table
	 */
	List<T> getMatchingWithFilter(List<FilterQuery> filter);

	/**
	 * Method that returns a single record from a table which meet a single condition given
	 * 
	 * @param params
	 *            sql parameters, if empty or null params given full tables is returned
	 * @return the number of records in table
	 */
	T getUniqueMatching(String field, Object value);

	/**
	 * Method that returns a single record from a table which meet some conditions(with where clause params).
	 * 
	 * @param params
	 *            sql parameters, if empty or null params given full tables is returned
	 * @return the number of records in table
	 */
	T getUniqueMatching(Map<String, Object> params);

	List<T> getNotMatching(String field, Object value);

	List<T> getNotMatching(Map<String, Object> params);

	/**
	 * Method that returns all records from a table which are active
	 * 
	 * @param params
	 *            sql parameters
	 * @return the number of records in table
	 */
	List<T> getActive();

	/**
	 * Method that returns active records from a table which meet some conditions(with where clause params).
	 * 
	 * @param params
	 *            sql parameters, if empty or null params given full tables is returned
	 * @return the number of records in table
	 */

	List<T> getMatchingActive(Map<String, Object> params);

	/**
	 * Method that returns active records from a table which meet a single condition
	 * 
	 * @param params
	 *            sql parameters, if empty or null params given full tables is returned
	 * @return the number of records in table
	 */
	List<T> getMatchingActive(String field, Object value);

	List<T> getMatchingContains(Map<String, Object> params, String field, String valueContained);

	List<T> getLimitedMatchingContains(Map<String, Object> params, String field, String valueContained, int number);

	/**
	 * 
	 * Method that returns records which have values starting with given string value
	 * 
	 * @param field
	 * @param value
	 * @return
	 */
	List<T> getAllStartsWith(String field, String value);

	/**
	 * 
	 * Method that returns records which have values ending with given string value
	 * 
	 * @param field
	 * @param value
	 * @return
	 */
	List<T> getAllEndsWith(String field, String value);

	/**
	 * Method that returns a single active record from a table which meet a single condition given
	 * 
	 * @param params
	 *            sql parameters, if empty or null params given full tables is returned
	 * @return the number of records in table
	 */
	T getUniqueMatchingActive(String field, Object value);

	/**
	 * Method that returns a single active record from a table which meet some conditions(with where clause params).
	 * 
	 * @param params
	 *            sql parameters, if empty or null params given full tables is returned
	 * @return the number of records in table
	 */
	T getUniqueMatchingActive(Map<String, Object> params);

	/**
	 * 
	 * Method that returns at most given number of records
	 * 
	 * @return
	 */
	List<T> getLimitedNumber(int maxNumber);

	/**
	 * 
	 * Method that returns at most given number of records
	 * 
	 * @return
	 */
	List<T> getLimitedNumberActive(int maxNumber);

	/**
	 * Method that returns at most given number of records from a table which meet some conditions(with where clause params).
	 * 
	 * @param params
	 *            sql parameters, if empty or null params given full tables is returned
	 * @return the number of records in table
	 */
	List<T> getLimitedNumberMatching(Map<String, Object> params, int maxNumber);

	/**
	 * Method that returns at most given number of active records from a table which meet some conditions(with where clause params).
	 * 
	 * @param params
	 *            sql parameters, if empty or null params given full tables is returned
	 * @return the number of records in table
	 */
	List<T> getLimitedNumberMatchingActive(Map<String, Object> params, int maxNumber);

	/**
	 * Method that returns all records ordered asc by given property
	 * 
	 * @param params
	 *            sql parameters
	 * @return the number of records in table
	 */

	List<T> getOrderedAsc(String propertyName);

	/**
	 * Method that returns all records ordered desc by given property
	 * 
	 * @param params
	 *            sql parameters
	 * @return the number of records in table
	 */

	List<T> getOrderedDesc(String propertyName);

	/**
	 * Method that returns all active records ordered asc by given property
	 * 
	 * @param params
	 *            sql parameters
	 * @return the number of records in table
	 */

	List<T> getOrderedActiveAsc(String propertyName);

	/**
	 * Method that returns all active records ordered desc by given property
	 * 
	 * @param params
	 *            sql parameters
	 * @return the number of records in table
	 */

	List<T> getOrderedActiveDesc(String propertyName);

	/**
	 * Method that returns all records ordered asc by given property which meets given criteria
	 * 
	 * @param params
	 *            sql parameters
	 * @return the number of records in table
	 */

	List<T> getOrderedMatchingAsc(Map<String, Object> params, String orderPropertyName);

	/**
	 * Method that returns all records ordered desc by given property which meets given criteria
	 * 
	 * @param params
	 *            sql parameters
	 * @return the number of records in table
	 */

	List<T> getOrderedMatchingDesc(Map<String, Object> params, String orderPropertyName);
	
	/**
	 * Method that returns all records ordered asc by given property which meets given criteria
	 * 
	 * @param params
	 *            sql parameters
	 * @return the number of records in table
	 */

	List<T> getOrderedLimitedNumberMatchingAsc(Map<String, Object> params, String orderPropertyName, int maxNumber);
	
	/**
	 * Method that returns all records ordered asc by given property which meets given criteria
	 * 
	 * @param params
	 *            sql parameters
	 * @return the number of records in table
	 */

	List<T> getOrderedLimitedNumberMatchingDesc(Map<String, Object> params, String orderPropertyName, int maxNumber);

	/**
	 * Method that returns active records ordered asc by given property which meets given criteria
	 * 
	 * @param params
	 *            sql parameters
	 * @return the number of records in table
	 */

	List<T> getOrderedActiveMatchingAsc(Map<String, Object> params, String orderPropertyName);

	/**
	 * Method that returns active records ordered desc by given property which meets given criteria
	 * 
	 * @param params
	 *            sql parameters
	 * @return the number of records in table
	 */

	List<T> getOrderedActiveMatchingDesc(Map<String, Object> params, String orderPropertyName);

	/**
	 * Creates given persistent domain object in the table
	 * 
	 * 
	 * @param t
	 * @return
	 */
	Serializable save(T t);

	/**
	 * Deletes given persistent domain object in the table
	 * 
	 * 
	 * @param t
	 * @return
	 */

	void delete(T t);

	/**
	 * Deactivates given persistent deactivatable domain object in the table
	 * 
	 * 
	 * @param t
	 * @return
	 */

	void deactivate(T t);
	
	void activate(T t);

	/**
	 * 
	 * Finds entry with given id in the table
	 * 
	 * @param id
	 * @return
	 */
	T findById(Long id);

	/**
	 * 
	 * Finds entry with given id in the table
	 * 
	 * @param id
	 * @return
	 */
	List<T> findByExample(T t);

	/**
	 * Updates changes on given entry
	 * 
	 * @param t
	 * @return
	 */

	void update(T t);

	void saveOrUpdate(T t);

	void saveOrUpdate(List<T> list);

	void refresh(T t);

	T merge(T t);

	void appplyChanges();
	
	void flushAndClear();

	List<T> getFilterCdateAll();
	
}
