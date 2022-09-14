package ma.marnissiabdelkarim.ecommebackend.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor

public class ProductResponseWithoutCategory {
	private String productId;
	private String name;
	private String description;
	private double currentPrice;
	private double discountedPrice;
	private boolean promotion;

}
