package com.izibiz.training.service.base;

import java.util.List;

import com.izibiz.training.entity.dto.CustomerClientDTO;

public interface CustomerClientService {

	List<CustomerClientDTO> getAll();

	CustomerClientDTO findByClientCode(String clientCode);

	CustomerClientDTO findByIdentifier(String identifier);

	List<CustomerClientDTO> findByIdentifierWithLike(String identifier);

	List<CustomerClientDTO> findByIdentifierWithLikeUsingLimit(String identifier, int limit);

	boolean updateCustomerClient(CustomerClientDTO clientDTO);

	boolean insertCustomerClient(CustomerClientDTO clientDTO);
}
