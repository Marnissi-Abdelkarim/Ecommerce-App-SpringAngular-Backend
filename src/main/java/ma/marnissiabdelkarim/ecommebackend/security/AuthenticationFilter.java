package ma.marnissiabdelkarim.ecommebackend.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.management.RuntimeErrorException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ma.marnissiabdelkarim.ecommebackend.SpringApplicationContext;
import ma.marnissiabdelkarim.ecommebackend.requests.UserLoginRequest;
import ma.marnissiabdelkarim.ecommebackend.services.UserService;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.UserDto;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {

		this.authenticationManager = authenticationManager;
	}

	// _________________________________1er
	// chose_________________________________________________

	// losque je fais le login cette methode exicute automatiquement

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request /* email , password */,
			HttpServletResponse response) throws AuthenticationException {
		try {
			UserLoginRequest creds = new ObjectMapper().readValue(request
					.getInputStream() /*
										 * les données entres doit etre le méme structure avec la class UserLogin
										 */, UserLoginRequest.class); // la reception des données

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>())); 
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String username = ((User) authResult.getPrincipal()).getUsername();

		// je vaut recupérer la valeur de méme objet qu'il existe dans WebSecurity
		UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
		// le nom de la classe qu'on instancié ms le premier lettre est miniscule

		UserDto userDto = userService.getUser(username);

		// .claim("id", userDto.getUserId()).claim("name", userDto.getFirstName() + " "
		// + userDto.getLastName())
		String token = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.claim("id", userDto.getUserId()).claim("username", userDto.getUsername())
				.claim("roles", userDto.getRoles())
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET).compact();

		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		response.addHeader("user_Id", userDto.getUserId());

		response.getWriter().write("{\"token\":\"" + token + "\",\"id\": \"" + userDto.getUserId() + "\" } ");
	}

}
