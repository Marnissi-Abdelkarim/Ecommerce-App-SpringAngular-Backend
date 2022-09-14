package ma.marnissiabdelkarim.ecommebackend.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.marnissiabdelkarim.ecommebackend.exception.UserException;
import ma.marnissiabdelkarim.ecommebackend.requests.UserRequest;
import ma.marnissiabdelkarim.ecommebackend.responses.UserErrorMessages;
import ma.marnissiabdelkarim.ecommebackend.responses.UserResponse;
import ma.marnissiabdelkarim.ecommebackend.services.UserService;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.UserDto;

@CrossOrigin("*")
@RestController
@RequestMapping("/users") // localhost:8080/users
public class UserController {

	@Autowired
	UserService userService;

	//get user by id
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })

	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
		UserDto userDto = userService.getUserByUserId(id);
		ModelMapper modelMapper = new ModelMapper();
		UserResponse userResponse =  modelMapper.map(userDto, UserResponse.class);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	//get all users by pages
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<UserResponse> getAllUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "15") int limit,
			@RequestParam(value = "search", defaultValue = "") String search,Principal principal) {
		

		List<UserResponse> userResponses = new ArrayList<>();

		List<UserDto> users = userService.getUsers(page, limit, search);
		for (UserDto userDto : users) {
			ModelMapper modelMapper = new ModelMapper();
			UserResponse userResponse = modelMapper.map(userDto, UserResponse.class);
			userResponses.add(userResponse);

		}

		return userResponses;
	}

	//create user
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }) 
																					
																					
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {

		System.out.println(userRequest);
		if (userRequest.getUsername().isEmpty()) {
			
			throw new UserException(UserErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}


		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);

		UserDto createUser = userService.createUser(userDto);

		UserResponse userResponse = modelMapper.map(createUser, UserResponse.class);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	}
	
	
	//create admin
		@PostMapping(path = "/admin" ,consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }) 
																						
																						
		public ResponseEntity<UserResponse> createAdmin(@RequestBody @Valid UserRequest userRequest) throws Exception {

			System.out.println(userRequest);
			if (userRequest.getUsername().isEmpty()) {
				
				throw new UserException(UserErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
			}


			ModelMapper modelMapper = new ModelMapper();
			UserDto userDto = modelMapper.map(userRequest, UserDto.class);

			UserDto createUser = userService.createAdmin(userDto);

			UserResponse userResponse = modelMapper.map(createUser, UserResponse.class);
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
		}
	

	//update user 
	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody @Valid UserRequest userRequest) {
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);

		UserDto updateUser = userService.updateUser(id, userDto);

		UserResponse userResponse = modelMapper.map(updateUser,UserResponse.class);

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
	}

	//delete user
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
