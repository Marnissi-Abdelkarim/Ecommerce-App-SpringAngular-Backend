package ma.marnissiabdelkarim.ecommebackend.shared.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Data;
import ma.marnissiabdelkarim.ecommebackend.entities.RoleEntity;

@Data
public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5088503118315914910L;

	private long id; // id user dans bd
	private String userId;
	private String username;
	private String email;
	private String password;
	private String encryptedPassword;
	private String emailVerificationToken;
	private Boolean emailVerificationStatus = false;
	private Collection<RoleDto> roles = new ArrayList<>();
	
	public UserDto() {}
	public UserDto(String username, String email, String password) {
		this.username=username;
		this.email=email;
		this.password=password;
	}

}
