package ma.marnissiabdelkarim.ecommebackend.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor

public class CategoryResponseWithProducts {
	private String categoryId;
	private String name;
	private String description;
	private List<ProductResponseWithoutCategory> products;
}
