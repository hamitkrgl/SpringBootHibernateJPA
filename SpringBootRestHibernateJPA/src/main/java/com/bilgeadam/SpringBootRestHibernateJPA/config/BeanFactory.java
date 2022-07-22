package com.bilgeadam.SpringBootRestHibernateJPA.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Component
public class BeanFactory implements WebMvcConfigurer {
	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI().info(new Info().title("OBS - Öğrenci Bilgi Sistemi").description("OBS Application"));
	}

	// localizasyon için yazdık gerek kalmadı beanlerde var.
//	@Bean
//	public ResourceBundleMessageSource bundleMessageSource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasename("messages");
//		return messageSource;
//	}

//	@Bean(name = "OGRENCI_SERVICE")
//	public Logger getLogger() {
//		return LoggerFactory.getLogger("OGRENCI_SERVICE");
//	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
