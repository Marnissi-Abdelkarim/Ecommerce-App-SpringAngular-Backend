package ma.marnissiabdelkarim.ecommebackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ma.marnissiabdelkarim.ecommebackend.services.UserService;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	
	final UserService userDetailsService; 
	final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public WebSecurity(UserService userDetailsService,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService=userDetailsService;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors(); // cors() => 'il utilise pour les nomes de domaines qui sont defferente'
		http.csrf().disable();
		http.authorizeRequests().antMatchers(HttpMethod.POST,SecurityConstants.SIGN_UP_URL).permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST,SecurityConstants.SIGN_UP_URL+"/admin").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		
 		http.addFilter(getAuthenticationFilter());
		http.addFilter(new AuthorizationFilter(authenticationManager()));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// les session va gérer à partir de token (microservices)
		
	} 
	
	protected AuthenticationFilter getAuthenticationFilter() throws Exception {

		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/users/login");
		return filter;
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
}
