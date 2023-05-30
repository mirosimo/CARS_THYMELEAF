package mirosimo.car_showroom2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig { 
	
	//@Order
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http
			.csrf().disable()	
			.authorizeRequests()						
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()				
				.antMatchers("/logout").permitAll()
				.antMatchers("/error").permitAll()				
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("usname")
				.passwordParameter("passwd")
				.permitAll()  				
				//.successForwardUrl("/login_success_handler")
				.loginProcessingUrl("/mirosimo-login")				
				//.defaultSuccessUrl("/home", true)
				.failureUrl("/login?error=true")
				.and()
			.logout()
				.logoutUrl("/mirosimo-logout")
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/logout");		
				
		return http.build();									
	}
		
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/img/**", "/css/**");
    }
	
	//authorizeConfig.antMatchers(HttpMethod.POST, "/save-user").permitAll();
	
	//authorizeConfig.antMatchers("/user-new").hasAnyRole("ADMIN_SYSTEM","ADMIN_APP");
	//authorizeConfig.antMatchers(HttpMethod.POST, "/save-user").hasAnyRole("ADMIN_SYSTEM","ADMIN_APP");
	
	
	/*authorizeConfig.antMatchers("/car-brand-list").hasAnyRole("CAR_BRAND_VIEW", "CAR_BRAND_UPDATE");
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
	authorizeConfig.antMatchers("/car-equipment-pack-new/renault").hasAnyRole("RENAULT_UPDATE");*/
					
	//authorizeConfig.anyRequest().authenticated();

}
