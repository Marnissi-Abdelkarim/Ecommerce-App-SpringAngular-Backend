package ma.marnissiabdelkarim.ecommebackend.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
	
	@NotNull(message = "ce champ ne doit etre null")
	@Size(min = 3, message = "ce champ doit etre plus long que 3")
	String name;
	
	@Size(min = 10, message = "ce champ doit etre plus long que 10")
	String description;

}
