package com.vishal.security.vishalsecurity.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vishal.security.vishalsecurity.entity.Customer;
import com.vishal.security.vishalsecurity.repo.CustomerRepository;


@Service
public class CustomerUserDetailService implements UserDetailsService {
	
	@Autowired
	private CustomerRepository customerRepository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		String userName = null;
		String password = null;
		List<GrantedAuthority> authorities = null;

		List<Customer> customers = customerRepository.findByEmail(username);

		if (customers == null || customers.isEmpty()) {
			throw new UsernameNotFoundException("User details not found for the user: " + username);
		}
		Customer customer = customers.get(0);
		userName = customer.getEmail();
		password = customer.getPwd();
		authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(customer.getRole()));
		return new User(userName, password, authorities);
	}

}
