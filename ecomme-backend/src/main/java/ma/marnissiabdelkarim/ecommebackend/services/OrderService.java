package ma.marnissiabdelkarim.ecommebackend.services;

import java.util.List;

import ma.marnissiabdelkarim.ecommebackend.shared.dto.OrderDto;

public interface OrderService {
	OrderDto createOrder(OrderDto OrderDto);

	OrderDto getOrderByOrderId(String OrderId) throws Exception;

	List<OrderDto> getOrders(int page, int limit);

	void deleteOrder(String id);

	OrderDto updateOrder(String id);

}
