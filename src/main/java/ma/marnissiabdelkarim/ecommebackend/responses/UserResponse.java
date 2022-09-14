package ma.marnissiabdelkarim.ecommebackend.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserResponse {
	private String userId;
	private String username;
	private String email;

	
}