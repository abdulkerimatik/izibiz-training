package com.izibiz.training.service.base;

import java.util.ArrayList;
import java.util.List;

import com.izibiz.training.entity.dto.CustomerClientDTO;
import com.izibiz.training.entity.dto.DataRepo;
import com.izibiz.training.service.CustomerClientService;

public class CustomerClientServiceImpl implements CustomerClientService {

	@Override
	public CustomerClientDTO findByClientCode(String clientCode) {
		for (CustomerClientDTO dto : DataRepo.customerClientList) {
			if (dto.getClientCode().equals(clientCode))
				return dto;
		}
		return null;
	}

	@Override
	public boolean updateCustomerClient(CustomerClientDTO clientDTO) {
		for (CustomerClientDTO customer : getAll()) {
			if (customer.getClientCode().equals(clientDTO.getClientCode())) {
				DataRepo.customerClientList.remove(customer);
				DataRepo.customerClientList.add(clientDTO);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean insertCustomerClient(CustomerClientDTO clientDTO) {
		DataRepo.customerClientList.add(clientDTO);
		return true;
	}

	@Override
	public CustomerClientDTO findByIdentifier(String identifier) {
		for (CustomerClientDTO dto : DataRepo.customerClientList) {
			if (dto.getIdentifier().equals(identifier))
				return dto;
		}
		return null;
	}

	@Override
	public List<CustomerClientDTO> findByIdentifierWithLike(String identifier) {
		List<CustomerClientDTO> resultList = new ArrayList<CustomerClientDTO>();
		for (CustomerClientDTO dto : DataRepo.customerClientList) {
			if (dto.getIdentifier().contains(identifier))
				resultList.add(dto);
		}
		return resultList;
	}

	@Override
	public List<CustomerClientDTO> findByIdentifierWithLikeUsingLimit(String identifier, int limit) {
		List<CustomerClientDTO> resultList = new ArrayList<CustomerClientDTO>();
		for (CustomerClientDTO dto : DataRepo.customerClientList) {
			if (dto.getIdentifier().contains(identifier)) {
				resultList.add(dto);
				if (resultList.size() == limit) {
					break;
				}
			}
		}
		return resultList;
	}

	@Override
	public List<CustomerClientDTO> getAll() {
		List<CustomerClientDTO> list = new ArrayList<>();
		list.addAll(DataRepo.customerClientList);
		return list;
	}

}
