package ma.marnissiabdelkarim.ecommebackend.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CustomerResponse {
	private String customerId;
	private String firstname;
	private String lastname;
	private String phone;
	private String address;
	private String additionalInformation;
	private String region;
	private String town;
	private UserResponse user;
}
