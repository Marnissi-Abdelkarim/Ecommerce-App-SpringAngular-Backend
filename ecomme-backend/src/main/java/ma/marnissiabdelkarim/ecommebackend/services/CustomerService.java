package ma.marnissiabdelkarim.ecommebackend.services;

import java.security.Principal;
import java.util.List;

import ma.marnissiabdelkarim.ecommebackend.shared.dto.CustomerDto;

public interface CustomerService {
	
	CustomerDto createCustomer(CustomerDto customerDto,Principal p);

	CustomerDto getCustomerByCustomerId(String customerId) throws Exception;

	List<CustomerDto> getCustomers(int page, int limit);

	void deleteCustomer(String id);

	CustomerDto updateCustomer(String id, CustomerDto customerDto);


}
