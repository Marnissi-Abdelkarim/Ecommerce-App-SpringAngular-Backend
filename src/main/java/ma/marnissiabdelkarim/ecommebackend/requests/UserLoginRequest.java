package ma.marnissiabdelkarim.ecommebackend.requests;

import lombok.Data;

@Data
public class UserLoginRequest {
	
	String email;
	String password;

}
