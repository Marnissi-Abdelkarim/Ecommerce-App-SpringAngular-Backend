package ma.marnissiabdelkarim.ecommebackend.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {
	
	@NotBlank(message = "this field may not be empty")
	private String productId;
	
	@NotNull(message= "quantity may not be empty")
	@Range(min = 1,message = "quantity must be greater than 1")
    private Integer quantity;
	

}
