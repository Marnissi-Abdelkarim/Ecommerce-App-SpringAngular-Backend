package ma.marnissiabdelkarim.ecommebackend.services;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import ma.marnissiabdelkarim.ecommebackend.entities.UserEntity;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.UserDto;


public interface UserService extends UserDetailsService {
	
	
	UserEntity findUserByEmail(String email);
	UserDto createUser(UserDto userDto);
	UserDto createAdmin(UserDto user);
	UserDto getUser(String email);
	UserDto getUserByUserId(String userId);
	UserDto updateUser(String id,UserDto userDto);
	void deleteUser(String id);
	List<UserDto> getUsers(int page, int limit,String search);
	
}
