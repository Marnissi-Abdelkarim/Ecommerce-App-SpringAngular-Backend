package ma.marnissiabdelkarim.ecommebackend.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor

public class CategoryResponseWithoutProducts {
	private String categoryId;
	private String name;
	private String description;

}
