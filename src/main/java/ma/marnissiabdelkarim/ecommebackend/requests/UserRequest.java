package ma.marnissiabdelkarim.ecommebackend.requests;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
	
	@NotNull(message = "ce champ ne doit etre null")
	@Size(min = 3, message = "ce champ doit etre plus long que 3")
	private String username;

	@NotNull(message = "ce champ ne doit etre null")
	@Email(message = "doit etre un email")
	private String email;

	@NotNull(message = "ce champ ne doit etre null")
	@Size(min = 4, max = 12, message = "doit etre entre 4 et 12")
	// @Pattern(regexp =
	// "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",message = "ce motd de
	// passe doit avoir des lettres en Maj et Minscule et numero ")// pour applique
	// un expression regulare
	private String password;

}
