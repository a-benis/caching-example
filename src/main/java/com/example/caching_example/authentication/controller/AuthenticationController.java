package com.example.caching_example.authentication.controller;

import com.example.caching_example.authentication.service.AuthenticationService;
import com.example.caching_example.authentication.utils.AuthRequest;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * The type Authentication controller.
 */
@RestController
@Api(value = "Authentication")
public class AuthenticationController {

	/**
	 * The Authentication service.
	 */
	@Autowired
	private AuthenticationService authenticationService;

	/**
	 * Authenticate response entity.
	 *
	 * @param authRequest the auth request
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticate(@Valid @RequestBody AuthRequest authRequest) throws Exception {
		return ResponseEntity.ok(authenticationService.authenticate(authRequest.getUsername(), authRequest.getPassword()));
	}
}
