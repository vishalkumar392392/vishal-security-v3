package com.vishal.security.vishalsecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vishal.security.vishalsecurity.entity.Customer;
import com.vishal.security.vishalsecurity.repo.CustomerRepository;
import com.vishal.security.vishalsecurity.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repo;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public String save(Customer customer) {

		customer.setPwd(encoder.encode(customer.getPwd()));

		repo.save(customer);

		return "Saved successfully..";
	}

}
