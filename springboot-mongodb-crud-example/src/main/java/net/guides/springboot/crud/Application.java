package net.guides.springboot.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;



@SpringBootApplication
@CrossOrigin("http:localhost:4200")
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
//	@Bean
//	public CorsFilter corsFilter() {
//	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	CorsConfiguration config = new CorsConfiguration();
//	config.setAllowCredentials(true);
//	config.addAllowedOriginPattern("*");
//	config.addAllowedHeader("*");
//	config.addAllowedMethod("OPTIONS");
//	config.addAllowedMethod("GET");
//	config.addAllowedMethod("POST");
//	config.addAllowedMethod("PUT");
//	config.addAllowedMethod("DELETE");
//	source.registerCorsConfiguration("/**", config);
//	return new CorsFilter(source);
//	}

	
}
