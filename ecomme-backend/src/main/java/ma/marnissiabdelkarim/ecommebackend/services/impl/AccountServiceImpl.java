package ma.marnissiabdelkarim.ecommebackend.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import ma.marnissiabdelkarim.ecommebackend.entities.RoleEntity;
import ma.marnissiabdelkarim.ecommebackend.entities.UserEntity;
import ma.marnissiabdelkarim.ecommebackend.repositories.RoleRepository;
import ma.marnissiabdelkarim.ecommebackend.repositories.UserRepository;
import ma.marnissiabdelkarim.ecommebackend.services.AccountService;
import ma.marnissiabdelkarim.ecommebackend.shared.Constants;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public RoleEntity saveRole(RoleEntity role) {
		
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String email, String rolename) {
		UserEntity user=userRepository.findByEmail(email);
		RoleEntity role=roleRepository.findByRoleName(rolename);
		if(role==null) { role=saveRole(new RoleEntity(null,rolename,null)); }
		user.getRoles().add(role);
		
	}
	
	public static boolean hasRole (String roleName)
	{
	    return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
	            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(roleName));
	}
	
	public static boolean isUser() {
		return hasRole(Constants.USER_ROLE);
	}
	public static boolean isAdmin() {
		return hasRole(Constants.ADMIN_ROLE);
	}
	
	public static boolean isSameToCurrentUser(String email) {
		String currentUserEmail=SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if(currentUserEmail.equals(email)) {return true;}
		else {return false;}
		
	}
	
	public static Set<String> currentUserRoles(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Set<String> roles = authentication.getAuthorities().stream()
		     .map(r -> r.getAuthority()).collect(Collectors.toSet());
		return roles;
	}
	
	public static String currentUserEmail() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
	}
	
}
