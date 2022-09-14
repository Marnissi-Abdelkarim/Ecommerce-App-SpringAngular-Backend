package ma.marnissiabdelkarim.ecommebackend.services.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ma.marnissiabdelkarim.ecommebackend.EcommeBackendApplication;
import ma.marnissiabdelkarim.ecommebackend.entities.CategoryEntity;
import ma.marnissiabdelkarim.ecommebackend.entities.CustomerEntity;
import ma.marnissiabdelkarim.ecommebackend.entities.ProductEntity;
import ma.marnissiabdelkarim.ecommebackend.entities.UserEntity;
import ma.marnissiabdelkarim.ecommebackend.repositories.CustomerRepository;
import ma.marnissiabdelkarim.ecommebackend.services.CustomerService;
import ma.marnissiabdelkarim.ecommebackend.services.UserService;
import ma.marnissiabdelkarim.ecommebackend.shared.Constants;
import ma.marnissiabdelkarim.ecommebackend.shared.Utils;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.CustomerDto;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.ProductDto;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	UserService userService;
	
	@Autowired
	Utils util;
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public CustomerDto createCustomer(CustomerDto customerDto,Principal principal) {
		UserEntity currentUser = userService.findUserByEmail(principal.getName());
		ModelMapper modelMapper = new ModelMapper();
		CustomerEntity customerEntity=modelMapper.map(customerDto, CustomerEntity.class);
		customerEntity.setCustomerId(util.generateStringId(30));
		customerEntity.setUser(currentUser);
		
		CustomerEntity newCustomerEntity=customerRepository.save(customerEntity);
		CustomerDto customerDto2 = modelMapper.map(newCustomerEntity, CustomerDto.class);
		return customerDto2;
	}

	@Override
	public CustomerDto getCustomerByCustomerId(String customerId) throws Exception {
		CustomerEntity customerEntity = customerRepository.findByCustomerId(customerId);
		if (customerEntity == null) {
			throw new RuntimeException("customer with the specified id does not exist !");
		}
		
		if(!AccountServiceImpl.isSameToCurrentUser(customerEntity.getUser().getEmail()) && !AccountServiceImpl.isAdmin()) {
			throw new RuntimeException("not authorized");
		}
			
		ModelMapper modelMapper = new ModelMapper();
		CustomerDto customerDto = modelMapper.map(customerEntity, CustomerDto.class);

		return customerDto;
	}

	@Override
	public List<CustomerDto> getCustomers(int page, int limit) {
		if (page > 0)
			page -= 1;

		List<CustomerDto> customersDto = new ArrayList<CustomerDto>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<CustomerEntity> customerPage;
 
		if(AccountServiceImpl.isAdmin()) {
		customerPage = customerRepository.findAll(pageableRequest);}
		else {
		customerPage = customerRepository.findByUserEmail(AccountServiceImpl.currentUserEmail(), pageableRequest);
		}

		List<CustomerEntity> customerEntities = customerPage.getContent();
		for (CustomerEntity customerEntity : customerEntities) {

			ModelMapper modelMapper = new ModelMapper();
			CustomerDto customerDto = modelMapper.map(customerEntity, CustomerDto.class);

			customersDto.add(customerDto);
		}
		return customersDto;
	}

	@Override
	public void deleteCustomer(String id) {
		CustomerEntity customerEntity = customerRepository.findByCustomerId(id);
		if (customerEntity == null) {
			throw new RuntimeException("customer with the specified id does not exist !");
		}
		
		if(!AccountServiceImpl.isSameToCurrentUser(customerEntity.getUser().getEmail()) && !AccountServiceImpl.isAdmin()) {
			throw new RuntimeException("not authorized");
		}
		customerRepository.delete(customerEntity);
	}

	@Override
	public CustomerDto updateCustomer(String id, CustomerDto customerDto) {
		CustomerEntity customerEntity = customerRepository.findByCustomerId(id);
		if (customerEntity == null) {
			throw new RuntimeException("customer with the specified id does not exist !");
		}
		
		if(!AccountServiceImpl.isSameToCurrentUser(customerEntity.getUser().getEmail()) && !AccountServiceImpl.isAdmin()) {
			throw new RuntimeException("not authorized");
		}
		
		customerEntity.setFirstname(customerDto.getFirstname());
		customerEntity.setLastname(customerDto.getLastname());
		customerEntity.setPhone(customerDto.getPhone());
		customerEntity.setAddress(customerDto.getAddress());
		customerEntity.setAdditionalInformation(customerDto.getAdditionalInformation());
		customerEntity.setRegion(customerDto.getRegion());
		customerEntity.setTown(customerDto.getTown());
	
		CustomerEntity updatedCustomer= customerRepository.save(customerEntity);
		ModelMapper modelMapper = new ModelMapper();
		CustomerDto customerDto2 =modelMapper.map(updatedCustomer, CustomerDto.class);
		
		return customerDto2;
	}

}
