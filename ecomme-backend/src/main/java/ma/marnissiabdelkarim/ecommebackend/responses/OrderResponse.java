package ma.marnissiabdelkarim.ecommebackend.responses;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.CustomerDto;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.OrderItemDto;

@Data @NoArgsConstructor @AllArgsConstructor
public class OrderResponse {
	
	private String orderId;
	private Date date;
	private double totalAmount;
	private boolean isPayementConfirmed;
	private CustomerResponse customer;
	private List<OrderItemResponse> orderItems;

}
