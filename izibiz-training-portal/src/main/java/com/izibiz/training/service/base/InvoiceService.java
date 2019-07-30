package com.izibiz.training.service.base;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import com.izibiz.training.entity.dto.InvoiceDTO;

public interface InvoiceService {

	List<InvoiceDTO> getInvoiceDtos(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters);
	
	long getInvoiceDtoCount(Map<String, Object> filters);

}
