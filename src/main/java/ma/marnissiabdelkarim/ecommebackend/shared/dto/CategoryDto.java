package ma.marnissiabdelkarim.ecommebackend.shared.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class CategoryDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8090177138162023158L;
	
	
	private long id;
	private String categoryId;
	private String name;
	private String description;
	private List<ProductDto> products;

}
