package ma.marnissiabdelkarim.ecommebackend.services.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ma.marnissiabdelkarim.ecommebackend.entities.CategoryEntity;
import ma.marnissiabdelkarim.ecommebackend.entities.CustomerEntity;
import ma.marnissiabdelkarim.ecommebackend.entities.OrderEntity;
import ma.marnissiabdelkarim.ecommebackend.entities.OrderItemEntity;
import ma.marnissiabdelkarim.ecommebackend.entities.ProductEntity;
import ma.marnissiabdelkarim.ecommebackend.repositories.CustomerRepository;
import ma.marnissiabdelkarim.ecommebackend.repositories.OrderRepository;
import ma.marnissiabdelkarim.ecommebackend.repositories.ProductRepository;
import ma.marnissiabdelkarim.ecommebackend.requests.OrderItemRequest;
import ma.marnissiabdelkarim.ecommebackend.services.OrderItemService;
import ma.marnissiabdelkarim.ecommebackend.services.OrderService;
import ma.marnissiabdelkarim.ecommebackend.shared.Utils;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.CustomerDto;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.OrderDto;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.OrderItemDto;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.ProductDto;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemService orderItemService;
	
	@Autowired
	Utils utils;

	@Override
	public OrderDto createOrder(OrderDto orderDto) {
		double totalAmount=0;
		CustomerEntity customer = customerRepository.findByCustomerId(orderDto.getCustomerId());
		if (customer == null) {
			throw new RuntimeException("customer not found");
		}
		
		if(!AccountServiceImpl.isSameToCurrentUser(customer.getUser().getEmail()) && !AccountServiceImpl.isAdmin()) {
			throw new RuntimeException("not authorized");
		}
		
		
		for (OrderItemDto orderItemDto : orderDto.getOrderItems()) {
			
			ProductEntity productEntity = productRepository.findByProductId(orderItemDto.getProductId());
			if(productEntity==null) {
				throw new RuntimeException("product with this productId "+orderItemDto.getProductId() +" not found");
			}
			totalAmount+=productEntity.isPromotion()?  productEntity.getDiscountedPrice()*orderItemDto.getQuantity():productEntity.getCurrentPrice()*orderItemDto.getQuantity();			
		}
		
		OrderEntity orderEntity=new OrderEntity();
		orderEntity.setOrderId(utils.generateStringId(30));
		orderEntity.setDate(new Date());
		orderEntity.setCustomer(customer);
		orderEntity.setPayementConfirmed(orderDto.isPayementConfirmed());
		orderEntity.setTotalAmount(totalAmount);
		
		OrderEntity newOrderEntity=orderRepository.save(orderEntity);

		List<OrderItemDto> orderItemDtos= orderItemService.saveItems(orderDto.getOrderItems(),newOrderEntity);
		
		ModelMapper mapper=new ModelMapper();
		
		OrderDto newOrderDto = mapper.map(newOrderEntity, OrderDto.class);
		newOrderDto.setOrderItems(orderItemDtos);
		

		return newOrderDto;
	}

	@Override
	public OrderDto getOrderByOrderId(String orderId) throws Exception {
		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		if (orderEntity == null) {
			throw new RuntimeException("order with the specified id does not exist !");
		}
		
		if(!AccountServiceImpl.isSameToCurrentUser(orderEntity.getCustomer().getUser().getEmail()) && !AccountServiceImpl.isAdmin()) {
			throw new RuntimeException("not authorized");
		}
			
		ModelMapper modelMapper = new ModelMapper();
		OrderDto orderDto = modelMapper.map(orderEntity, OrderDto.class);

		return orderDto;
	}

	@Override
	public List<OrderDto> getOrders(int page, int limit) {
		if (page > 0)
			page -= 1;

		List<OrderDto> orderDtos = new ArrayList<OrderDto>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<OrderEntity> orderPage;
 
		if(AccountServiceImpl.isAdmin()) {
			orderPage = orderRepository.findAll(pageableRequest);}
		else {
			orderPage = orderRepository.findByCustomerUserEmail(AccountServiceImpl.currentUserEmail(), pageableRequest);
		}

		List<OrderEntity> orderEntities = orderPage.getContent();
		for (OrderEntity orderEntity : orderEntities) {

			ModelMapper modelMapper = new ModelMapper();
			OrderDto orderDto = modelMapper.map(orderEntity, OrderDto.class);

			orderDtos.add(orderDto);
		}
		return orderDtos;
	}

	@Override
	public void deleteOrder(String orderId) {
		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		if (orderEntity == null) {
			throw new RuntimeException("order with the specified id does not exist !");
		}
		
		if(!AccountServiceImpl.isSameToCurrentUser(orderEntity.getCustomer().getUser().getEmail()) && !AccountServiceImpl.isAdmin()) {
			throw new RuntimeException("not authorized");
		}
		orderRepository.delete(orderEntity);
		
		
	}

	@Override
	public OrderDto updateOrder(String orderId) {
		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		if (orderEntity == null) {
			throw new RuntimeException("order with the specified id does not exist !");
		}
		
		if(!AccountServiceImpl.isSameToCurrentUser(orderEntity.getCustomer().getUser().getEmail()) && !AccountServiceImpl.isAdmin()) {
			throw new RuntimeException("not authorized");
		}
		if (orderEntity.isPayementConfirmed() == true) {
			throw new RuntimeException("order payement is confirmed !");
		}
		
		orderEntity.setPayementConfirmed(true);
		OrderEntity orderEntity2=orderRepository.save(orderEntity);
		
		ModelMapper modelMapper = new ModelMapper();
		OrderDto orderDto = modelMapper.map(orderEntity2, OrderDto.class);
		
		
		
		return orderDto;
	}

	

}
