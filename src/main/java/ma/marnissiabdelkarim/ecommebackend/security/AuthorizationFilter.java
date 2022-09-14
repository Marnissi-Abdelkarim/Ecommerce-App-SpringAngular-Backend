package ma.marnissiabdelkarim.ecommebackend.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter{

	
	
	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}






	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String header = request.getHeader(SecurityConstants.HEADER_STRING);
		if(header==null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
		
		UsernamePasswordAuthenticationToken authenticationToken=getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
	}
	
	
	
	
	
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request)  {	
		
				String token = request.getHeader(SecurityConstants.HEADER_STRING);
				System.out.println("token is  ====> "+token);
				
				if(token != null) {
					token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
					Claims claims = Jwts.parser()
							.setSigningKey(SecurityConstants.TOKEN_SECRET)
							
							.parseClaimsJws(token)
							.getBody();
					String user=claims.getSubject();
					ArrayList<Map<String, String>> roles=(ArrayList<Map<String, String>>)claims.get("roles");
					  Collection<GrantedAuthority> authorities=new ArrayList<>();
					  roles.forEach(r->{
					     authorities.add(new SimpleGrantedAuthority(r.get("roleName")));
					  });
							
					if(user != null) {
						UsernamePasswordAuthenticationToken authenticatedUser=new UsernamePasswordAuthenticationToken(user, null,authorities);
						 SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
						 System.out.println("authenticated user : "+authenticatedUser);
						return  authenticatedUser;
						
					}
					return null;
				}
				
				return null;
				
	}

}
