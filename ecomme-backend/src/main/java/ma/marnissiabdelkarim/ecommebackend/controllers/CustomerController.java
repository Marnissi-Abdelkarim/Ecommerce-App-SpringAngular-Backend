package ma.marnissiabdelkarim.ecommebackend.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.marnissiabdelkarim.ecommebackend.requests.CustomerRequest;
import ma.marnissiabdelkarim.ecommebackend.requests.ProductRequest;
import ma.marnissiabdelkarim.ecommebackend.responses.CustomerResponse;
import ma.marnissiabdelkarim.ecommebackend.responses.ProductResponseWithCategory;
import ma.marnissiabdelkarim.ecommebackend.services.CustomerService;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.CustomerDto;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.ProductDto;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	// CREATE CUSTOMER
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })

	public ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest customerRequest,
			Principal principal) throws Exception {

		System.out.println(customerRequest);

		ModelMapper modelMapper = new ModelMapper();
		CustomerDto customerDto = modelMapper.map(customerRequest, CustomerDto.class);

		CustomerDto createCustomer = customerService.createCustomer(customerDto, principal);

		CustomerResponse customerResponse = modelMapper.map(createCustomer, CustomerResponse.class);

		return new ResponseEntity<CustomerResponse>(customerResponse, HttpStatus.CREATED);

	}

	// GET CUSTOMER BY CUSTOMERID
	@GetMapping(path = "/{customerId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CustomerResponse> getCustomerByCustomerId(@PathVariable String customerId)
			throws Exception {
		CustomerDto customerDto = customerService.getCustomerByCustomerId(customerId);
		ModelMapper modelMapper = new ModelMapper();
		CustomerResponse customerResponse = modelMapper.map(customerDto, CustomerResponse.class);
		return new ResponseEntity<CustomerResponse>(customerResponse, HttpStatus.OK);
	}
	
	
	//GET CUSTOMERS BY PAGES
		@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public List<CustomerResponse> getAllCustomers(@RequestParam(value = "page", defaultValue = "1") int page,
				@RequestParam(value = "limit", defaultValue = "15") int limit) {
			

			List<CustomerResponse> customersResponses = new ArrayList<>();

			List<CustomerDto> customers = customerService.getCustomers(page, limit);
			for (CustomerDto customerDto : customers) {
				ModelMapper modelMapper = new ModelMapper();
				CustomerResponse customerResponse = modelMapper.map(customerDto, CustomerResponse.class);
				customersResponses.add(customerResponse);
			}

			return customersResponses;
		}
		
		
		//DELETE CUSTOMER
		@DeleteMapping(path = "/{customerId}")
		public ResponseEntity<Object> deleteCustomer(@PathVariable String customerId) {
			customerService.deleteCustomer(customerId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		
		//UPDATE CUSTOMER		
		@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
				MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
						MediaType.APPLICATION_XML_VALUE })
		public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable String id, @RequestBody @Valid CustomerRequest customerRequest)  {
						
			ModelMapper modelMapper = new ModelMapper();
			CustomerDto customerDto = modelMapper.map(customerRequest, CustomerDto.class);

			CustomerDto updatedCustomer = customerService.updateCustomer(id, customerDto);

			CustomerResponse customerResponse = modelMapper.map(updatedCustomer,CustomerResponse.class);

			return new ResponseEntity<CustomerResponse>(customerResponse, HttpStatus.ACCEPTED);
		}

}
