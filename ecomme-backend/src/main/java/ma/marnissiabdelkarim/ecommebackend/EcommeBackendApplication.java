package ma.marnissiabdelkarim.ecommebackend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import ma.marnissiabdelkarim.ecommebackend.services.AccountService;
import ma.marnissiabdelkarim.ecommebackend.services.UserService;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.UserDto;

@SpringBootApplication
public class EcommeBackendApplication extends SpringBootServletInitializer implements CommandLineRunner{
	
	@Autowired
	UserService userService;
	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EcommeBackendApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(EcommeBackendApplication.class, args);
	}
	
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext(){
		return new SpringApplicationContext();
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		
		
//		userService.createUser(new UserDto("user101","user101@gmail.com","000000"));
//		userService.createAdmin(new UserDto("admin1","admin1@gmail.com","000000"));
	
		
		
		
	}

}
