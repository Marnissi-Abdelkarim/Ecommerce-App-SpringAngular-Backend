package ma.marnissiabdelkarim.ecommebackend.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
	@NotNull(message = "ce champ ne doit etre null")
	@Size(min = 3, message = "ce champ doit etre plus long que 3")
	private String firstname;
	
	@NotNull(message = "ce champ ne doit etre null")
	@Size(min = 3, message = "ce champ doit etre plus long que 3")
	private String lastname;
	
	@NotNull(message = "ce champ ne doit etre null")
	@Size(min = 3, message = "ce champ doit etre plus long que 3")
	private String phone;
	
	@NotNull(message = "ce champ ne doit etre null")
	@Size(min = 10, message = "ce champ doit etre plus long que 3")
	private String address;
	
	
	private String additionalInformation;
	
	@NotNull(message = "ce champ ne doit etre null")
	@Size(min = 2, message = "ce champ doit etre plus long que 2")
	private String region;
	
	@NotNull(message = "ce champ ne doit etre null")
	@Size(min = 2, message = "ce champ doit etre plus long que 2")
	private String town;
	
	

}
