package ma.marnissiabdelkarim.ecommebackend.shared.dto;

import java.io.Serializable;

import lombok.Data;


@Data
public class OrderItemDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9124973465777669101L;
	
	
	private String orderItemId;
	private String productId;
	private Integer quantity;
	private ProductDto product;

}
