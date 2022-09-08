package ma.marnissiabdelkarim.ecommebackend.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ma.marnissiabdelkarim.ecommebackend.entities.RoleEntity;
import ma.marnissiabdelkarim.ecommebackend.entities.UserEntity;
import ma.marnissiabdelkarim.ecommebackend.repositories.RoleRepository;
import ma.marnissiabdelkarim.ecommebackend.repositories.UserRepository;
import ma.marnissiabdelkarim.ecommebackend.services.AccountService;

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
	
	

}
