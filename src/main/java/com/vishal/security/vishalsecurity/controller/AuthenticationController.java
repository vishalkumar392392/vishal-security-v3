package com.vishal.security.vishalsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vishal.security.vishalsecurity.config.JwtUtils;
import com.vishal.security.vishalsecurity.entity.Customer;
import com.vishal.security.vishalsecurity.modal.AuthenticationRequest;
import com.vishal.security.vishalsecurity.service.CustomerService;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private CustomerService service;

	@PostMapping("/login")
	public String register(@RequestBody AuthenticationRequest request) {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		if (userDetails != null) {
			return jwtUtils.generateToken(userDetails);
		} else {
			throw new BadCredentialsException("User not found");
		}

	}

	@PostMapping("/register")
	public String create(@RequestBody Customer cutomer) {
		return service.save(cutomer);
	}

}
