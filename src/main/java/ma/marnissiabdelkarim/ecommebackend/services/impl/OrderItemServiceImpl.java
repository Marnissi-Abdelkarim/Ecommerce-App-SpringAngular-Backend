package ma.marnissiabdelkarim.ecommebackend.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.marnissiabdelkarim.ecommebackend.entities.OrderEntity;
import ma.marnissiabdelkarim.ecommebackend.entities.OrderItemEntity;
import ma.marnissiabdelkarim.ecommebackend.entities.ProductEntity;
import ma.marnissiabdelkarim.ecommebackend.repositories.OrderItemRepository;
import ma.marnissiabdelkarim.ecommebackend.repositories.ProductRepository;
import ma.marnissiabdelkarim.ecommebackend.services.OrderItemService;
import ma.marnissiabdelkarim.ecommebackend.shared.Utils;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.OrderItemDto;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	@Autowired
	Utils utils;
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;

	@Override
	public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItemDto getOrderItemByOrderItemId(String orderItemId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderItemDto> getOrderItems(int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrderItem(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public OrderItemDto updateOrderItem(String id, OrderItemDto orderItemDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderItemDto> saveItems(List<OrderItemDto> orderItems, OrderEntity newOrderEntity) {
		List<OrderItemDto> orderItemDtos =new ArrayList<OrderItemDto>();
		for (OrderItemDto orderItemDto : orderItems) {
			OrderItemEntity orderItemEntity = new OrderItemEntity();
			orderItemEntity.setOrderItemId(utils.generateStringId(30));
			ProductEntity productEntity = productRepository.findByProductId(orderItemDto.getProductId());
			orderItemEntity.setProduct(productEntity);
			orderItemEntity.setQuantity(orderItemDto.getQuantity());
			orderItemEntity.setOrder(newOrderEntity);
			
			OrderItemEntity itemEntity = orderItemRepository.save(orderItemEntity);
			ModelMapper mapper= new ModelMapper();
			OrderItemDto itemDto = mapper.map(itemEntity, OrderItemDto.class);
			orderItemDtos.add(itemDto);
		}
		
		return orderItemDtos;
		
	}

}
