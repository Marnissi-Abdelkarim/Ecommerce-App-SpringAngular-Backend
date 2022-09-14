package ma.marnissiabdelkarim.ecommebackend;

import java.util.Arrays;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import ma.marnissiabdelkarim.ecommebackend.entities.CategoryEntity;
import ma.marnissiabdelkarim.ecommebackend.repositories.CategoryRepository;
import ma.marnissiabdelkarim.ecommebackend.requests.ProductRequest;
import ma.marnissiabdelkarim.ecommebackend.services.AccountService;
import ma.marnissiabdelkarim.ecommebackend.services.UserService;
import ma.marnissiabdelkarim.ecommebackend.shared.Utils;
import ma.marnissiabdelkarim.ecommebackend.shared.dto.UserDto;

@SpringBootApplication
public class EcommeBackendApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	UserService userService;

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	Utils utils;

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
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

	@Override
	@Transactional
	@Valid
	public void run(String... args) throws Exception {

		CategoryEntity category = categoryRepository.findByName("Undefined");

		if (category == null) {
			categoryRepository.save(new CategoryEntity(null, utils.generateStringId(30), "Undefined", "Undefined category type", null));
		}

		/*
		 * ProductRequest productRequest = new ProductRequest();
		 * productRequest.setDescription("tiw"); productRequest.setCurrentPrice(0.2);
		 * ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		 * Validator v = factory.getValidator();
		 * Set<ConstraintViolation<ProductRequest>> violations =
		 * v.validate(productRequest); for (ConstraintViolation<ProductRequest>
		 * constraintViolation : violations) { System.out.println(constraintViolation);
		 * } System.out.println(productRequest);
		 */

//		userService.createUser(new UserDto("user101","user101@gmail.com","000000"));
//		userService.createAdmin(new UserDto("admin1","admin1@gmail.com","000000"));

	}

}
