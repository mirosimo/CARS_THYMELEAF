package mirosimo.car_showroom2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mirosimo.car_showroom2.model.User;
import mirosimo.car_showroom2.repository.UserRepository;


/* Security is just now in development state ... */
@Service
public class AuthenticationUserService implements UserDetailsService{
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        UserBuilder builder= org.springframework.security.core.userdetails.User
				.withUsername(username);
        builder.password(bCryptPasswordEncoder.encode(user.getPassword()));
        builder.roles(user.getRole());
        System.out.println("BUBUBUBU");
        //builder.authorities(user.getRole());
        return builder.build();
	}

    /*private Set<GrantedAuthority> convertAuthorities(Set<UserRole> userRoles) {
        Set<GrantedAuthority> authorities=new HashSet<>();
        for (UserRole userRole : userRoles) {
            authorities.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        return authorities;
    }*/

	
}
