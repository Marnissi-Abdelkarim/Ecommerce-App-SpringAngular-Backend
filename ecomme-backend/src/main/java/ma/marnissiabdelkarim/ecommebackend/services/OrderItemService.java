package ma.marnissiabdelkarim.ecommebackend.services;

import java.util.List;

import ma.marnissiabdelkarim.ecommebackend.entities.OrderEntity;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.OrderItemDto;

public interface OrderItemService {
	OrderItemDto createOrderItem(OrderItemDto orderItemDto);

	OrderItemDto getOrderItemByOrderItemId(String orderItemId) throws Exception;

	List<OrderItemDto> getOrderItems(int page, int limit);

	void deleteOrderItem(String id);

	OrderItemDto updateOrderItem(String id, OrderItemDto orderItemDto);

	List<OrderItemDto> saveItems(List<OrderItemDto> orderItems, OrderEntity newOrderEntity);
}
