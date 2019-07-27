package com.izibiz.training.dao.base;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import com.izibiz.training.dao.common.GenericDao;
import com.izibiz.training.entity.dto.InvoiceDTO;

public  interface InvoiceDao extends GenericDao<InvoiceDTO>{
	
	public List<InvoiceDTO> findByInvoiceId(String invoiceID);

	List<InvoiceDTO> getInvoiceDtos(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters);

	long getInvoiceDtoCount(Map<String, Object> filters);

}
