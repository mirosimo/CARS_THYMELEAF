package mirosimo.car_showroom2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/* Security is just now in development state ... */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
		
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
			.authorizeRequests(authorizeConfig -> {
				authorizeConfig.antMatchers("/department-new").authenticated();
				authorizeConfig.anyRequest().permitAll();
			})					
				.formLogin(Customizer.withDefaults())
				.build();			
	}
	
	/*@Bean 
	public UserDetailsService userDetailService() {
		//FilterChainProxy 
		return new InMemoryUserDetailsManager(
				User.builder()
						.username("evzen")
						.password("{noop}evzen")
						.authorities("leader")
						.build()
				);
	}*/
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
