package ma.marnissiabdelkarim.ecommebackend.controllers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
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
import ma.marnissiabdelkarim.ecommebackend.requests.OrderItemRequest;
import ma.marnissiabdelkarim.ecommebackend.requests.OrderRequest;
import ma.marnissiabdelkarim.ecommebackend.requests.ProductRequest;
import ma.marnissiabdelkarim.ecommebackend.responses.CustomerResponse;
import ma.marnissiabdelkarim.ecommebackend.responses.OrderResponse;
import ma.marnissiabdelkarim.ecommebackend.responses.ProductResponseWithCategory;
import ma.marnissiabdelkarim.ecommebackend.services.OrderService;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.CustomerDto;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.OrderDto;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.OrderItemDto;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.ProductDto;

@RestController
@RequestMapping("/orders") 
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	//CREATE ORDER
		@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }) 
																																										
		public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderRequest orderRequest) throws Exception {
			ModelMapper listmapper= new ModelMapper();
			listmapper.addMappings(new PropertyMap<OrderItemRequest, OrderItemDto>() {

				@Override
				protected void configure() {
					skip(destination.getOrderItemId());
					skip(destination.getProduct());
					
				}
			});
			
			
			Type listType = new TypeToken<List<OrderItemDto>>(){}.getType(); 
			List<OrderItemDto> orderItemsDto = listmapper.map(orderRequest.getOrderItems(), listType);
			
			OrderDto orderDto=new OrderDto();
			orderDto.setCustomerId(orderRequest.getCustomerId());
			orderDto.setOrderItems(orderItemsDto);
			System.out.println(orderDto);
			
			
			OrderDto createOrder = orderService.createOrder(orderDto);
			
			ModelMapper mapper =new ModelMapper();
			OrderResponse orderResponse = mapper.map(createOrder, OrderResponse.class);
			
			
			return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.CREATED);
			
			
		}
		
		
		
		// GET ORDER BY ORDERID
		@GetMapping(path = "/{orderId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
		public ResponseEntity<OrderResponse> getOrderByOrderId(@PathVariable String orderId)
				throws Exception {
			OrderDto orderDto = orderService.getOrderByOrderId(orderId);
			ModelMapper mapper =new ModelMapper();
			OrderResponse orderResponse = mapper.map(orderDto, OrderResponse.class);
			return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
		}
		
		//GET ORDERS BY PAGES
				@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
				public List<OrderResponse> getAllOrders(@RequestParam(value = "page", defaultValue = "1") int page,
						@RequestParam(value = "limit", defaultValue = "15") int limit) {
					

					List<OrderResponse> orderResponses = new ArrayList<>();

					List<OrderDto> orderDtos = orderService.getOrders(page, limit);
					for (OrderDto orderDto : orderDtos) {
						ModelMapper modelMapper = new ModelMapper();
						OrderResponse orderResponse = modelMapper.map(orderDto, OrderResponse.class);
						orderResponses.add(orderResponse);
					}

					return orderResponses;
				}
				
				//CONFIRME ORDER PAYMENT		
				@PutMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
								MediaType.APPLICATION_XML_VALUE })
				public ResponseEntity<OrderResponse> confirmeOrderPayment(@PathVariable String id)  {
								
					

					OrderDto updatedOrder = orderService.updateOrder(id);
					ModelMapper mapper=new ModelMapper();
					OrderResponse orderResponse = mapper.map(updatedOrder,OrderResponse.class);

					return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.ACCEPTED);
				}
				
				//DELETE ORDER
				@DeleteMapping(path = "/{orderId}")
				public ResponseEntity<Object> deleteOrder(@PathVariable String orderId) {
					orderService.deleteOrder(orderId);
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				
		

}
