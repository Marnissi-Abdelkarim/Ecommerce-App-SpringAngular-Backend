package ma.marnissiabdelkarim.ecommebackend.services;

import ma.marnissiabdelkarim.ecommebackend.entities.RoleEntity;

public interface AccountService {
	
	public RoleEntity saveRole(RoleEntity role);
	public void addRoleToUser(String email,String rolename);
	

}
