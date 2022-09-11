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

import ma.marnissiabdelkarim.ecommebackend.requests.ProductRequest;
import ma.marnissiabdelkarim.ecommebackend.requests.UserRequest;
import ma.marnissiabdelkarim.ecommebackend.responses.ProductResponseWithCategory;
import ma.marnissiabdelkarim.ecommebackend.responses.UserResponse;
import ma.marnissiabdelkarim.ecommebackend.services.ProductService;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.ProductDto;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.UserDto;

@RestController
@RequestMapping("/products") 
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	//CREATE PRODUCT
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }) 
																																									
	public ResponseEntity<ProductResponseWithCategory> createProduct(@RequestBody @Valid ProductRequest productRequest) throws Exception {
		
		System.out.println(productRequest);
		if(productRequest.getDiscountedPrice()>productRequest.getCurrentPrice()) {
			throw new Exception("discounted priced must be smaller than current price");
		}
		
		ModelMapper modelMapper = new ModelMapper();
		ProductDto productDto = modelMapper.map(productRequest, ProductDto.class);
		
		
		ProductDto createProduct = productService.createProduct(productDto);
		
		ProductResponseWithCategory productResponse = modelMapper.map(createProduct, ProductResponseWithCategory.class);
		
		return new ResponseEntity<ProductResponseWithCategory>(productResponse, HttpStatus.CREATED);
		
	}
	
	
	//GET PRODUCT BY PRODUCTID
	@GetMapping(path = "/{productId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ProductResponseWithCategory> getProductByProductId(@PathVariable String productId) throws Exception 
	{		
			ProductDto productDto = productService.getProductByProductId(productId);
			ModelMapper modelMapper = new ModelMapper();
			ProductResponseWithCategory productResponse = modelMapper.map(productDto, ProductResponseWithCategory.class);
			return new ResponseEntity<ProductResponseWithCategory>(productResponse,HttpStatus.OK);
	}
	
	
	//GET PRODUCTS BY PAGES
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<ProductResponseWithCategory> getAllProducts(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "15") int limit,
			@RequestParam(value = "search", defaultValue = "") String search,Principal principal) {
		

		List<ProductResponseWithCategory> productsResponses = new ArrayList<>();

		List<ProductDto> products = productService.getProducts(page, limit, search);
		for (ProductDto productDto : products) {
			ModelMapper modelMapper = new ModelMapper();
			ProductResponseWithCategory productResponse = modelMapper.map(productDto, ProductResponseWithCategory.class);
			productsResponses.add(productResponse);
		}

		return productsResponses;
	}
	
	
	//DELETE PRODUCT
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable String id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	//UPDATE PRODUCT
	
	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ProductResponseWithCategory> updateProduct(@PathVariable String id, @RequestBody @Valid ProductRequest productRequest)  {
		
		if(productRequest.getDiscountedPrice()>productRequest.getCurrentPrice()) {
			throw new RuntimeException("discounted priced must be smaller than current price");
		}
		
		ModelMapper modelMapper = new ModelMapper();
		ProductDto productDto = modelMapper.map(productRequest, ProductDto.class);

		ProductDto updatedProduct = productService.updateProduct(id, productDto);

		ProductResponseWithCategory productResponse = modelMapper.map(updatedProduct,ProductResponseWithCategory.class);

		return new ResponseEntity<ProductResponseWithCategory>(productResponse, HttpStatus.ACCEPTED);
	}
	
}
