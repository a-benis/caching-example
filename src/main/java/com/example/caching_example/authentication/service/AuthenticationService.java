package com.example.caching_example.authentication.service;

import com.example.caching_example.config.JwtTokenUtil;
import com.example.caching_example.config.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * The type Authentication service.
 */
@Service
public class AuthenticationService {

	/**
	 * The Authentication manager.
	 */
	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * The Jwt token util.
	 */
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * The User details service.
	 */
	@Autowired
	private JwtUserDetailsService userDetailsService;

	/**
	 * Authenticate string.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the string
	 * @throws Exception the exception
	 */
	public String authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			return jwtTokenUtil.generateToken(userDetails);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
