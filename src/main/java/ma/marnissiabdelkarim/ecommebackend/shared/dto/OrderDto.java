package ma.marnissiabdelkarim.ecommebackend.shared.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -4603256103773578590L;
	
	
	private String orderId;
	private Date date;
	private double totalAmount;
	private boolean isPayementConfirmed;
	private String customerId;
	private CustomerDto customer;
	private List<OrderItemDto> orderItems;

}
