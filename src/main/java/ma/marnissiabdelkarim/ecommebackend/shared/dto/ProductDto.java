package ma.marnissiabdelkarim.ecommebackend.shared.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1914011688810100315L;
	private long id;
	private String productId;
	private String name;
	private String description;
	private double currentPrice;
	private double discountedPrice;
	private boolean promotion;
	private CategoryDto category;

}
