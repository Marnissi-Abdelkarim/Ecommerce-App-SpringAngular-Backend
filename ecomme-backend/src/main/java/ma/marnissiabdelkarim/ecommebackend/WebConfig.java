package ma.marnissiabdelkarim.ecommebackend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	
	public void addCorsMappings(CorsRegistry registry) {
		//registry.addMapping("/users").allowedMethods("GET","PUT","POST").allowedOrigins("*");
      registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
      	
	}

}
