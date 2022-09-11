package ma.marnissiabdelkarim.ecommebackend.requests;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
	
	
	@NotNull(message = "ce champ ne doit etre null")
	@Size(min = 3, message = "ce champ doit etre plus long que 3")
	String name;
	
	@Size(min = 10, message = "ce champ doit etre plus long que 10")	
	String description;
	
	@NotNull(message = "ce champ ne doit etre null")
	@Min(value = 0,message = "valeur min est 0")
	Double currentPrice;
	
	
	@Min(value = 0,message = "valeur min est 0")
	double discountedPrice;
	
	
	boolean promotion=false;
	
	@NotNull(message = "ce champ ne doit etre null")
	@Valid
	CategoryRequest category;
	
	
	
	

}
