package com.pin.vetspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class VetspaceApplication {
	
	@Bean
	public WebMvcConfigurer configCORS( ) {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("*")
						.allowedOrigins("http://190.168.0.109:3000");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VetspaceApplication.class, args);
	}

}
