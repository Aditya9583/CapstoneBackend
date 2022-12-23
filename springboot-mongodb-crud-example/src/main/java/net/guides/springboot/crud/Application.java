package net.guides.springboot.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
@CrossOrigin(origins = "*")
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
