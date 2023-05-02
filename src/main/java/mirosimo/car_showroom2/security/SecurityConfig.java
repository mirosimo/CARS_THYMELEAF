package mirosimo.car_showroom2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/* **************************************************/
/*    Security is just now in development state ... */
/* **************************************************/
@Configuration
@EnableWebSecurity
public class SecurityConfig { 
	
	//@Order
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
			//.antMatcher("/something")
			.csrf().disable()	
			.authorizeRequests(authorizeConfig -> {						
				authorizeConfig.antMatchers("/").permitAll();
				authorizeConfig.antMatchers("/login").permitAll();				
				authorizeConfig.antMatchers("/error").permitAll();
				
				authorizeConfig.antMatchers("/img/**").permitAll();
				authorizeConfig.antMatchers("/css/**").permitAll();
				
				
				authorizeConfig.antMatchers("/user-new").hasAnyRole("ADMIN_SYSTEM","ADMIN_APP");
				authorizeConfig.antMatchers(HttpMethod.POST, "/save-user").hasAnyRole("ADMIN_SYSTEM","ADMIN_APP");
				
				
				authorizeConfig.antMatchers("/car-brand-list").hasAnyRole("CAR_BRAND_VIEW", "CAR_BRAND_UPDATE");
				authorizeConfig.antMatchers("/car-brand-new").hasAnyRole("CAR_BRAND_UPDATE");
				
				authorizeConfig.antMatchers("/car-model-list/skoda").hasAnyRole("SKODA_VIEW","SKODA_UPDATE");
				authorizeConfig.antMatchers("/car-model-new/skoda").hasAnyRole("SKODA_UPDATE");
				authorizeConfig.antMatchers("/car-equipment-pack-list/skoda/**").hasAnyRole("SKODA_VIEW","SKODA_UPDATE");
				authorizeConfig.antMatchers("/car-equipment-pack-new/skoda").hasAnyRole("SKODA_UPDATE");
				
				
				authorizeConfig.antMatchers("/car-model-list/audi").hasAnyRole("AUDI_VIEW","AUDI_UPDATE");
				authorizeConfig.antMatchers("/car-model-new/audi").hasAnyRole("AUDI_UPDATE");
				authorizeConfig.antMatchers("/car-equipment-pack-list/audi/**").hasAnyRole("AUDI_VIEW","AUDI_UPDATE");
				authorizeConfig.antMatchers("/car-equipment-pack-new/audi").hasAnyRole("AUDI_UPDATE");
				
				authorizeConfig.antMatchers("/car-model-list/renault").hasAnyRole("RENAULT_VIEW","RENAULT_UPDATE");
				authorizeConfig.antMatchers("/car-model-new/renault").hasAnyRole("RENAULT_UPDATE");
				authorizeConfig.antMatchers("/car-equipment-pack-list/renault/**").hasAnyRole("RENAULT_VIEW","RENAULT_UPDATE");
				authorizeConfig.antMatchers("/car-equipment-pack-new/renault").hasAnyRole("RENAULT_UPDATE");
								
				authorizeConfig.anyRequest().authenticated();	
				
			})					
				.formLogin(Customizer.withDefaults())
					
				.build();			
	}
		
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
