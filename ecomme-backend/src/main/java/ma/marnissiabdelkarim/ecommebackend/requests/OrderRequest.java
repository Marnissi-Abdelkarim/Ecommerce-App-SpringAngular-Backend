package ma.marnissiabdelkarim.ecommebackend.requests;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
	@NotNull(message = "ce champ ne doit etre null")
	private String customerId;
	
	@Valid
	@NotNull(message = "ce champ ne doit etre null")
	private List<OrderItemRequest> orderItems;

}
