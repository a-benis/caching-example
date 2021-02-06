package com.example.caching_example.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * The type Jwt user details service.
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

	/**
	 * Load user by username user details.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("testing".equals(username)) {
			return new User("testing", "$2a$10$I/Ao3SRGxUZa651B9iWB8.lxRccdE5yCSyeoadc9JJ9zcKXN6I3/y", new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
