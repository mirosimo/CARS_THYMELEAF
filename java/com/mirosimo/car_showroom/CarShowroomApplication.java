package com.mirosimo.car_showroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class CarShowroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarShowroomApplication.class, args);
	}
	
	/*
	 * Create MessageSource bean
	 */
	@Bean
	public MessageSource messageSource() {
	  ReloadableResourceBundleMessageSource messageSource = new 
	  ReloadableResourceBundleMessageSource();
	  messageSource.setBasename("classpath:messages");
	  /*messageSource.setDefaultEncoding("UTF-8");*/
	  messageSource.setDefaultEncoding("ISO-8859-1");
	  return messageSource;
	}

	/*
	 * Create LocaleResolver Bean
	 */
	/*@Bean
	public LocaleResolver localeResolver(){
	  CookieLocaleResolver resolver = new CookieLocaleResolver();
	  resolver.setDefaultLocale(new Locale("")); // your default locale
	  return resolver;
	}*/

}
