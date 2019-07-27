package com.izibiz.training.service.base;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.izibiz.training.dao.base.InvoiceDao;
import com.izibiz.training.entity.dto.InvoiceDTO;
import com.izibiz.training.service.InvoiceService;

@Transactional
public class InvoiceServiceImpl  implements InvoiceService{

  @Autowired
  private InvoiceDao  invoiceDao;
	
	@Override
	public List<InvoiceDTO> getInvoiceDtos(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		if (CollectionUtils.isEmpty(filters)) {
			throw new RuntimeException("filter must not empty");
		}
		return invoiceDao.getInvoiceDtos(first, pageSize, sortField, sortOrder, filters);
	}

	@Override
	public long getInvoiceDtoCount(Map<String, Object> filters) {
		return invoiceDao.getInvoiceDtoCount(filters);
	}

}
