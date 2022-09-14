package ma.marnissiabdelkarim.ecommebackend.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class OrderItemResponse {
	
	private String orderItemId;
	private Integer quantity;
	private ProductResponseWithCategory product;

}
