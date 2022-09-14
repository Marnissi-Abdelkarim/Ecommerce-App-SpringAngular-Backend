package ma.marnissiabdelkarim.ecommebackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.marnissiabdelkarim.ecommebackend.repositories.RoleRepository;
import ma.marnissiabdelkarim.ecommebackend.services.AccountService;

@RestController
@RequestMapping("/roles") 
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	

}
